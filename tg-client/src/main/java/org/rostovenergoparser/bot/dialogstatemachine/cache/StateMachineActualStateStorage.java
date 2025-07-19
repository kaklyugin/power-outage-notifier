package org.rostovenergoparser.bot.dialogstatemachine.cache;

import org.rostovenergoparser.bot.dialogstatemachine.dto.DialogStateMachineContext;

import javax.swing.plaf.nimbus.State;

public interface StateMachineActualStateStorage {
    State getContext(Long chatId);

    State pushContext(Long chatId, DialogStateMachineContext dialogStateMachineContext);
}
