package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.tgclient.dto.message.request.MessageBodyDto;
import org.rostovenergoparser.tgclient.dto.message.request.keyboard.InlineKeyboardButtonDto;
import org.rostovenergoparser.tgclient.dto.message.request.keyboard.InlineKeyboardDto;

import java.util.List;

@Slf4j
public class StartMessageHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto message) {
        log.info("Handling message = {}", message);
        MessageBodyDto messageBodyDto = MessageBodyDto.builder().replyMarkup(
            new InlineKeyboardDto.KeyboardBuilder()
                    .addRow(
                    List.of(
                            new InlineKeyboardButtonDto("Ростов","CALLBACK_ROSTOV"),
                            new InlineKeyboardButtonDto("Аксай","CALLBACK_AKSAI")
                    )
                    ).addRow(
                            List.of(
                                    new InlineKeyboardButtonDto("Новочеркасск","CALLBACK_NOVOCHERKASSK")
                            )
                    ).build()
                ).build();

        return message.getUserResponse();
    }
}
