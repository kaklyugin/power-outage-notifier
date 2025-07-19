package org.rostovenergoparser.tgclient.service.polling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.mapper.UpdateResponseMapper;
import org.rostovenergoparser.rabbit.producer.RabbitMQUpdateProducer;
import org.rostovenergoparser.tgclient.dto.updates.UpdatesResponseDto;
import org.rostovenergoparser.tgclient.service.http.HttpBotClient;
import org.rostovenergoparser.tgclient.storage.ChatStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//TODO Обсудить с Сашей - попробовал сделать через интерфейс и посыпались ошибки
public class UpdatesPollingService {

    private final HttpBotClient httpBotClient;
    private final ChatStore chatStore;
    private final RabbitMQUpdateProducer rabbitMQUpdateProducer;
    private final ObjectMapper objectMapper;
    private final UpdateResponseMapper mapper;

    public UpdatesPollingService(HttpBotClient httpBotClient,
                                 ChatStore chatStore,
                                 RabbitMQUpdateProducer rabbitMQUpdateProducer,
                                 ObjectMapper objectMapper,
                                 UpdateResponseMapper mapper) {
        this.httpBotClient = httpBotClient;
        this.chatStore = chatStore;
        this.rabbitMQUpdateProducer = rabbitMQUpdateProducer;
        this.objectMapper = objectMapper;
        this.mapper = mapper;
    }

    @SneakyThrows
    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void poll() {
        log.info("Polling");
        String json = httpBotClient.getUpdates();
        log.info("Updates received from tg =  {}", json);

        UpdatesResponseDto updates = objectMapper.readValue(json, UpdatesResponseDto.class);
        if (!updates.isOk()) {
            throw new RuntimeException("No updates found." + json);
        }
        updates.getResult().forEach(responseDto ->
            {
                if (!chatStore.checkUpdateExists(responseDto.getChatId(), responseDto.getUpdateId())) {
                    chatStore.pushUpdate(responseDto.getChatId(), responseDto.getUpdateId(), responseDto);
                    UpdateDto updateDto = mapper.mapUpdateResponseToUpdateDto(responseDto);
                    publishUpdateForProcessing(updateDto);
                }
            }
        );
    }

    public void publishUpdateForProcessing(UpdateDto update) {
        try {
            rabbitMQUpdateProducer.sendMessage(objectMapper.writeValueAsString(update));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}