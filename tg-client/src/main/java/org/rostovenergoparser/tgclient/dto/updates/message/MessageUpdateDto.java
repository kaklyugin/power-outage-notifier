package org.rostovenergoparser.tgclient.dto.updates.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;

import java.util.ArrayList;
import java.util.List;


@Data
public class MessageUpdateDto {
    @JsonProperty("message_id")
    private Integer messageId;
    private String text;
    private From from;
    private Chat chat;
    private final List<CommandEntity> entities = new ArrayList<>();
}
