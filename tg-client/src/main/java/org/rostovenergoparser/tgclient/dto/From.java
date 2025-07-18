package org.rostovenergoparser.tgclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class From {
    private Long id;
    @JsonProperty("is_bot")
    private boolean is_bot;
}
