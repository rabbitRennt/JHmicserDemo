package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.ExamPaper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.ExamPaper}.
 */
public interface ExamPaperService {
  /**
   * Save a examPaper.
   *
   * @param examPaper the entity to save.
   * @return the persisted entity.
   */
  ExamPaper save(ExamPaper examPaper);

  /**
   * Partially updates a examPaper.
   *
   * @param examPaper the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ExamPaper> partialUpdate(ExamPaper examPaper);

  /**
   * Get all the examPapers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ExamPaper> findAll(Pageable pageable);

  /**
   * Get the "id" examPaper.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ExamPaper> findOne(Long id);

  /**
   * Delete the "id" examPaper.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
