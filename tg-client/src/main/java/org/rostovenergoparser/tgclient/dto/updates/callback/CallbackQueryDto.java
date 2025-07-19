package org.rostovenergoparser.tgclient.dto.updates.callback;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.rostovenergoparser.tgclient.dto.Chat;
import org.rostovenergoparser.tgclient.dto.From;


@Data
public class CallbackQueryDto {
    private String id;
    private String data;
    private From from;
    private Chat chat;
}
