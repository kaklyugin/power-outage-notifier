package org.rostovenergoparser.tgclient.dto.updates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private long updateId;
    private UpdateType updateType;
    private long fromId;
    private long chatId;
    private long date;
    private String userReply;
}