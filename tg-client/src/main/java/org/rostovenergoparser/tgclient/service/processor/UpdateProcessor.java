package org.rostovenergoparser.tgclient.service.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.bot.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.persistence.entity.DialogContextEntity;
import org.rostovenergoparser.persistence.repository.DialogContextRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateProcessor {
    private final DialogContextRepository dialogContextRepository;
    private final ObjectMapper objectMapper;

    public UpdateProcessor(DialogContextRepository dialogContextRepository, ObjectMapper objectMapper) {
        this.dialogContextRepository = dialogContextRepository;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.updates.queue.name}")
    public void receiveMessage(String message) {
        var update = objectMapper.readValue(message, UpdateDto.class);
        DialogContextEntity contextEntity = dialogContextRepository.findById(update.getChatId())
                .orElse(new DialogContextEntity());
        DialogStateMachine dialogStateMachine = (contextEntity.getId() == null)  ?
                new DialogStateMachine(update): new DialogStateMachine(contextEntity.getContext());
        dialogStateMachine.handle(update);
        contextEntity.setId(dialogStateMachine.getContext().getChatId());
        contextEntity.setContext(dialogStateMachine.getContext());
        dialogContextRepository.save(contextEntity);
    }
}
