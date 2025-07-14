package org.rostovenergoparser.bot.dialogstatemachine.cache;

import javax.swing.plaf.nimbus.State;

public interface StateContextStorage {
    State getCurrnetState(int chatId);
    State setCurrnetState(int chatId, State state);
}
