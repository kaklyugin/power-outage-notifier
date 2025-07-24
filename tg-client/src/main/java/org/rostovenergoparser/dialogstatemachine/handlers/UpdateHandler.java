package org.rostovenergoparser.dialogstatemachine.handlers;

import org.rostovenergoparser.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dto.UpdateDto;

public interface UpdateHandler {
    String handleUpdate(UpdateDto update, DialogStateMachine dialogStateMachine);
}
