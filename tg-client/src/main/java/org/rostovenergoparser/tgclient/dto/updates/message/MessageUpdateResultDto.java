package org.rostovenergoparser.tgclient.dto.updates.message;

import lombok.Data;
import lombok.Getter;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.MessageType;

@Data
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
    public MessageType getResponseType() {
        if (this.message.getEntities() == null || this.message.getEntities().isEmpty()) {
            return MessageType.message;
        } else if (this.message.getEntities().getFirst().getType().equals(MessageEntityType.bot_command)) {
            return MessageType.bot_command;
        }
        return null;
    }

    @Override
    public Chat getChat() {
        return message.getChat();
    }
}
