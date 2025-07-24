package org.rostovenergoparser.tgclient.storage;

import org.rostovenergoparser.tgclient.dto.updates.UpdateResponseDto;

public interface ChatStore {
    void pushUpdate(Long chatId, Long updateId, UpdateResponseDto message);
    boolean checkUpdateExists(Long chatId, Long updateId);
}
