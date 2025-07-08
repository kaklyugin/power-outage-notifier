package org.rostovenergoparser.tgclient.dto.updates.message;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;


@Getter
@ToString
public class MessageUpdateDto {
    @SerializedName("message_id")
    private Integer messageId;
    private String text;
    private From from;
    private Chat chat;

    public MessageUpdateDto(Integer messageId) {
        this.messageId = messageId;
    }

}
