package org.rostovenergoparser.tgclient.rabbit;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class BrokerMessageInMemoryKeyStore implements BrokerMessageKeyStore {
   private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(36, TimeUnit.HOURS)
            .build();

    @Override
    public void push(String key) {
        cache.put(key, ZonedDateTime.now().toString());
    }

    @Override
    public boolean checkExists(String key) {
        return cache.get(key, k -> null) != null;
    }
}
