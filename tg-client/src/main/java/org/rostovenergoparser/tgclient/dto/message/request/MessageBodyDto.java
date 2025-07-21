package org.rostovenergoparser.tgclient.dto.message.request;  //TODO переименовать

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.rostovenergoparser.tgclient.dto.message.request.keyboard.InlineKeyboardDto;

import java.util.List;

@Setter
@SuperBuilder(toBuilder = true)
public class MessageBodyDto {

    private String text;

    @JsonProperty("reply_markup")
    private InlineKeyboardDto replyMarkup;
}
