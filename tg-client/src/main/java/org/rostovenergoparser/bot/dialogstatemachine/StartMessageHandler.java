package org.rostovenergoparser.bot.dialogstatemachine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartMessageHandler implements MessageHandler {
    @Override
    public void handle(String message) {
        log.info("Handling message = {}",message);
    }
}
