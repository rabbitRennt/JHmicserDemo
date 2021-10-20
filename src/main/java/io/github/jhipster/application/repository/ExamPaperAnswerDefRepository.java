package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExamPaperAnswerDef;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamPaperAnswerDef entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamPaperAnswerDefRepository
  extends JpaRepository<ExamPaperAnswerDef, Long> {}
