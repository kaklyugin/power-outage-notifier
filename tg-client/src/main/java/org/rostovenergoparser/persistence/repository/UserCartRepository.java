package org.rostovenergoparser.persistence.repository;

import org.rostovenergoparser.persistence.entity.UserCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCartRepository extends JpaRepository<UserCartEntity,Long> {
}
