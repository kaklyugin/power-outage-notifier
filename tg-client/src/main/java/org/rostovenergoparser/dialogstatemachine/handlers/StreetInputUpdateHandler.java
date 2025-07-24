package org.rostovenergoparser.dialogstatemachine.handlers;

import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dialogstatemachine.DialogStateMachine;
import org.rostovenergoparser.dto.UpdateDto;

@Slf4j
public class StreetInputUpdateHandler implements UpdateHandler {
    @Override
    public String handleUpdate(UpdateDto update, DialogStateMachine dialogStateMachine) {
        log.info("Handling message = {}", update);
        log.info("User has input street = {}", update.getUserResponse());
        return update.getUserResponse();
    }
}
