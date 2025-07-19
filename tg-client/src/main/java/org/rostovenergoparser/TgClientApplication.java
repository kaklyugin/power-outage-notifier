package org.rostovenergoparser;

import org.mapstruct.extensions.spring.SpringMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TgClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgClientApplication.class, args);
	}

}
