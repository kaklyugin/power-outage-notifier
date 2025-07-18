package org.rostovenergoparser.bot.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

@Slf4j
public class CitySelectUpdateHandler implements UpdateHandler {
    @Override
    public String handleUpdate(AbstractUpdateResultDto message) {
        log.info("Handling message = {}",message);
        log.info("User has selected = {}",message.getUserResponse());
        return message.getUserResponse();
    }
}
