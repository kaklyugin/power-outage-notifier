package org.rostovenergoparser.tgclient.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class MessageDto extends MessageBody {

    @JsonProperty("chat_id")
    private String chatId;

    public MessageDto(String chatId, MessageBody body) {
        super(body.toBuilder());
        this.chatId = chatId;
    }
}
