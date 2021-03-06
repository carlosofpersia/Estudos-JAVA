package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class KafkaService<T> implements Closeable {

    private final KafkaConsumer<String,  Message<T>> consumer;
    private final ConsumerFunction<T> parse;

    public KafkaService(
            String groupId
            , String topic
            , ConsumerFunction<T> parse
            , Map<String, String> properties) {
        this(parse, groupId, properties);
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    public KafkaService(String groupId
            , Pattern topic
            , ConsumerFunction<T> parse
            , Map<String, String> properties) {
        this(parse, groupId, properties);
        this.consumer.subscribe(topic);
    }

    private KafkaService(
            ConsumerFunction<T> parse
            , String groupId
            , Map<String, String> properties) {
        this.parse = parse;
        this.consumer = new KafkaConsumer<>(getProperties(groupId, properties));
    }

    public void run() throws ExecutionException, InterruptedException {

        try( var deadLetterDispatcher = new KafkaDispatcher<>() ) {

            while(true) {
                var records = consumer.poll(Duration.ofMillis(100));
                if (!records.isEmpty()) {
                    System.out.println("Encontrei " + records.count() + " registros!");
                    for (var record : records) {
                        try {
                            parse.consumer(record);
                        } catch (Exception e) {
                            // only catches Exception because no matter witch Exception
                            // i want to recover and parse the next one.
                            // so far, just logging the exception for this message
                            e.printStackTrace();
                            var message = record.value();
                            deadLetterDispatcher.send(
                                    "ECOMMERCE_DEADLETTER"
                                    , message.getId().toString()
                                    , message.getId().continueWith("DeadLetter")
                                    , new GsonSerializer().serialize("", message) );
                        }
                    }
                } else {
                    System.out.println("Aguardando registros!");
                }
            }
        }
    }

    private Properties getProperties(String groupId, Map<String, String> overrideProperties) {
        var properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());

        // obrigatorio receber de um grupo.
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //CLIENT_ID_CONFIG aqui colocar o ip da maquina ou o id do usuario
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());

        //MAX_POLL_RECORDS_CONFIG max de registros para commitar. menos chance de perder registros.
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

        //Problema com deserializacao de String para Gson na hora de deserializar.
        properties.putAll(overrideProperties);

        return properties;
    }


    @Override
    public void close() {
        consumer.close();
    }
}



