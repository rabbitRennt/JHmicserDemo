package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MessageUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessageUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageUserRepository
  extends JpaRepository<MessageUser, Long> {}
