package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.ExamPaper;
import io.github.jhipster.application.repository.ExamPaperRepository;
import io.github.jhipster.application.service.ExamPaperService;
import io.github.jhipster.application.service.dto.ExamPaper;
import io.github.jhipster.application.service.mapper.ExamPaperMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExamPaper}.
 */
@Service
@Transactional
public class ExamPaperServiceImpl implements ExamPaperService {

  private final Logger log = LoggerFactory.getLogger(
    ExamPaperServiceImpl.class
  );

  private final ExamPaperRepository examPaperRepository;

  private final ExamPaperMapper examPaperMapper;

  public ExamPaperServiceImpl(
    ExamPaperRepository examPaperRepository,
    ExamPaperMapper examPaperMapper
  ) {
    this.examPaperRepository = examPaperRepository;
    this.examPaperMapper = examPaperMapper;
  }

  @Override
  public ExamPaper save(ExamPaper examPaper) {
    log.debug("Request to save ExamPaper : {}", examPaper);
    ExamPaper examPaper = examPaperMapper.toEntity(examPaper);
    examPaper = examPaperRepository.save(examPaper);
    return examPaperMapper.toDto(examPaper);
  }

  @Override
  public Optional<ExamPaper> partialUpdate(ExamPaper examPaper) {
    log.debug("Request to partially update ExamPaper : {}", examPaper);

    return examPaperRepository
      .findById(examPaper.getId())
      .map(existingExamPaper -> {
        examPaperMapper.partialUpdate(existingExamPaper, examPaper);

        return existingExamPaper;
      })
      .map(examPaperRepository::save)
      .map(examPaperMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ExamPaper> findAll(Pageable pageable) {
    log.debug("Request to get all ExamPapers");
    return examPaperRepository.findAll(pageable).map(examPaperMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ExamPaper> findOne(Long id) {
    log.debug("Request to get ExamPaper : {}", id);
    return examPaperRepository.findById(id).map(examPaperMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ExamPaper : {}", id);
    examPaperRepository.deleteById(id);
  }
}
