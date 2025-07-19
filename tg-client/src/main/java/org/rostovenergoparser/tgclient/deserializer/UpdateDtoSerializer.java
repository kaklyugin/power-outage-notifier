package org.rostovenergoparser.tgclient.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.callback.CallbackQueryResultDto;
import org.rostovenergoparser.tgclient.dto.updates.message.MessageUpdateResultDto;

import java.io.IOException;

public class UpdateDtoSerializer extends JsonSerializer<AbstractUpdateResultDto> {

    @Override
    public void serialize(AbstractUpdateResultDto value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("update_id", value.getUpdateId());

        if (value instanceof MessageUpdateResultDto) {
            MessageUpdateResultDto messageResult = (MessageUpdateResultDto) value;
            gen.writeObjectField("message", messageResult.getMessage());
        } else if (value instanceof CallbackQueryResultDto) {
            CallbackQueryResultDto callbackResult = (CallbackQueryResultDto) value;
            gen.writeObjectField("callback_query", callbackResult.getCallbackQuery());
        }

        gen.writeEndObject();
    }
}