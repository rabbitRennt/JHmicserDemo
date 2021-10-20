package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Question;
import io.github.jhipster.application.repository.QuestionRepository;
import io.github.jhipster.application.service.dto.Question;
import io.github.jhipster.application.service.mapper.QuestionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Question}.
 */
@Service
@Transactional
public class QuestionService {

  private final Logger log = LoggerFactory.getLogger(QuestionService.class);

  private final QuestionRepository questionRepository;

  private final QuestionMapper questionMapper;

  public QuestionService(
    QuestionRepository questionRepository,
    QuestionMapper questionMapper
  ) {
    this.questionRepository = questionRepository;
    this.questionMapper = questionMapper;
  }

  /**
   * Save a question.
   *
   * @param question the entity to save.
   * @return the persisted entity.
   */
  public Question save(Question question) {
    log.debug("Request to save Question : {}", question);
    Question question = questionMapper.toEntity(question);
    question = questionRepository.save(question);
    return questionMapper.toDto(question);
  }

  /**
   * Partially update a question.
   *
   * @param question the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<Question> partialUpdate(Question question) {
    log.debug("Request to partially update Question : {}", question);

    return questionRepository
      .findById(question.getId())
      .map(existingQuestion -> {
        questionMapper.partialUpdate(existingQuestion, question);

        return existingQuestion;
      })
      .map(questionRepository::save)
      .map(questionMapper::toDto);
  }

  /**
   * Get all the questions.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<Question> findAll(Pageable pageable) {
    log.debug("Request to get all Questions");
    return questionRepository.findAll(pageable).map(questionMapper::toDto);
  }

  /**
   * Get one question by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<Question> findOne(Long id) {
    log.debug("Request to get Question : {}", id);
    return questionRepository.findById(id).map(questionMapper::toDto);
  }

  /**
   * Delete the question by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Question : {}", id);
    questionRepository.deleteById(id);
  }
}
