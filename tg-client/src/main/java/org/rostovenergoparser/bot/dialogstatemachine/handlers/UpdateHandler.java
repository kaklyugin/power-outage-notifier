package org.rostovenergoparser.bot.dialogstatemachine.handlers;

import org.rostovenergoparser.dto.UpdateDto;

public interface UpdateHandler {
    String handleUpdate(UpdateDto message);
}
