package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TaskExam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskExam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskExamRepository extends JpaRepository<TaskExam, Long> {}
