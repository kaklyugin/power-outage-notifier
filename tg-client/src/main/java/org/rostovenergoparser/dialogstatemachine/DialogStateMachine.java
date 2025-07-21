package org.rostovenergoparser.dialogstatemachine;

import lombok.Getter;
import org.rostovenergoparser.dialogstatemachine.dto.DialogStateMachineContext;
import org.rostovenergoparser.dialogstatemachine.enums.DialogStatus;
import org.rostovenergoparser.dialogstatemachine.handlers.CitySelectUpdateHandler;
import org.rostovenergoparser.dialogstatemachine.handlers.StartMessageHandler;
import org.rostovenergoparser.dialogstatemachine.handlers.StopMessageHandler;
import org.rostovenergoparser.dialogstatemachine.handlers.StreetInputUpdateHandler;
import org.rostovenergoparser.dto.UpdateDto;
import org.rostovenergoparser.tgclient.dto.updates.UpdateType;

public class DialogStateMachine {

    @Getter
    private final DialogStateMachineContext context;

    public DialogStateMachine(UpdateDto update) {
        this.context = new DialogStateMachineContext();
        this.context.setChatId(update.getChatId());
        this.context.setDialogStatus(DialogStatus.NEW);
        this.context.setLastUpdate(update);
    }

    public DialogStateMachine(DialogStateMachineContext context) {
        this.context = context;
    }

    public void handle(UpdateDto update) {
        switch (update.getUpdateType()) {
            case UpdateType.COMMAND -> handleCommand(update);
            case UpdateType.TEXT ->  handleReply(update);
            case UpdateType.CALLBACK -> handleReply(update);
            default -> throw new RuntimeException("Unhandled response type: " + update.getUpdateType());
        }
    }

    private void handleCommand(UpdateDto update) {
        switch (update.getUserResponse()) {
            case "/start" -> {
                var handler = new StartMessageHandler();
                handler.handleUpdate(update);
                this.context.setDialogStatus(DialogStatus.WAITING_FOR_CITY_INPUT);
            }
            case "/stop" -> {
                var handler = new StopMessageHandler();
                handler.handleUpdate(update);
                this.context.setDialogStatus(DialogStatus.STOPPED);
            }
        }
    }

    private void handleReply(UpdateDto update) {
        switch (this.getContext().getDialogStatus()) {
            case WAITING_FOR_CITY_INPUT -> {
                var handler = new CitySelectUpdateHandler();
                this.context.getUserResponseCart().setCity(handler.handleUpdate(update));
                this.context.setDialogStatus(DialogStatus.WAITING_FOR_STREET_INPUT);
            }
            case WAITING_FOR_STREET_INPUT -> {
                var handler = new StreetInputUpdateHandler();
                this.context.getUserResponseCart().setStreet(handler.handleUpdate(update));
                this.context.setDialogStatus(DialogStatus.DONE);
            }
        }
    }
}
