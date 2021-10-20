package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ExamPaperAnswerDef;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ExamPaperAnswerDef}.
 */
public interface ExamPaperAnswerDefService {
  /**
   * Save a examPaperAnswerDef.
   *
   * @param examPaperAnswerDef the entity to save.
   * @return the persisted entity.
   */
  ExamPaperAnswerDef save(ExamPaperAnswerDef examPaperAnswerDef);

  /**
   * Partially updates a examPaperAnswerDef.
   *
   * @param examPaperAnswerDef the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ExamPaperAnswerDef> partialUpdate(
    ExamPaperAnswerDef examPaperAnswerDef
  );

  /**
   * Get all the examPaperAnswerDefs.
   *
   * @return the list of entities.
   */
  List<ExamPaperAnswerDef> findAll();

  /**
   * Get the "id" examPaperAnswerDef.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ExamPaperAnswerDef> findOne(Long id);

  /**
   * Delete the "id" examPaperAnswerDef.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
