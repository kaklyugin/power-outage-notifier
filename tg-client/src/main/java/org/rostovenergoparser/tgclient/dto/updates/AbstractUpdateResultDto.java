package org.rostovenergoparser.tgclient.dto.updates;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;


@Getter
public abstract class  AbstractUpdateResultDto {
    @SerializedName(value = "update_id")
    private Long updateId;

    public AbstractUpdateResultDto(Long updateId) {
        this.updateId = updateId;
    }

    public abstract String getUserResponse();

    public abstract String getResponseType();

    public abstract Chat getChat();

    public abstract From getForm();
}
