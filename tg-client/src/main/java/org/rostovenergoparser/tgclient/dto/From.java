package org.rostovenergoparser.tgclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class From {
    private Long id;
    @JsonProperty("is_bot")
    private boolean isBot;
}
