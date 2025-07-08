package org.rostovenergoparser.tgclient.dto.send;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@SuperBuilder(toBuilder = true)
public class BotResponseContentDto {

    private String text;

    @SerializedName(value = "reply_markup")
    private InlineKeyboard replyMarkup;

    @Setter
    @Builder
    public static class InlineKeyboard {
        @Singular("keyboardRow")
        @SerializedName(value = "inline_keyboard")
        private List<List<InlineKeyboardButton>> inlineKeyboard;
    }

    @Builder
    public static class InlineKeyboardButton {
        private String text;
        @SerializedName("callback_data")
        private String callbackData;
    }
}
