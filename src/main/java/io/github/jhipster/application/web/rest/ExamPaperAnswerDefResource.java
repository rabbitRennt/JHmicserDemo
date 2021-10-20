package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.ExamPaperAnswerDefRepository;
import io.github.jhipster.application.service.ExamPaperAnswerDefService;
import io.github.jhipster.application.service.dto.ExamPaperAnswerDef;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ExamPaperAnswerDef}.
 */
@RestController
@RequestMapping("/api")
public class ExamPaperAnswerDefResource {

  private final Logger log = LoggerFactory.getLogger(
    ExamPaperAnswerDefResource.class
  );

  private static final String ENTITY_NAME = "jHmicserDemoExamPaperAnswerDef";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ExamPaperAnswerDefService examPaperAnswerDefService;

  private final ExamPaperAnswerDefRepository examPaperAnswerDefRepository;

  public ExamPaperAnswerDefResource(
    ExamPaperAnswerDefService examPaperAnswerDefService,
    ExamPaperAnswerDefRepository examPaperAnswerDefRepository
  ) {
    this.examPaperAnswerDefService = examPaperAnswerDefService;
    this.examPaperAnswerDefRepository = examPaperAnswerDefRepository;
  }

  /**
   * {@code POST  /exam-paper-answer-defs} : Create a new examPaperAnswerDef.
   *
   * @param examPaperAnswerDef the examPaperAnswerDef to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examPaperAnswerDef, or with status {@code 400 (Bad Request)} if the examPaperAnswerDef has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/exam-paper-answer-defs")
  public ResponseEntity<ExamPaperAnswerDef> createExamPaperAnswerDef(
    @RequestBody ExamPaperAnswerDef examPaperAnswerDef
  ) throws URISyntaxException {
    log.debug(
      "REST request to save ExamPaperAnswerDef : {}",
      examPaperAnswerDef
    );
    if (examPaperAnswerDef.getId() != null) {
      throw new BadRequestAlertException(
        "A new examPaperAnswerDef cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    ExamPaperAnswerDef result = examPaperAnswerDefService.save(
      examPaperAnswerDef
    );
    return ResponseEntity
      .created(new URI("/api/exam-paper-answer-defs/" + result.getId()))
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
   * {@code PUT  /exam-paper-answer-defs/:id} : Updates an existing examPaperAnswerDef.
   *
   * @param id the id of the examPaperAnswerDef to save.
   * @param examPaperAnswerDef the examPaperAnswerDef to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaperAnswerDef,
   * or with status {@code 400 (Bad Request)} if the examPaperAnswerDef is not valid,
   * or with status {@code 500 (Internal Server Error)} if the examPaperAnswerDef couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/exam-paper-answer-defs/{id}")
  public ResponseEntity<ExamPaperAnswerDef> updateExamPaperAnswerDef(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaperAnswerDef examPaperAnswerDef
  ) throws URISyntaxException {
    log.debug(
      "REST request to update ExamPaperAnswerDef : {}, {}",
      id,
      examPaperAnswerDef
    );
    if (examPaperAnswerDef.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaperAnswerDef.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperAnswerDefRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    ExamPaperAnswerDef result = examPaperAnswerDefService.save(
      examPaperAnswerDef
    );
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          examPaperAnswerDef.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /exam-paper-answer-defs/:id} : Partial updates given fields of an existing examPaperAnswerDef, field will ignore if it is null
   *
   * @param id the id of the examPaperAnswerDef to save.
   * @param examPaperAnswerDef the examPaperAnswerDef to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaperAnswerDef,
   * or with status {@code 400 (Bad Request)} if the examPaperAnswerDef is not valid,
   * or with status {@code 404 (Not Found)} if the examPaperAnswerDef is not found,
   * or with status {@code 500 (Internal Server Error)} if the examPaperAnswerDef couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/exam-paper-answer-defs/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<ExamPaperAnswerDef> partialUpdateExamPaperAnswerDef(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaperAnswerDef examPaperAnswerDef
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update ExamPaperAnswerDef partially : {}, {}",
      id,
      examPaperAnswerDef
    );
    if (examPaperAnswerDef.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaperAnswerDef.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperAnswerDefRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<ExamPaperAnswerDef> result = examPaperAnswerDefService.partialUpdate(
      examPaperAnswerDef
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        examPaperAnswerDef.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /exam-paper-answer-defs} : get all the examPaperAnswerDefs.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examPaperAnswerDefs in body.
   */
  @GetMapping("/exam-paper-answer-defs")
  public List<ExamPaperAnswerDef> getAllExamPaperAnswerDefs() {
    log.debug("REST request to get all ExamPaperAnswerDefs");
    return examPaperAnswerDefService.findAll();
  }

  /**
   * {@code GET  /exam-paper-answer-defs/:id} : get the "id" examPaperAnswerDef.
   *
   * @param id the id of the examPaperAnswerDef to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examPaperAnswerDef, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/exam-paper-answer-defs/{id}")
  public ResponseEntity<ExamPaperAnswerDef> getExamPaperAnswerDef(
    @PathVariable Long id
  ) {
    log.debug("REST request to get ExamPaperAnswerDef : {}", id);
    Optional<ExamPaperAnswerDef> examPaperAnswerDef = examPaperAnswerDefService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(examPaperAnswerDef);
  }

  /**
   * {@code DELETE  /exam-paper-answer-defs/:id} : delete the "id" examPaperAnswerDef.
   *
   * @param id the id of the examPaperAnswerDef to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/exam-paper-answer-defs/{id}")
  public ResponseEntity<Void> deleteExamPaperAnswerDef(@PathVariable Long id) {
    log.debug("REST request to delete ExamPaperAnswerDef : {}", id);
    examPaperAnswerDefService.delete(id);
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
