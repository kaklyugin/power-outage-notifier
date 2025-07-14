package org.rostovenergoparser.bot.dialogstatemachine;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component //TODO think about Async method call
public class DialogStateMachine {

    private final ConcurrentHashMap<ChatStates, MessageHandler> messageHandlers = new ConcurrentHashMap<>();

    public DialogStateMachine() {
        messageHandlers.put(ChatStates.NEW,new StartMessageHandler());
        messageHandlers.put(ChatStates.WAITING_FOR_CITY_INPUT,new WaitForCitySelectMessageHandler());
    }

    public void handleMessage(ChatStates state, String message)
    {
        messageHandlers.get(state).handle(message);
    }
}
