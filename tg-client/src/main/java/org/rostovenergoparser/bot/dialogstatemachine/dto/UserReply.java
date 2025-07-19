package org.rostovenergoparser.bot.dialogstatemachine.dto;

import lombok.Data;

@Data
public class UserReply {
    private Long chatId;
    private String city;
    private String street;
}
