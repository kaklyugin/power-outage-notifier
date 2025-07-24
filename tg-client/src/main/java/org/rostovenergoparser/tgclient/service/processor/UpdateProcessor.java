package org.rostovenergoparser.tgclient.service.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.persistence.entity.ChatEntity;
import org.rostovenergoparser.persistence.repository.ChatRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateProcessor {
    private final ChatRepository chatRepository;
    private final ObjectMapper objectMapper;

    public UpdateProcessor(ChatRepository chatRepository, ObjectMapper objectMapper) {
        this.chatRepository = chatRepository;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.updates.queue.name}")
    public void handleMessage(String message, Channel channel,
                              @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)  {
        try {
            var update = objectMapper.readValue(message, UpdateDto.class);
            ChatEntity contextEntity = chatRepository.findById(update.getChatId())
                    .orElse(new ChatEntity());
            DialogStateMachine dialogStateMachine = (contextEntity.getId() == null)  ?
                    new DialogStateMachine(update): new DialogStateMachine(contextEntity.getContext());
            dialogStateMachine.handle(update);
            contextEntity.setId(dialogStateMachine.getContext().getChatId());
            contextEntity.setContext(dialogStateMachine.getContext());
            chatRepository.save(contextEntity);
            channel.basicAck(deliveryTag, false);

        }
        catch (Exception e) {
            log.error("RabbitMQ message processor failed. Could not process update {}", e.getMessage());
            channel.basicReject(deliveryTag, false);
        }

    }
}
