package org.rostovenergoparser.tgclient.dto.updates;


import lombok.Getter;

import java.util.List;

@Getter
public class TelegramResponseDto {

    private boolean ok;
    private List<AbstractUpdateResultDto> result;

}
