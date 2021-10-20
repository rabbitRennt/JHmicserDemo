package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExamPaper;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamPaper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamPaperRepository extends JpaRepository<ExamPaper, Long> {}
