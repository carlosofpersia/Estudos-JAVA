package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class BatchSendMessageService {

    private final Connection connection;

    private final KafkaDispatcher<User> userDispatcher = new KafkaDispatcher<>();

    BatchSendMessageService() throws SQLException {
        String url = "jdbc:sqlite:target/users_database.db";
        connection = DriverManager.getConnection(url);
        try {
            connection.createStatement().execute("create table Users ( uuid varchar (200) primary key, email varchar(200)  )");
        } catch (SQLException ex) {
            // be careful, the sql could be wrong, be realllly careful
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, SQLException {
        var batchService = new BatchSendMessageService();
        try (var service = new KafkaService<>(BatchSendMessageService.class.getSimpleName()
                , "ECOMMERCE_SEND_MESSAGE_TO_ALL_USERS"
                , batchService::parse
                , new HashMap<>())) {
            service.run();
        };
    }

    private void parse(ConsumerRecord<String, Message<String>> record)
            throws SQLException, ExecutionException, InterruptedException {

        System.out.println("---------------------------------------------");
        System.out.println("Processing new batch");
        System.out.println("Topic: " + record.topic());

        // Testando dead letter. colocado no catch do KafkaService deadLetterDispatcher
        Random isDeadLetter = new Random();
        if(isDeadLetter.nextBoolean()) {
            throw new RuntimeException("deu um erro que eu forcei!");
        }

        var message = record.value();
        for(User user: getAllUsers()) {
            userDispatcher.sendAsync(
                    message.getPayload()
                    , user.getUuid()
                    , message.getId().continueWith(BatchSendMessageService.class.getSimpleName())
                    , user);
            System.out.println("Acho que enviei para " + user);
        }
        System.out.println("Batch processed!");
    }

    private List<User> getAllUsers() throws SQLException {

        var results = connection.prepareStatement("select uuid from Users").executeQuery();
        List<User> users = new ArrayList<>();
        while(results.next()) {
            users.add(new User(results.getString(1)));
        }
        return users;
    }



}
