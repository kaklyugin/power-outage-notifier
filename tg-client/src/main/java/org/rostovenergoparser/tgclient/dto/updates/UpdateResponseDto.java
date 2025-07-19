package org.rostovenergoparser.tgclient.dto.updates;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rostovenergoparser.jsonmapper.UpdateResponseDtoDeserializer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = UpdateResponseDtoDeserializer.class)
public class UpdateResponseDto {
    private long updateId;
    private UpdateType updateType;
    private long fromId;
    private long chatId;
    private long date;
    private String userResponse;
}

