package org.rostovenergoparser.tgclient.service.polling;

import lombok.RequiredArgsConstructor;
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
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:bot.properties") //TODO все настройки в один файл
public class UpdatesListener { //TODO создать интерфейс

    private final HttpBotClient httpBotClient;
    private final GsonConfig gsonConfig;
    private final RabbitMQProducer rabbitMQProducer;
    private final BrokerMessageKeyStore rabbitMQBrokerMessageKeyStore;

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void getUpdatesBySchedule() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Launched getUpdates scheduled task");
        String rawJsonUpdates = httpBotClient.getUpdates();
        log.info("Updates received from tg =  {}", rawJsonUpdates);
        TelegramResponseAsJsonStringsDto updates = gsonConfig.gson().fromJson(rawJsonUpdates, TelegramResponseAsJsonStringsDto.class);
        if (!updates.isOk()) {
            throw new RuntimeException("No updates found." + rawJsonUpdates);
        }

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

