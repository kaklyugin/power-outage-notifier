package org.rostovenergoparser.tgclient.dto.updates;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.List;

@Getter
public class TelegramResponseAsJsonStringsDto {

    private boolean ok;
    private List<AbstractUpdateResultDto> result;

}
