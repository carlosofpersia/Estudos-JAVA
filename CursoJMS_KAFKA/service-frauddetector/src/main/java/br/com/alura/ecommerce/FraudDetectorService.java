package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FraudDetectorService {

    private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var fraudDetectorService = new FraudDetectorService();
        try (var service = new KafkaService<Order>(
                FraudDetectorService.class.getSimpleName()
                , "ECOMMERCE_NEW_ORDER"
                , fraudDetectorService::parse
                , new HashMap<>())) {
            service.run();
        };
    }

    private void parse(ConsumerRecord<String, Message<Order>> record) throws ExecutionException, InterruptedException {
        System.out.println("---------------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println("KEY: " + record.key() + " / VALUE: " + record.value());
        System.out.println(record.topic() + ":::partition: " + record.partition() + " / offset: " + record.offset() + " / timestamp: " + record.timestamp());
        System.out.println("Order processed!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }

        var message = record.value();

        var order = message.getPayload();

        if(isFraud(order)) {
            System.out.println("Order is a fraud!!!");
            orderDispatcher.send("ECOMMERCE_ORDER_REJECTED"
                    , order.getEmail()
                    , message.getId().continueWith(FraudDetectorService.class.getSimpleName())
                    , order);
        } else {
            System.out.println("Approved:" + order);
            orderDispatcher.send("ECOMMERCE_ORDER_APPROVED"
                    , order.getEmail()
                    , message.getId().continueWith(FraudDetectorService.class.getSimpleName())
                    , order);
        }
    }

    private boolean isFraud(Order order) {
        return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
    }

}
