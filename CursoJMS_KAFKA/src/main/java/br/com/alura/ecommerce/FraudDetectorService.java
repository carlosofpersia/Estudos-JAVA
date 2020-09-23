package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class FraudDetectorService {

    public static void main(String[] args) {

        var consumer = new KafkaConsumer<String, String>(getProperties());
        consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));

        while(true) {
            var records = consumer.poll(Duration.ofMillis(100));

            if( !records.isEmpty() ) {
                System.out.println("Encontrei " + records.count() + " registros!");
                int i = 1;
                for(var record: records) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Processing new order, checking for fraud" + i);
                    System.out.println("KEY: " + record.key() + " / VALUE: " + record.value());
                    System.out.println(record.topic() + ":::partition: " + record.partition() + " / offset: " + record.offset() + " / timestamp: " + record.timestamp());
                    System.out.println("Order processed!");
                    i++;
                }
            } else {
                System.out.println("Fraud.Aguardando registros!");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // ignoring
                e.printStackTrace();
            }
        }

    }

    private static Properties getProperties() {
        var properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //GROUP_ID_CONFIG obrigatorio receber de um grupo.
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());

        //CLIENT_ID_CONFIG aqui colocar o ip da maquina ou o id do usuario
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, FraudDetectorService.class.getSimpleName() + "_" + UUID.randomUUID().toString());

        //MAX_POLL_RECORDS_CONFIG max de registros para commitar. menos chance de perder registros.
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

        return properties;
    }


}