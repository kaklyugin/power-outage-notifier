package org.rostovenergoparser.bot.dialogstatemachine.handlers;

import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

public interface UpdateHandler {
    String handleUpdate(AbstractUpdateResultDto message);
}
