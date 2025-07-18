package org.rostovenergoparser.bot.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

@Slf4j
public class StartMessageHandler implements UpdateHandler {
    @Override
    public String handleUpdate(AbstractUpdateResultDto message) {
        log.info("Handling message = {}",message);
        return message.getUserResponse();
    }
}
