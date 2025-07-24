package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.common.ApplicationContextProvider;
import org.rostovenergoparser.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dialogstatemachine.enums.DialogQuestionCodes;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.tgclient.dto.message.request.MessageDto;
import org.rostovenergoparser.tgclient.dto.message.request.keyboard.InlineKeyboardDto;
import org.rostovenergoparser.tgclient.service.http.BotClient;
import org.springframework.context.ApplicationContext;

@Slf4j
public class StartMessageHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto update, DialogStateMachine dialogStateMachine) {
        log.info("Handling message = {}", update);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        BotClient botClient = context.getBean(BotClient.class);

        MessageDto selectCityMessage = MessageDto.builder()
                .chatId(String.valueOf(update.getChatId()))
                .text("Выберите город")
                .replyMarkup(
                        new InlineKeyboardDto.KeyboardBuilder()
                                .addRow()
                                .addButton("Ростов","CALLBACK_ROSTOV")
                                .addButton("Аксай","CALLBACK_AKSAI")
                                .addRow()
                                .addButton("Новочеркасск","CALLBACK_NOVOCHERKASSK")
                                .build())
                .build();
            String messageId = botClient.sendMessage(selectCityMessage).getMessageId();
            dialogStateMachine.getContext().addQuestion(Long.valueOf(messageId), DialogQuestionCodes.CITY);
        return null;
    }
}
