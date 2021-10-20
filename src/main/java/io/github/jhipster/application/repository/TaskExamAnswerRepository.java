package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TaskExamAnswer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskExamAnswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskExamAnswerRepository
  extends JpaRepository<TaskExamAnswer, Long> {}
