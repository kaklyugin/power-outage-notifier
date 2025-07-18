package org.rostovenergoparser.bot.dialogstatemachine.dto;

import lombok.Data;

@Data
public class UserReplies {

    private Long chatId;
    private String city;
    private String street;

}
