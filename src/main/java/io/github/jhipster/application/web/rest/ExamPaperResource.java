package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.ExamPaperRepository;
import io.github.jhipster.application.service.ExamPaperService;
import io.github.jhipster.application.service.dto.ExamPaper;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.ExamPaper}.
 */
@RestController
@RequestMapping("/api")
public class ExamPaperResource {

  private final Logger log = LoggerFactory.getLogger(ExamPaperResource.class);

  private static final String ENTITY_NAME = "jHmicserDemoExamPaper";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ExamPaperService examPaperService;

  private final ExamPaperRepository examPaperRepository;

  public ExamPaperResource(
    ExamPaperService examPaperService,
    ExamPaperRepository examPaperRepository
  ) {
    this.examPaperService = examPaperService;
    this.examPaperRepository = examPaperRepository;
  }

  /**
   * {@code POST  /exam-papers} : Create a new examPaper.
   *
   * @param examPaper the examPaper to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examPaper, or with status {@code 400 (Bad Request)} if the examPaper has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/exam-papers")
  public ResponseEntity<ExamPaper> createExamPaper(
    @RequestBody ExamPaper examPaper
  ) throws URISyntaxException {
    log.debug("REST request to save ExamPaper : {}", examPaper);
    if (examPaper.getId() != null) {
      throw new BadRequestAlertException(
        "A new examPaper cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    ExamPaper result = examPaperService.save(examPaper);
    return ResponseEntity
      .created(new URI("/api/exam-papers/" + result.getId()))
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
   * {@code PUT  /exam-papers/:id} : Updates an existing examPaper.
   *
   * @param id the id of the examPaper to save.
   * @param examPaper the examPaper to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaper,
   * or with status {@code 400 (Bad Request)} if the examPaper is not valid,
   * or with status {@code 500 (Internal Server Error)} if the examPaper couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/exam-papers/{id}")
  public ResponseEntity<ExamPaper> updateExamPaper(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaper examPaper
  ) throws URISyntaxException {
    log.debug("REST request to update ExamPaper : {}, {}", id, examPaper);
    if (examPaper.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaper.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    ExamPaper result = examPaperService.save(examPaper);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          examPaper.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /exam-papers/:id} : Partial updates given fields of an existing examPaper, field will ignore if it is null
   *
   * @param id the id of the examPaper to save.
   * @param examPaper the examPaper to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examPaper,
   * or with status {@code 400 (Bad Request)} if the examPaper is not valid,
   * or with status {@code 404 (Not Found)} if the examPaper is not found,
   * or with status {@code 500 (Internal Server Error)} if the examPaper couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/exam-papers/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<ExamPaper> partialUpdateExamPaper(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody ExamPaper examPaper
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update ExamPaper partially : {}, {}",
      id,
      examPaper
    );
    if (examPaper.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, examPaper.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!examPaperRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<ExamPaper> result = examPaperService.partialUpdate(examPaper);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        examPaper.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /exam-papers} : get all the examPapers.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examPapers in body.
   */
  @GetMapping("/exam-papers")
  public ResponseEntity<List<ExamPaper>> getAllExamPapers(Pageable pageable) {
    log.debug("REST request to get a page of ExamPapers");
    Page<ExamPaper> page = examPaperService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /exam-papers/:id} : get the "id" examPaper.
   *
   * @param id the id of the examPaper to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examPaper, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/exam-papers/{id}")
  public ResponseEntity<ExamPaper> getExamPaper(@PathVariable Long id) {
    log.debug("REST request to get ExamPaper : {}", id);
    Optional<ExamPaper> examPaper = examPaperService.findOne(id);
    return ResponseUtil.wrapOrNotFound(examPaper);
  }

  /**
   * {@code DELETE  /exam-papers/:id} : delete the "id" examPaper.
   *
   * @param id the id of the examPaper to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/exam-papers/{id}")
  public ResponseEntity<Void> deleteExamPaper(@PathVariable Long id) {
    log.debug("REST request to delete ExamPaper : {}", id);
    examPaperService.delete(id);
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
