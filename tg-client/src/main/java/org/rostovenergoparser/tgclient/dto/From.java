package org.rostovenergoparser.tgclient.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class From {
    private Long id;
    @SerializedName("is_bot")
    private boolean is_bot;
}
