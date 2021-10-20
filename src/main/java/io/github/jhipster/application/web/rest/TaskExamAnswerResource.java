package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.TaskExamAnswerRepository;
import io.github.jhipster.application.service.TaskExamAnswerService;
import io.github.jhipster.application.service.dto.TaskExamAnswer;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.TaskExamAnswer}.
 */
@RestController
@RequestMapping("/api")
public class TaskExamAnswerResource {

  private final Logger log = LoggerFactory.getLogger(
    TaskExamAnswerResource.class
  );

  private static final String ENTITY_NAME = "jHmicserDemoTaskExamAnswer";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TaskExamAnswerService taskExamAnswerService;

  private final TaskExamAnswerRepository taskExamAnswerRepository;

  public TaskExamAnswerResource(
    TaskExamAnswerService taskExamAnswerService,
    TaskExamAnswerRepository taskExamAnswerRepository
  ) {
    this.taskExamAnswerService = taskExamAnswerService;
    this.taskExamAnswerRepository = taskExamAnswerRepository;
  }

  /**
   * {@code POST  /task-exam-answers} : Create a new taskExamAnswer.
   *
   * @param taskExamAnswer the taskExamAnswer to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskExamAnswer, or with status {@code 400 (Bad Request)} if the taskExamAnswer has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/task-exam-answers")
  public ResponseEntity<TaskExamAnswer> createTaskExamAnswer(
    @RequestBody TaskExamAnswer taskExamAnswer
  ) throws URISyntaxException {
    log.debug("REST request to save TaskExamAnswer : {}", taskExamAnswer);
    if (taskExamAnswer.getId() != null) {
      throw new BadRequestAlertException(
        "A new taskExamAnswer cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    TaskExamAnswer result = taskExamAnswerService.save(taskExamAnswer);
    return ResponseEntity
      .created(new URI("/api/task-exam-answers/" + result.getId()))
      .headers(
        HeaderUtil.createEntityCreationAlert(
          applicationName,
          true,
          ENTITY_NAME,
          result.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PUT  /task-exam-answers/:id} : Updates an existing taskExamAnswer.
   *
   * @param id the id of the taskExamAnswer to save.
   * @param taskExamAnswer the taskExamAnswer to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskExamAnswer,
   * or with status {@code 400 (Bad Request)} if the taskExamAnswer is not valid,
   * or with status {@code 500 (Internal Server Error)} if the taskExamAnswer couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/task-exam-answers/{id}")
  public ResponseEntity<TaskExamAnswer> updateTaskExamAnswer(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TaskExamAnswer taskExamAnswer
  ) throws URISyntaxException {
    log.debug(
      "REST request to update TaskExamAnswer : {}, {}",
      id,
      taskExamAnswer
    );
    if (taskExamAnswer.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, taskExamAnswer.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!taskExamAnswerRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    TaskExamAnswer result = taskExamAnswerService.save(taskExamAnswer);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          taskExamAnswer.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /task-exam-answers/:id} : Partial updates given fields of an existing taskExamAnswer, field will ignore if it is null
   *
   * @param id the id of the taskExamAnswer to save.
   * @param taskExamAnswer the taskExamAnswer to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskExamAnswer,
   * or with status {@code 400 (Bad Request)} if the taskExamAnswer is not valid,
   * or with status {@code 404 (Not Found)} if the taskExamAnswer is not found,
   * or with status {@code 500 (Internal Server Error)} if the taskExamAnswer couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/task-exam-answers/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<TaskExamAnswer> partialUpdateTaskExamAnswer(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TaskExamAnswer taskExamAnswer
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update TaskExamAnswer partially : {}, {}",
      id,
      taskExamAnswer
    );
    if (taskExamAnswer.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, taskExamAnswer.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!taskExamAnswerRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<TaskExamAnswer> result = taskExamAnswerService.partialUpdate(
      taskExamAnswer
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        taskExamAnswer.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /task-exam-answers} : get all the taskExamAnswers.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskExamAnswers in body.
   */
  @GetMapping("/task-exam-answers")
  public List<TaskExamAnswer> getAllTaskExamAnswers() {
    log.debug("REST request to get all TaskExamAnswers");
    return taskExamAnswerService.findAll();
  }

  /**
   * {@code GET  /task-exam-answers/:id} : get the "id" taskExamAnswer.
   *
   * @param id the id of the taskExamAnswer to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskExamAnswer, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/task-exam-answers/{id}")
  public ResponseEntity<TaskExamAnswer> getTaskExamAnswer(
    @PathVariable Long id
  ) {
    log.debug("REST request to get TaskExamAnswer : {}", id);
    Optional<TaskExamAnswer> taskExamAnswer = taskExamAnswerService.findOne(id);
    return ResponseUtil.wrapOrNotFound(taskExamAnswer);
  }

  /**
   * {@code DELETE  /task-exam-answers/:id} : delete the "id" taskExamAnswer.
   *
   * @param id the id of the taskExamAnswer to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/task-exam-answers/{id}")
  public ResponseEntity<Void> deleteTaskExamAnswer(@PathVariable Long id) {
    log.debug("REST request to delete TaskExamAnswer : {}", id);
    taskExamAnswerService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(
        HeaderUtil.createEntityDeletionAlert(
          applicationName,
          true,
          ENTITY_NAME,
          id.toString()
        )
      )
      .build();
  }
}
