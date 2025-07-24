package org.rostovenergoparser.persistence.repository;

import org.rostovenergoparser.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {

}
