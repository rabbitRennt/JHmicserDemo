package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.ExamPaperAnswerDef;
import io.github.jhipster.application.repository.ExamPaperAnswerDefRepository;
import io.github.jhipster.application.service.ExamPaperAnswerDefService;
import io.github.jhipster.application.service.dto.ExamPaperAnswerDef;
import io.github.jhipster.application.service.mapper.ExamPaperAnswerDefMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExamPaperAnswerDef}.
 */
@Service
@Transactional
public class ExamPaperAnswerDefServiceImpl
  implements ExamPaperAnswerDefService {

  private final Logger log = LoggerFactory.getLogger(
    ExamPaperAnswerDefServiceImpl.class
  );

  private final ExamPaperAnswerDefRepository examPaperAnswerDefRepository;

  private final ExamPaperAnswerDefMapper examPaperAnswerDefMapper;

  public ExamPaperAnswerDefServiceImpl(
    ExamPaperAnswerDefRepository examPaperAnswerDefRepository,
    ExamPaperAnswerDefMapper examPaperAnswerDefMapper
  ) {
    this.examPaperAnswerDefRepository = examPaperAnswerDefRepository;
    this.examPaperAnswerDefMapper = examPaperAnswerDefMapper;
  }

  @Override
  public ExamPaperAnswerDef save(ExamPaperAnswerDef examPaperAnswerDef) {
    log.debug("Request to save ExamPaperAnswerDef : {}", examPaperAnswerDef);
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toEntity(
      examPaperAnswerDef
    );
    examPaperAnswerDef = examPaperAnswerDefRepository.save(examPaperAnswerDef);
    return examPaperAnswerDefMapper.toDto(examPaperAnswerDef);
  }

  @Override
  public Optional<ExamPaperAnswerDef> partialUpdate(
    ExamPaperAnswerDef examPaperAnswerDef
  ) {
    log.debug(
      "Request to partially update ExamPaperAnswerDef : {}",
      examPaperAnswerDef
    );

    return examPaperAnswerDefRepository
      .findById(examPaperAnswerDef.getId())
      .map(existingExamPaperAnswerDef -> {
        examPaperAnswerDefMapper.partialUpdate(
          existingExamPaperAnswerDef,
          examPaperAnswerDef
        );

        return existingExamPaperAnswerDef;
      })
      .map(examPaperAnswerDefRepository::save)
      .map(examPaperAnswerDefMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ExamPaperAnswerDef> findAll() {
    log.debug("Request to get all ExamPaperAnswerDefs");
    return examPaperAnswerDefRepository
      .findAll()
      .stream()
      .map(examPaperAnswerDefMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ExamPaperAnswerDef> findOne(Long id) {
    log.debug("Request to get ExamPaperAnswerDef : {}", id);
    return examPaperAnswerDefRepository
      .findById(id)
      .map(examPaperAnswerDefMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ExamPaperAnswerDef : {}", id);
    examPaperAnswerDefRepository.deleteById(id);
  }
}
