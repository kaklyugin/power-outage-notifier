package org.rostovenergoparser.bot.dialogstatemachine.dto;

import lombok.Data;
import org.rostovenergoparser.bot.dialogstatemachine.enums.DialogStatus;
import org.rostovenergoparser.dto.UpdateDto;

@Data
public class DialogStateMachineContext {
    private Long chatId;
    private DialogStatus dialogStatus;
    private UserResponseCart userResponseCart;
    private UpdateDto lastUpdate;
}
