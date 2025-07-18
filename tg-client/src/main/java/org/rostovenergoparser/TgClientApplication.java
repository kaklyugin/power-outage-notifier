package org.rostovenergoparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@EnableScheduling
@EnableAsync
public class TgClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgClientApplication.class, args);
	}

}
