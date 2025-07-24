package org.rostovenergoparser.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_cart")
public class UserCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private ChatEntity chatEntity;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;
// TODO
// @Column(name = "last_notified_at")
// private ZonedDateTime lastNotifiedAt;
}
