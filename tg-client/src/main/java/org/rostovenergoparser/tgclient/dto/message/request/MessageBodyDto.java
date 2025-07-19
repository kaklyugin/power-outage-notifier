package org.rostovenergoparser.tgclient.dto.message.request;  //TODO переименовать

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@SuperBuilder(toBuilder = true)
public class MessageBodyDto {

    private String text;

    @JsonProperty("reply_markup")
    private InlineKeyboard replyMarkup;

    //TODO вынести в отдельные классы
    @Setter
    @Builder
    public static class InlineKeyboard {
        @Singular("keyboardRow")
        @JsonProperty("inline_keyboard")
        private List<List<InlineKeyboardButton>> inlineKeyboard;
    }

    @Builder
    public static class InlineKeyboardButton {
        private String text;
        @JsonProperty("callback_data")
        private String callbackData;
    }
}
