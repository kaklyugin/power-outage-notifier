package org.rostovenergoparser.dialogstatemachine.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.rostovenergoparser.dialogstatemachine.enums.DialogQuestionCodes;
import org.rostovenergoparser.dialogstatemachine.enums.DialogStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class DialogStateMachineContext {
    private Long chatId;
    private DialogStatus dialogStatus;
    private List<DialogQuestionAnswer> dialogQuestionsAnswers;

    public DialogStateMachineContext() {
        this.dialogQuestionsAnswers = new ArrayList<>();
    }

    public void addUserCallbackAnswer(Long messageIdOfInitialQuestion, String userResponse) {
        DialogQuestionAnswer qa = findQuestionByInitialMessageId(messageIdOfInitialQuestion);
        if (qa == null)
        {
            log.error("Could not find question with id {}", messageIdOfInitialQuestion);
            return;
        }
        qa.setAnswer(userResponse);
    }

    public void addUserTextAnswer(DialogQuestionCodes questionCode, String userResponse) {
        DialogQuestionAnswer qa = findQuestionByCode(questionCode);
        qa.setAnswer(userResponse);
    }

    public void addQuestion(Long messageId, DialogQuestionCodes questionCode) {
        var userResponse = new DialogQuestionAnswer(messageId, questionCode);
        dialogQuestionsAnswers.add(userResponse);
    }

    public DialogQuestionAnswer findQuestionByInitialMessageId(Long requestMessageId) {
        return dialogQuestionsAnswers.stream().filter(cart ->
                        cart.getQuestionMessageId().equals(requestMessageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find question with id " + requestMessageId));
    }

    public DialogQuestionAnswer findQuestionByCode(DialogQuestionCodes questionCode) {
        return dialogQuestionsAnswers.stream().filter(cart ->
                        cart.getQuestionCode().equals(questionCode))
                .findFirst()
                .orElse(null);
    }
}
