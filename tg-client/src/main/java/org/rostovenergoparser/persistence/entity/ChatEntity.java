package org.rostovenergoparser.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.rostovenergoparser.dialogstatemachine.dto.DialogStateMachineContext;

@Entity
@Data
@Table(name = "chat")
public class ChatEntity {

    @Id
    @Column(name = "chat_id", nullable = false, unique = true)
    @OneToMany(mappedBy = "chatEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "context")
    private DialogStateMachineContext context;
}
