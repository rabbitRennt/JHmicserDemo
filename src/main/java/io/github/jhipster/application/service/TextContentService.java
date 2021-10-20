package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TextContent;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TextContent}.
 */
public interface TextContentService {
  /**
   * Save a textContent.
   *
   * @param textContent the entity to save.
   * @return the persisted entity.
   */
  TextContent save(TextContent textContent);

  /**
   * Partially updates a textContent.
   *
   * @param textContent the entity to update partially.
   * @return the persisted entity.
   */
  Optional<TextContent> partialUpdate(TextContent textContent);

  /**
   * Get all the textContents.
   *
   * @return the list of entities.
   */
  List<TextContent> findAll();

  /**
   * Get the "id" textContent.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<TextContent> findOne(Long id);

  /**
   * Delete the "id" textContent.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
