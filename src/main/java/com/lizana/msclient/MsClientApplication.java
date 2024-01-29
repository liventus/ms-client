package com.lizana.msclient;


import com.lizana.msclient.event.ClientKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@Slf4j
public class MsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientApplication.class, args);
	}

  @KafkaListener(topics = "notificationTopic")
	public void handleNotification(ClientKafka clientKafka){
		log.info("NOtification recibida desde order - {}",clientKafka);
	}



}
