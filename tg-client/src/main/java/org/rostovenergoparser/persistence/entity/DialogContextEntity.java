package org.rostovenergoparser.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.rostovenergoparser.bot.dialogstatemachine.dto.DialogStateMachineContext;

@Entity
@Data
@Table(name = "dialog_context")
public class DialogContextEntity {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "context")
    private DialogStateMachineContext context;
}
