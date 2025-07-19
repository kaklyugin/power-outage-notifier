package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dto.UpdateDto;

@Slf4j
public class StartMessageHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto message) {
        log.info("Handling message = {}", message);
        return message.getUserResponse();
    }
}
