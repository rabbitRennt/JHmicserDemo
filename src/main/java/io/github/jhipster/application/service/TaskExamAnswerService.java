package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TaskExamAnswer;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.TaskExamAnswer}.
 */
public interface TaskExamAnswerService {
  /**
   * Save a taskExamAnswer.
   *
   * @param taskExamAnswer the entity to save.
   * @return the persisted entity.
   */
  TaskExamAnswer save(TaskExamAnswer taskExamAnswer);

  /**
   * Partially updates a taskExamAnswer.
   *
   * @param taskExamAnswer the entity to update partially.
   * @return the persisted entity.
   */
  Optional<TaskExamAnswer> partialUpdate(TaskExamAnswer taskExamAnswer);

  /**
   * Get all the taskExamAnswers.
   *
   * @return the list of entities.
   */
  List<TaskExamAnswer> findAll();

  /**
   * Get the "id" taskExamAnswer.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<TaskExamAnswer> findOne(Long id);

  /**
   * Delete the "id" taskExamAnswer.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
