package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.TaskExamAnswer;
import io.github.jhipster.application.repository.TaskExamAnswerRepository;
import io.github.jhipster.application.service.TaskExamAnswerService;
import io.github.jhipster.application.service.dto.TaskExamAnswer;
import io.github.jhipster.application.service.mapper.TaskExamAnswerMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskExamAnswer}.
 */
@Service
@Transactional
public class TaskExamAnswerServiceImpl implements TaskExamAnswerService {

  private final Logger log = LoggerFactory.getLogger(
    TaskExamAnswerServiceImpl.class
  );

  private final TaskExamAnswerRepository taskExamAnswerRepository;

  private final TaskExamAnswerMapper taskExamAnswerMapper;

  public TaskExamAnswerServiceImpl(
    TaskExamAnswerRepository taskExamAnswerRepository,
    TaskExamAnswerMapper taskExamAnswerMapper
  ) {
    this.taskExamAnswerRepository = taskExamAnswerRepository;
    this.taskExamAnswerMapper = taskExamAnswerMapper;
  }

  @Override
  public TaskExamAnswer save(TaskExamAnswer taskExamAnswer) {
    log.debug("Request to save TaskExamAnswer : {}", taskExamAnswer);
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toEntity(
      taskExamAnswer
    );
    taskExamAnswer = taskExamAnswerRepository.save(taskExamAnswer);
    return taskExamAnswerMapper.toDto(taskExamAnswer);
  }

  @Override
  public Optional<TaskExamAnswer> partialUpdate(TaskExamAnswer taskExamAnswer) {
    log.debug(
      "Request to partially update TaskExamAnswer : {}",
      taskExamAnswer
    );

    return taskExamAnswerRepository
      .findById(taskExamAnswer.getId())
      .map(existingTaskExamAnswer -> {
        taskExamAnswerMapper.partialUpdate(
          existingTaskExamAnswer,
          taskExamAnswer
        );

        return existingTaskExamAnswer;
      })
      .map(taskExamAnswerRepository::save)
      .map(taskExamAnswerMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TaskExamAnswer> findAll() {
    log.debug("Request to get all TaskExamAnswers");
    return taskExamAnswerRepository
      .findAll()
      .stream()
      .map(taskExamAnswerMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TaskExamAnswer> findOne(Long id) {
    log.debug("Request to get TaskExamAnswer : {}", id);
    return taskExamAnswerRepository
      .findById(id)
      .map(taskExamAnswerMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete TaskExamAnswer : {}", id);
    taskExamAnswerRepository.deleteById(id);
  }
}
