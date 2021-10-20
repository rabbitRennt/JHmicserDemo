package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.ExamPaperAnswerRepository;
import io.github.jhipster.application.service.ExamPaperAnswerService;
import io.github.jhipster.application.service.dto.ExamPaperAnswer;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ExamPaperAnswer}.
 */
@RestController
@RequestMapping("/api")
public class ExamPaperAnswerResource {

  private final Logger log = LoggerFactory.getLogger(
    ExamPaperAnswerResource.class
  );

  private static final String ENTITY_NAME = "jHmicserDemoExamPaperAnswer";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ExamPaperAnswerService examPaperAnswerService;

  private final ExamPaperAnswerRepository examPaperAnswerRepository;

  public ExamPaperAnswerResource(
    ExamPaperAnswerService examPaperAnswerService,
    ExamPaperAnswerRepository examPaperAnswerRepository
  ) {
    this.examPaperAnswerService = examPaperAnswerService;
    this.examPaperAnswerRepository = examPaperAnswerRepository;
  }

  /**
   * {@code POST  /exam-paper-answers} : Create a new examPaperAnswer.
   *
   * @param examPaperAnswer the examPaperAnswer to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examPaperAnswer, or with status {@code 400 (Bad Request)} if the examPaperAnswer has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/exam-paper-answers")
  public ResponseEntity<ExamPaperAnswer> createExamPaperAnswer(
    @RequestBody ExamPaperAnswer examPaperAnswer
  ) throws URISyntaxException {
    log.debug("REST request to save ExamPaperAnswer : {}", examPaperAnswer);
    if (examPaperAnswer.getId() != null) {
      throw new BadRequestAlertException(
        "A new examPaperAnswer cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    ExamPaperAnswer result = examPaperAnswerService.save(examPaperAnswer);
    return ResponseEntity
      .created(new URI("/api/exam-paper-answers/" + result.getId()))
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
   * {@code PUT  /exam-paper-answers/:id} : Updates an existing examPaperAnswer.
   *
   * @param id the id of the examPaperAnswer to save.
   * @param examPaperAnswer the examPaperAnswer to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaperAnswer,
   * or with status {@code 400 (Bad Request)} if the examPaperAnswer is not valid,
   * or with status {@code 500 (Internal Server Error)} if the examPaperAnswer couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/exam-paper-answers/{id}")
  public ResponseEntity<ExamPaperAnswer> updateExamPaperAnswer(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaperAnswer examPaperAnswer
  ) throws URISyntaxException {
    log.debug(
      "REST request to update ExamPaperAnswer : {}, {}",
      id,
      examPaperAnswer
    );
    if (examPaperAnswer.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaperAnswer.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperAnswerRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    ExamPaperAnswer result = examPaperAnswerService.save(examPaperAnswer);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          examPaperAnswer.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /exam-paper-answers/:id} : Partial updates given fields of an existing examPaperAnswer, field will ignore if it is null
   *
   * @param id the id of the examPaperAnswer to save.
   * @param examPaperAnswer the examPaperAnswer to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaperAnswer,
   * or with status {@code 400 (Bad Request)} if the examPaperAnswer is not valid,
   * or with status {@code 404 (Not Found)} if the examPaperAnswer is not found,
   * or with status {@code 500 (Internal Server Error)} if the examPaperAnswer couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/exam-paper-answers/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<ExamPaperAnswer> partialUpdateExamPaperAnswer(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaperAnswer examPaperAnswer
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update ExamPaperAnswer partially : {}, {}",
      id,
      examPaperAnswer
    );
    if (examPaperAnswer.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaperAnswer.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperAnswerRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<ExamPaperAnswer> result = examPaperAnswerService.partialUpdate(
      examPaperAnswer
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        examPaperAnswer.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /exam-paper-answers} : get all the examPaperAnswers.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examPaperAnswers in body.
   */
  @GetMapping("/exam-paper-answers")
  public ResponseEntity<List<ExamPaperAnswer>> getAllExamPaperAnswers(
    Pageable pageable
  ) {
    log.debug("REST request to get a page of ExamPaperAnswers");
    Page<ExamPaperAnswer> page = examPaperAnswerService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /exam-paper-answers/:id} : get the "id" examPaperAnswer.
   *
   * @param id the id of the examPaperAnswer to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examPaperAnswer, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/exam-paper-answers/{id}")
  public ResponseEntity<ExamPaperAnswer> getExamPaperAnswer(
    @PathVariable Long id
  ) {
    log.debug("REST request to get ExamPaperAnswer : {}", id);
    Optional<ExamPaperAnswer> examPaperAnswer = examPaperAnswerService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(examPaperAnswer);
  }

  /**
   * {@code DELETE  /exam-paper-answers/:id} : delete the "id" examPaperAnswer.
   *
   * @param id the id of the examPaperAnswer to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/exam-paper-answers/{id}")
  public ResponseEntity<Void> deleteExamPaperAnswer(@PathVariable Long id) {
    log.debug("REST request to delete ExamPaperAnswer : {}", id);
    examPaperAnswerService.delete(id);
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
