package org.rostovenergoparser.tgclient.service.http;

import org.rostovenergoparser.tgclient.dto.message.request.MessageDto;
import org.rostovenergoparser.tgclient.dto.message.response.SendMessageResponseDto;

public interface BotClient {
    SendMessageResponseDto sendMessage(MessageDto message);
    String getUpdates();
    void answerCallbackQuery(String callbackQueryId);
}
