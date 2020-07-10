package com.cpedroza.KafkaProducer;

import com.cpedroza.Domains.OrderSubmittedEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerImpl {
        private KafkaProducer kafkaProducer;
        private Properties properties = new Properties();
        private int recordCount = 1;

        public KafkaProducerImpl(){
            properties.put("bootstrap.servers", "localhost:9092");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            kafkaProducer = new KafkaProducer(properties);
        }

        public void sendRecord(OrderSubmittedEvent message){
            kafkaProducer.send(new ProducerRecord("order-submitted", Integer.toString(recordCount), message.toString() ));
            recordCount++;
    }
}
