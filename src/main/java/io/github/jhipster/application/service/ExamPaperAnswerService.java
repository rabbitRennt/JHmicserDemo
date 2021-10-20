package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ExamPaperAnswer;
import io.github.jhipster.application.repository.ExamPaperAnswerRepository;
import io.github.jhipster.application.service.dto.ExamPaperAnswer;
import io.github.jhipster.application.service.mapper.ExamPaperAnswerMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExamPaperAnswer}.
 */
@Service
@Transactional
public class ExamPaperAnswerService {

  private final Logger log = LoggerFactory.getLogger(
    ExamPaperAnswerService.class
  );

  private final ExamPaperAnswerRepository examPaperAnswerRepository;

  private final ExamPaperAnswerMapper examPaperAnswerMapper;

  public ExamPaperAnswerService(
    ExamPaperAnswerRepository examPaperAnswerRepository,
    ExamPaperAnswerMapper examPaperAnswerMapper
  ) {
    this.examPaperAnswerRepository = examPaperAnswerRepository;
    this.examPaperAnswerMapper = examPaperAnswerMapper;
  }

  /**
   * Save a examPaperAnswer.
   *
   * @param examPaperAnswer the entity to save.
   * @return the persisted entity.
   */
  public ExamPaperAnswer save(ExamPaperAnswer examPaperAnswer) {
    log.debug("Request to save ExamPaperAnswer : {}", examPaperAnswer);
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toEntity(
      examPaperAnswer
    );
    examPaperAnswer = examPaperAnswerRepository.save(examPaperAnswer);
    return examPaperAnswerMapper.toDto(examPaperAnswer);
  }

  /**
   * Partially update a examPaperAnswer.
   *
   * @param examPaperAnswer the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<ExamPaperAnswer> partialUpdate(
    ExamPaperAnswer examPaperAnswer
  ) {
    log.debug(
      "Request to partially update ExamPaperAnswer : {}",
      examPaperAnswer
    );

    return examPaperAnswerRepository
      .findById(examPaperAnswer.getId())
      .map(existingExamPaperAnswer -> {
        examPaperAnswerMapper.partialUpdate(
          existingExamPaperAnswer,
          examPaperAnswer
        );

        return existingExamPaperAnswer;
      })
      .map(examPaperAnswerRepository::save)
      .map(examPaperAnswerMapper::toDto);
  }

  /**
   * Get all the examPaperAnswers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<ExamPaperAnswer> findAll(Pageable pageable) {
    log.debug("Request to get all ExamPaperAnswers");
    return examPaperAnswerRepository
      .findAll(pageable)
      .map(examPaperAnswerMapper::toDto);
  }

  /**
   * Get one examPaperAnswer by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<ExamPaperAnswer> findOne(Long id) {
    log.debug("Request to get ExamPaperAnswer : {}", id);
    return examPaperAnswerRepository
      .findById(id)
      .map(examPaperAnswerMapper::toDto);
  }

  /**
   * Delete the examPaperAnswer by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete ExamPaperAnswer : {}", id);
    examPaperAnswerRepository.deleteById(id);
  }
}
