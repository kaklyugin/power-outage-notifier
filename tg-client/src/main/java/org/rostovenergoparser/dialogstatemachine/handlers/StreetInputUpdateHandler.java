package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.common.ApplicationContextProvider;
import org.rostovenergoparser.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dialogstatemachine.enums.DialogQuestionCodes;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.tgclient.dto.message.request.MessageDto;
import org.rostovenergoparser.tgclient.service.http.BotClient;
import org.springframework.context.ApplicationContext;

@Slf4j
public class StreetInputUpdateHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto update, DialogStateMachine dialogStateMachine) {
        log.info("Handling message = {}", update);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        BotClient botClient = context.getBean(BotClient.class);

        dialogStateMachine.getContext().addUserTextAnswer(DialogQuestionCodes.STREET, update.getUserResponse());

        MessageDto cityInputQuestionMessage = MessageDto.builder()
                .chatId(String.valueOf(update.getChatId()))
                .text("Всё получилось. Мы отправим уведомление, если на вашей улице будет запланировано отключение света.")
                .build();
        String messageId = botClient.sendMessage(cityInputQuestionMessage).getMessageId();
        dialogStateMachine.getContext().addQuestion(Long.valueOf(messageId), DialogQuestionCodes.STREET);
        return update.getUserResponse();
    }
}
