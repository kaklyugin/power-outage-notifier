package org.rostovenergoparser.tgclient.deserializer;

import com.google.gson.*;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.rostovenergoparser.tgclient.dto.updates.callback.CallbackQueryDto;
import org.rostovenergoparser.tgclient.dto.updates.callback.CallbackQueryResultDto;
import org.rostovenergoparser.tgclient.dto.updates.message.MessageUpdateDto;
import org.rostovenergoparser.tgclient.dto.updates.message.MessageUpdateResultDto;

import java.lang.reflect.Type;

public class UpdateResultAdapter implements JsonDeserializer<AbstractUpdateResultDto> {

    @Override
    public AbstractUpdateResultDto deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        long updateId = jsonObject.get("update_id").getAsLong();

        if (jsonObject.has("message")) {
            MessageUpdateDto messageDto = context.deserialize(jsonObject.get("message"), MessageUpdateDto.class);
            return new MessageUpdateResultDto(updateId, messageDto);
        } else if (jsonObject.has("callback_query")) {
            CallbackQueryDto callbackQueryDto = context.deserialize(jsonObject.get("callback_query"), CallbackQueryDto.class);
            return new CallbackQueryResultDto(updateId, callbackQueryDto);
        }


        throw new JsonParseException("Update must contain either 'message' or 'callback_query'");

//        for (JsonElement jsonElement : jsonUpdate.getAsJsonArray("result")) {
//            var jsonObject = jsonElement.getAsJsonObject();
//            Class<? extends UpdateResultDto> cls = null;
//            if (jsonObject.has("message"))
//                cls = MessageResultDto.class;
//            else if (jsonObject.has("callback_query")) {
//                cls = CallbackQueryResultDto.class;
//            } else throw new JsonParseException("Could not find parsing rule for json = " + json);
//
//            var resultObject = new Gson().fromJson(jsonObject, cls);
//
//            updatesDto.getResult().add(resultObject);
//        }
    }

}
