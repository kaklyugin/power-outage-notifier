package org.rostovenergoparser.tgclient.service.polling;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.tgclient.deserializer.GsonConfig;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.TelegramResponseAsJsonStringsDto;
import org.rostovenergoparser.tgclient.rabbit.BrokerMessageKeyStore;
import org.rostovenergoparser.tgclient.rabbit.service.RabbitMQProducer;
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
    private final RabbitMQProducer rabbitMQProducer;
    private final BrokerMessageKeyStore rabbitMQBrokerMessageKeyStore;

    public UpdatesListener(HttpBotClient httpBotClient, GsonConfig gsonConfig, RabbitMQProducer rabbitMQProducer, BrokerMessageKeyStore rabbitMQBrokerMessageKeyStore) {
        this.httpBotClient = httpBotClient;
        this.gsonConfig = gsonConfig;
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQBrokerMessageKeyStore = rabbitMQBrokerMessageKeyStore;
    }

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void getUpdatesBySchedule() {

        log.info("Launched getUpdates scheduled task");
        var rawJsonUpdates = httpBotClient.getUpdates();
        log.info("Updates received from tg =  {}", rawJsonUpdates);
        var updates = gsonConfig.gson().fromJson(rawJsonUpdates, TelegramResponseAsJsonStringsDto.class);
        if (!updates.isOk())
            throw new RuntimeException("No updates found." + rawJsonUpdates);
        if (!updates.getRawJsonList().isEmpty()) {
            updates.getRawJsonList().forEach(upd ->
                    {
                        var update = gsonConfig.gson().fromJson(upd, AbstractUpdateResultDto.class);
                        var json = gsonConfig.gson().toJson(update);
                        if (!rabbitMQBrokerMessageKeyStore.checkExists(String.valueOf(update.getUpdateId())))
                            {
                                rabbitMQBrokerMessageKeyStore.push(String.valueOf(update.getUpdateId()));
                                rabbitMQProducer.sendMessage(json);
                                log.info("Sent to rabbit mq update: {}", json);
                            }
                    }
            );
        }
    }
}

