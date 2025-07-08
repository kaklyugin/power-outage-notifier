package org.rostovenergoparser.tgclient.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GsonConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(AbstractUpdateResultDto.class, new UpdateResultAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .disableHtmlEscaping()
                .setPrettyPrinting() // Only for development
                .create();
    }
}