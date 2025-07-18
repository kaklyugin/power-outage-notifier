package org.rostovenergoparser.tgclient.dto.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class BotResponseMessageDto extends BotResponseContentDto {

    @JsonProperty("chat_id")
    private String chatId;

    public BotResponseMessageDto(String chatId, BotResponseContentDto content) {
        super(content.toBuilder());
        this.chatId = chatId;
    }
}
