package org.rostovenergoparser.tgclient.dto.updates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;


@Data
@ToString
public abstract class AbstractUpdateResultDto {
    @JsonProperty("update_id")
    private Long updateId;

    public AbstractUpdateResultDto(Long updateId) {
        this.updateId = updateId;
    }

    public abstract String getUserResponse();

    public abstract MessageType getResponseType();

    public abstract Chat getChat();

    public abstract From getForm();
}
