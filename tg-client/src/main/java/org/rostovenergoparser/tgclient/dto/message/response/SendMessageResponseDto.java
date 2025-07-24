package org.rostovenergoparser.tgclient.dto.message.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponseDto {
    private boolean ok;
    private String messageId;

    @JsonProperty("result")
    private void unpackMessageId(Map<String, Object> result) {
        this.messageId = result.get("message_id").toString();
    }

}
