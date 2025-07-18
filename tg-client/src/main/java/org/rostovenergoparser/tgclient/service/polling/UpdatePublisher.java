package org.rostovenergoparser.tgclient.service.polling;

import org.rostovenergoparser.tgclient.dto.updates.AbstractUpdateResultDto;

public interface UpdatePublisher {
    void publishUpdateForProcessing(AbstractUpdateResultDto message);
}
