package org.rostovenergoparser.tgclient.dto.updates.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.MessageType;

@Data
public class CallbackQueryResultDto extends AbstractUpdateResultDto {

    @JsonProperty("callback_query")
    private final CallbackQueryDto callbackQuery;

    public CallbackQueryResultDto(Long updateId, CallbackQueryDto callbackQuery) {
        super(updateId);
        this.callbackQuery = callbackQuery;
    }

    @Override
    public String getUserResponse() {
        return this.getCallbackQuery().getData();
    }

    @Override
    public MessageType getResponseType() {
        return MessageType.callback;
    }

    @Override
    public Chat getChat() {
        return callbackQuery.getChat();
    }

}