package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExamPaperAnswer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamPaperAnswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamPaperAnswerRepository
  extends JpaRepository<ExamPaperAnswer, Long> {}
