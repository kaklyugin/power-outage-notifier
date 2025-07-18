package org.rostovenergoparser.tgclient.dto.updates.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@NoArgsConstructor
public class MessageUpdateDto {
    @JsonProperty("message_id")
    private Integer messageId;
    private String text;
    private From from;
    private Chat chat;
    private List<CommandEntity> entities = new ArrayList<>();

    public MessageUpdateDto(Integer messageId) {
        this.messageId = messageId;
    }

}
