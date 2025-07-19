package org.rostovenergoparser.tgclient.service.polling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.rabbit.producer.RabbitMQUpdateProducer;
import org.rostovenergoparser.tgclient.deserializer.JacksonConfig;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.TelegramResponseAsJsonStringsDto;
import org.rostovenergoparser.tgclient.service.http.HttpBotClient;
import org.rostovenergoparser.tgclient.storage.ChatStore;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//TODO Обсудить с Сашей - попробовал сделать через интерфейс и посыпались ошибки
public class UpdatesPollingService {

    private final HttpBotClient httpBotClient;
    private final JacksonConfig jacksonConfig;
    private final ChatStore chatStore;
    private final RabbitMQUpdateProducer rabbitMQUpdateProducer;
    private final ObjectMapper objectMapper;

    public UpdatesPollingService(HttpBotClient httpBotClient, JacksonConfig jacksonConfig, ChatStore chatStore, RabbitMQUpdateProducer rabbitMQUpdateProducer, ObjectMapper objectMapper) {
        this.httpBotClient = httpBotClient;
        this.jacksonConfig = jacksonConfig;
        this.chatStore = chatStore;
        this.rabbitMQUpdateProducer = rabbitMQUpdateProducer;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void poll() {
        log.info("Polling");
        String rawJsonUpdates = httpBotClient.getUpdates();
        log.info("Updates received from tg =  {}", rawJsonUpdates);
        TelegramResponseAsJsonStringsDto updates = objectMapper.readValue(rawJsonUpdates, TelegramResponseAsJsonStringsDto.class);
        if (!updates.isOk()) {
            throw new RuntimeException("No updates found." + rawJsonUpdates);
        }
        updates.getResult().forEach(update ->
                {
                    if (!chatStore.checkUpdateExists(update.getChat().getId(), update.getUpdateId())) {
                        chatStore.pushUpdate(update.getChat().getId(), update.getUpdateId(), update);
                        publishUpdateForProcessing(update);
                    }
                }
        );
    }

    public void publishUpdateForProcessing(AbstractUpdateResultDto message) {
        try {
            rabbitMQUpdateProducer.sendMessage(objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}