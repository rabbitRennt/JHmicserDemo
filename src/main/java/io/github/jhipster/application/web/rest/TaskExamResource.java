package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.TaskExamRepository;
import io.github.jhipster.application.service.TaskExamService;
import io.github.jhipster.application.service.dto.TaskExam;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.TaskExam}.
 */
@RestController
@RequestMapping("/api")
public class TaskExamResource {

  private final Logger log = LoggerFactory.getLogger(TaskExamResource.class);

  private static final String ENTITY_NAME = "jHmicserDemoTaskExam";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TaskExamService taskExamService;

  private final TaskExamRepository taskExamRepository;

  public TaskExamResource(
    TaskExamService taskExamService,
    TaskExamRepository taskExamRepository
  ) {
    this.taskExamService = taskExamService;
    this.taskExamRepository = taskExamRepository;
  }

  /**
   * {@code POST  /task-exams} : Create a new taskExam.
   *
   * @param taskExam the taskExam to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskExam, or with status {@code 400 (Bad Request)} if the taskExam has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/task-exams")
  public ResponseEntity<TaskExam> createTaskExam(
    @RequestBody TaskExam taskExam
  ) throws URISyntaxException {
    log.debug("REST request to save TaskExam : {}", taskExam);
    if (taskExam.getId() != null) {
      throw new BadRequestAlertException(
        "A new taskExam cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    TaskExam result = taskExamService.save(taskExam);
    return ResponseEntity
      .created(new URI("/api/task-exams/" + result.getId()))
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
   * {@code PUT  /task-exams/:id} : Updates an existing taskExam.
   *
   * @param id the id of the taskExam to save.
   * @param taskExam the taskExam to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskExam,
   * or with status {@code 400 (Bad Request)} if the taskExam is not valid,
   * or with status {@code 500 (Internal Server Error)} if the taskExam couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/task-exams/{id}")
  public ResponseEntity<TaskExam> updateTaskExam(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TaskExam taskExam
  ) throws URISyntaxException {
    log.debug("REST request to update TaskExam : {}, {}", id, taskExam);
    if (taskExam.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, taskExam.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!taskExamRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    TaskExam result = taskExamService.save(taskExam);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          taskExam.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /task-exams/:id} : Partial updates given fields of an existing taskExam, field will ignore if it is null
   *
   * @param id the id of the taskExam to save.
   * @param taskExam the taskExam to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskExam,
   * or with status {@code 400 (Bad Request)} if the taskExam is not valid,
   * or with status {@code 404 (Not Found)} if the taskExam is not found,
   * or with status {@code 500 (Internal Server Error)} if the taskExam couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/task-exams/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<TaskExam> partialUpdateTaskExam(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TaskExam taskExam
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update TaskExam partially : {}, {}",
      id,
      taskExam
    );
    if (taskExam.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, taskExam.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!taskExamRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<TaskExam> result = taskExamService.partialUpdate(taskExam);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        taskExam.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /task-exams} : get all the taskExams.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskExams in body.
   */
  @GetMapping("/task-exams")
  public ResponseEntity<List<TaskExam>> getAllTaskExams(Pageable pageable) {
    log.debug("REST request to get a page of TaskExams");
    Page<TaskExam> page = taskExamService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /task-exams/:id} : get the "id" taskExam.
   *
   * @param id the id of the taskExam to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskExam, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/task-exams/{id}")
  public ResponseEntity<TaskExam> getTaskExam(@PathVariable Long id) {
    log.debug("REST request to get TaskExam : {}", id);
    Optional<TaskExam> taskExam = taskExamService.findOne(id);
    return ResponseUtil.wrapOrNotFound(taskExam);
  }

  /**
   * {@code DELETE  /task-exams/:id} : delete the "id" taskExam.
   *
   * @param id the id of the taskExam to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/task-exams/{id}")
  public ResponseEntity<Void> deleteTaskExam(@PathVariable Long id) {
    log.debug("REST request to delete TaskExam : {}", id);
    taskExamService.delete(id);
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
