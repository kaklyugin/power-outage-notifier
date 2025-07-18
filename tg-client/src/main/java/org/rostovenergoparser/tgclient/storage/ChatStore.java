package org.rostovenergoparser.tgclient.storage;

import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

public interface ChatStore {
    void pushUpdate(Long chatId, Long updateId, AbstractUpdateResultDto message);
    boolean checkUpdateExists(Long chatId, Long updateId);
}
