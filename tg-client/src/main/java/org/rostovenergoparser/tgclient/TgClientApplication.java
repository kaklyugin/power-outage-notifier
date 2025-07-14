package org.rostovenergoparser.tgclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TgClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgClientApplication.class, args);
	}

}
