package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TaskExam;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TaskExam}.
 */
public interface TaskExamService {
  /**
   * Save a taskExam.
   *
   * @param taskExam the entity to save.
   * @return the persisted entity.
   */
  TaskExam save(TaskExam taskExam);

  /**
   * Partially updates a taskExam.
   *
   * @param taskExam the entity to update partially.
   * @return the persisted entity.
   */
  Optional<TaskExam> partialUpdate(TaskExam taskExam);

  /**
   * Get all the taskExams.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<TaskExam> findAll(Pageable pageable);

  /**
   * Get the "id" taskExam.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<TaskExam> findOne(Long id);

  /**
   * Delete the "id" taskExam.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
