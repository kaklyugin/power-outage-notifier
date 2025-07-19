package org.rostovenergoparser.tgclient.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.callback.CallbackQueryDto;
import org.rostovenergoparser.tgclient.dto.updates.callback.CallbackQueryResultDto;
import org.rostovenergoparser.tgclient.dto.updates.message.MessageUpdateDto;
import org.rostovenergoparser.tgclient.dto.updates.message.MessageUpdateResultDto;

import java.io.IOException;


public class AbstractUpdateResultDtoDeserializer extends StdDeserializer<AbstractUpdateResultDto> {

    public AbstractUpdateResultDtoDeserializer() {
        this(null);
    }

    public AbstractUpdateResultDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AbstractUpdateResultDto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);
        long updateId = node.get("update_id").asLong();

        if (node.has("message")) {
            MessageUpdateDto messageDto = jp.getCodec().treeToValue(node.get("message"), MessageUpdateDto.class);
            return new MessageUpdateResultDto(updateId, messageDto);
        } else if (node.has("callback_query")) {
            CallbackQueryDto callbackQueryDto = jp.getCodec().treeToValue(node.get("callback_query"), CallbackQueryDto.class);
            return new CallbackQueryResultDto(updateId, callbackQueryDto);
        }

        throw new IOException("Update must contain either 'message' or 'callback_query'");
    }
}