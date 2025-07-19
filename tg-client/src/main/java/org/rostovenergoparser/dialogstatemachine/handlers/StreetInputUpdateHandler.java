package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dto.UpdateDto;

@Slf4j
public class StreetInputUpdateHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto message) {
        log.info("Handling message = {}", message);
        log.info("User has input street = {}", message.getUserResponse());
        return message.getUserResponse();
    }
}
