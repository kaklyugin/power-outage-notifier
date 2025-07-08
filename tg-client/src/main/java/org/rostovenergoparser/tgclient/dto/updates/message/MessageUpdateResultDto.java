package org.rostovenergoparser.tgclient.dto.updates.message;

import lombok.Getter;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

@Getter
public class MessageUpdateResultDto extends AbstractUpdateResultDto {

    private final MessageUpdateDto message;

    public MessageUpdateResultDto(Long updateId, MessageUpdateDto message) {
        super(updateId);
        this.message = message;
    }

    @Override
    public String getUserResponse() {
        return this.message.getText();
    }

    @Override
    public String getResponseType() {
        return "message";
    }

    @Override
    public Chat getChat() {
        return message.getChat();
    }

    @Override
    public From getForm() {
        return message.getFrom();
    }
}
