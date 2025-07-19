package org.rostovenergoparser.tgclient.dto.updates;


import lombok.Getter;

import java.util.List;

@Getter
public class UpdatesResponseDto {

    private boolean ok;
    private List<UpdateDto> result;

}
