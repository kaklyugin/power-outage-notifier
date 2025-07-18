package org.rostovenergoparser.tgclient.storage;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class ChatCaffeineStore implements ChatStore {
    private final Cache<Long, HashMap<Long, AbstractUpdateResultDto>> cache = Caffeine.newBuilder()
            .expireAfterWrite(36, TimeUnit.HOURS)
            .build();
    //TODO Можно упростить - хранить id последнего сообщения
    @Override
    public void pushUpdate(Long chatId, Long updateId, AbstractUpdateResultDto message) {
        HashMap<Long, AbstractUpdateResultDto> updates = cache.get(chatId, list ->
                new HashMap<Long, AbstractUpdateResultDto>() {{
                    put(updateId, message);
                }});
        updates.put(updateId, message);
    }

    @Override
    public boolean checkUpdateExists(Long chatId, Long updateId) {
        HashMap<Long,AbstractUpdateResultDto> updates =  cache.get(chatId, upd ->null);
        return updates != null && updates.containsKey(updateId);

    }
}
