package org.rostovenergoparser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rostovenergoparser.tgclient.dto.updates.UpdateType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private long updateId;
    private UpdateType updateType;
    private String callbackQueryId;
    private long fromId;
    private long chatId;
    private long date;
    private String userResponse;
    private long sourceMessageId;
}
