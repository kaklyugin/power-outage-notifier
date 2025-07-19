package org.rostovenergoparser.tgclient.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.rostovenergoparser.tgclient.dto.updates.UpdateDto;
import org.rostovenergoparser.tgclient.dto.updates.UpdateType;


import java.io.IOException;
import java.util.stream.StreamSupport;


public class UpdateDtoDeserializer extends StdDeserializer<UpdateDto> {

    public UpdateDtoDeserializer() {
        this(null);
    }

    public UpdateDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UpdateDto deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        UpdateDto resultUpdateDto = new UpdateDto();
        JsonNode node = jp.getCodec().readTree(jp);
        resultUpdateDto.setUpdateId(node.get("update_id").asLong());

        if (node.has("text") && !node.has("entities")) {
            resultUpdateDto.setUpdateType(UpdateType.TEXT);
            resultUpdateDto.setFromId(node.at("/callback_query/message/from/id").asLong());
            resultUpdateDto.setChatId(node.at("/callback_query/message/chat/id").asLong());
            resultUpdateDto.setDate(node.at("/callback_query/message/date").asLong());
            resultUpdateDto.setUserReply(node.at("/callback_query/data").asText());
        } else if (node.has("callback_query")) {
            resultUpdateDto.setUpdateType(UpdateType.CALLBACK);
            resultUpdateDto.setFromId(node.at("/callback_query/message/from/id").asLong());
            resultUpdateDto.setChatId(node.at("/callback_query/message/chat/id").asLong());
            resultUpdateDto.setDate(node.at("/callback_query/message/date").asLong());
            resultUpdateDto.setUserReply(node.at("/callback_query/data").asText());
        } else if (node.has("entities")) {
            JsonNode entity = StreamSupport
                    .stream(node.get("entities").spliterator(), false)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Entities are empty"));
            if (entity.get("type").asText().equals("bot_command")) {
                resultUpdateDto.setUpdateType(UpdateType.COMMAND);
                resultUpdateDto.setFromId(node.at("/callback_query/message/from/id").asLong());
                resultUpdateDto.setChatId(node.at("/callback_query/message/chat/id").asLong());
                resultUpdateDto.setDate(node.at("/callback_query/message/date").asLong());
                resultUpdateDto.setUserReply(node.at("/callback_query/data").asText());
            } else
                throw new JsonParseException("Cannot define update type");
        }
        return resultUpdateDto;
    }
}