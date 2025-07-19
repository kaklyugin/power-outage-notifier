package org.rostovenergoparser.tgclient.dto.updates;


import lombok.Getter;

import java.util.List;

@Getter
public class TelegramResponseAsJsonStringsDto {

    private boolean ok;
    private List<AbstractUpdateResultDto> result;

}
