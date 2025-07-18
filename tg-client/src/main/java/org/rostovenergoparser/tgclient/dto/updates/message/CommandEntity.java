package org.rostovenergoparser.tgclient.dto.updates.message;

import lombok.Getter;

@Getter
public class CommandEntity {
    private int offset;
    private int length;
    private MessageEntityType type;
}
