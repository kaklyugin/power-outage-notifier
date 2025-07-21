package org.rostovenergoparser.tgclient.dto.message.request.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InlineKeyboardDto {
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButtonDto>> inlineKeyboard = new ArrayList<>();

    InlineKeyboardDto(KeyboardBuilder builder) {
        this.inlineKeyboard = builder.inlineKeyboard
    }

    public static class KeyboardBuilder {
        private List<List<InlineKeyboardButtonDto>> inlineKeyboard = new ArrayList<>();

        public List<List<InlineKeyboardButtonDto>> addKeyBoard() {
            return inlineKeyboard;
        }

        public KeyboardBuilder addRow(List<InlineKeyboardButtonDto> buttons) {
            this.inlineKeyboard.add(buttons);
            return this;
        }


        public InlineKeyboardDto build()
        {
            return new InlineKeyboardDto(this);
        }

    }
}
