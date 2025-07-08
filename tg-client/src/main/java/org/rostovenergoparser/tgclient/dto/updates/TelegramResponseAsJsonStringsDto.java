package org.rostovenergoparser.tgclient.dto.updates;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class TelegramResponseAsJsonStringsDto {

    private boolean ok;
    @SerializedName("result")
    private List<JsonObject> rawJsonList;

}
