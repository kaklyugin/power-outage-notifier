package org.rostovenergoparser.dialogstatemachine.dto;

import lombok.Data;
import org.rostovenergoparser.dialogstatemachine.enums.DialogQuestionCodes;

@Data
public class DialogQuestionAnswer {
    private DialogQuestionCodes questionCode;
    private Long questionMessageId;
    private String answer;

    public DialogQuestionAnswer(Long questionMessageId, DialogQuestionCodes questionCode) {
        this.questionMessageId = questionMessageId;
        this.questionCode = questionCode;
    }
}
