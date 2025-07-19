package org.rostovenergoparser.bot.dialogstatemachine;

import lombok.Getter;
import org.rostovenergoparser.bot.dialogstatemachine.dto.DialogStateMachineContext;
import org.rostovenergoparser.bot.dialogstatemachine.enums.DialogStatus;
import org.rostovenergoparser.bot.dialogstatemachine.handlers.CitySelectUpdateHandler;
import org.rostovenergoparser.bot.dialogstatemachine.handlers.StartMessageHandler;
import org.rostovenergoparser.bot.dialogstatemachine.handlers.StopMessageHandler;
import org.rostovenergoparser.bot.dialogstatemachine.handlers.StreetInputUpdateHandler;
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
            case UpdateType.TEXT, UpdateType.CALLBACK -> handleReply(update);
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
            default -> throw new RuntimeException("Unhandled command: " + update.getUserResponse());
        }
    }

    private void handleReply(UpdateDto update) {
        switch (this.getContext().getDialogStatus()) {
            case WAITING_FOR_CITY_INPUT -> {
                var handler = new CitySelectUpdateHandler();
               // this.context.getUserReplies().setCity(handler.handleUpdate(update));
                this.context.setDialogStatus(DialogStatus.WAITING_FOR_STREET_INPUT);
            }
            case WAITING_FOR_STREET_INPUT -> {
                var handler = new StreetInputUpdateHandler();
              //  this.context.getUserReplies().setStreet(handler.handleUpdate(update));
                this.context.setDialogStatus(DialogStatus.DONE);
            }
        }
    }
}
