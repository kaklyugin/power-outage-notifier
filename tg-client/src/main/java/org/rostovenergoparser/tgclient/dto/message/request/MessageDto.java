package org.rostovenergoparser.tgclient.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class MessageDto extends MessageBodyDto {

    @JsonProperty("chat_id")
    private String chatId;

    public MessageDto(String chatId, MessageBodyDto body) {
        super(body.toBuilder());
        this.chatId = chatId;
    }
}
