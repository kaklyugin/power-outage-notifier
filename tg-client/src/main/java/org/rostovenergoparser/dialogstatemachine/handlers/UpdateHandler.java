package org.rostovenergoparser.dialogstatemachine.handlers;

import org.rostovenergoparser.dto.UpdateDto;

public interface UpdateHandler {
    String handleUpdate(UpdateDto message);
}
