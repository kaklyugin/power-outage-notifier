package org.rostovenergoparser.tgclient.dto.message.request.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InlineKeyboardButtonDto {
    private String text;
    @JsonProperty("callback_data")
    private String callbackData;
}

