package org.rostovenergoparser.tgclient.rabbit;

public interface BrokerMessageKeyStore {
    void push(String key);
    boolean checkExists(String key);
}
