package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Subject;
import io.github.jhipster.application.repository.SubjectRepository;
import io.github.jhipster.application.service.dto.Subject;
import io.github.jhipster.application.service.mapper.SubjectMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Subject}.
 */
@Service
@Transactional
public class SubjectService {

  private final Logger log = LoggerFactory.getLogger(SubjectService.class);

  private final SubjectRepository subjectRepository;

  private final SubjectMapper subjectMapper;

  public SubjectService(
    SubjectRepository subjectRepository,
    SubjectMapper subjectMapper
  ) {
    this.subjectRepository = subjectRepository;
    this.subjectMapper = subjectMapper;
  }

  /**
   * Save a subject.
   *
   * @param subject the entity to save.
   * @return the persisted entity.
   */
  public Subject save(Subject subject) {
    log.debug("Request to save Subject : {}", subject);
    Subject subject = subjectMapper.toEntity(subject);
    subject = subjectRepository.save(subject);
    return subjectMapper.toDto(subject);
  }

  /**
   * Partially update a subject.
   *
   * @param subject the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<Subject> partialUpdate(Subject subject) {
    log.debug("Request to partially update Subject : {}", subject);

    return subjectRepository
      .findById(subject.getId())
      .map(existingSubject -> {
        subjectMapper.partialUpdate(existingSubject, subject);

        return existingSubject;
      })
      .map(subjectRepository::save)
      .map(subjectMapper::toDto);
  }

  /**
   * Get all the subjects.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<Subject> findAll(Pageable pageable) {
    log.debug("Request to get all Subjects");
    return subjectRepository.findAll(pageable).map(subjectMapper::toDto);
  }

  /**
   * Get one subject by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<Subject> findOne(Long id) {
    log.debug("Request to get Subject : {}", id);
    return subjectRepository.findById(id).map(subjectMapper::toDto);
  }

  /**
   * Delete the subject by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Subject : {}", id);
    subjectRepository.deleteById(id);
  }
}
