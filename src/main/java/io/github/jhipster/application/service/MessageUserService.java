package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MessageUser;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.MessageUser}.
 */
public interface MessageUserService {
  /**
   * Save a messageUser.
   *
   * @param messageUser the entity to save.
   * @return the persisted entity.
   */
  MessageUser save(MessageUser messageUser);

  /**
   * Partially updates a messageUser.
   *
   * @param messageUser the entity to update partially.
   * @return the persisted entity.
   */
  Optional<MessageUser> partialUpdate(MessageUser messageUser);

  /**
   * Get all the messageUsers.
   *
   * @return the list of entities.
   */
  List<MessageUser> findAll();

  /**
   * Get the "id" messageUser.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<MessageUser> findOne(Long id);

  /**
   * Delete the "id" messageUser.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
