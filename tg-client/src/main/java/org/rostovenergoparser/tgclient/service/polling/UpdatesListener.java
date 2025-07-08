package org.rostovenergoparser.tgclient.service.polling;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.tgclient.deserializer.GsonConfig;
import org.rostovenergoparser.tgclient.dto.updates.TelegramResponseAsJsonStringsDto;
import org.rostovenergoparser.tgclient.service.HttpBotClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableAsync
@Configuration
@PropertySource("classpath:bot.properties")
public class UpdatesListener {

    private final HttpBotClient httpBotClient;
    private final GsonConfig gsonConfig;

    public UpdatesListener(HttpBotClient httpBotClient, GsonConfig gsonConfig) {
        this.httpBotClient = httpBotClient;
        this.gsonConfig = gsonConfig;
    }

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void getUpdatesBySchedule() {

        log.info("Launched getUpdates scheduled task");
        var updatesJson = httpBotClient.getUpdates();
        var updates = gsonConfig.gson().fromJson(updatesJson, TelegramResponseAsJsonStringsDto.class);
        log.info("Deserialized json with updates =  {}", updates.toString());
        if (!updates.isOk())
            throw new RuntimeException("No updates found." + updatesJson);

//        if (!updates.getResult().isEmpty()) {
//            updates.getResult().forEach(
//
//            );
//        }

        //}
    }

}
