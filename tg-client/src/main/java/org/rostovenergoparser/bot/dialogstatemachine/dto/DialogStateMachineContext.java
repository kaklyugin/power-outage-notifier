package org.rostovenergoparser.bot.dialogstatemachine.dto;

import lombok.Data;
import org.rostovenergoparser.bot.dialogstatemachine.enums.DialogStatus;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

@Data
public class DialogStateMachineContext {
    private Long chatId;
    private DialogStatus dialogStatus;
    private UserReplies userReplies;
    private AbstractUpdateResultDto lastUpdate;
}
