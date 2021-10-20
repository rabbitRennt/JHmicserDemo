package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.TaskExam;
import io.github.jhipster.application.repository.TaskExamRepository;
import io.github.jhipster.application.service.TaskExamService;
import io.github.jhipster.application.service.dto.TaskExam;
import io.github.jhipster.application.service.mapper.TaskExamMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskExam}.
 */
@Service
@Transactional
public class TaskExamServiceImpl implements TaskExamService {

  private final Logger log = LoggerFactory.getLogger(TaskExamServiceImpl.class);

  private final TaskExamRepository taskExamRepository;

  private final TaskExamMapper taskExamMapper;

  public TaskExamServiceImpl(
    TaskExamRepository taskExamRepository,
    TaskExamMapper taskExamMapper
  ) {
    this.taskExamRepository = taskExamRepository;
    this.taskExamMapper = taskExamMapper;
  }

  @Override
  public TaskExam save(TaskExam taskExam) {
    log.debug("Request to save TaskExam : {}", taskExam);
    TaskExam taskExam = taskExamMapper.toEntity(taskExam);
    taskExam = taskExamRepository.save(taskExam);
    return taskExamMapper.toDto(taskExam);
  }

  @Override
  public Optional<TaskExam> partialUpdate(TaskExam taskExam) {
    log.debug("Request to partially update TaskExam : {}", taskExam);

    return taskExamRepository
      .findById(taskExam.getId())
      .map(existingTaskExam -> {
        taskExamMapper.partialUpdate(existingTaskExam, taskExam);

        return existingTaskExam;
      })
      .map(taskExamRepository::save)
      .map(taskExamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TaskExam> findAll(Pageable pageable) {
    log.debug("Request to get all TaskExams");
    return taskExamRepository.findAll(pageable).map(taskExamMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TaskExam> findOne(Long id) {
    log.debug("Request to get TaskExam : {}", id);
    return taskExamRepository.findById(id).map(taskExamMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete TaskExam : {}", id);
    taskExamRepository.deleteById(id);
  }
}
