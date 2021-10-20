package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.SubjectRepository;
import io.github.jhipster.application.service.SubjectService;
import io.github.jhipster.application.service.dto.Subject;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Subject}.
 */
@RestController
@RequestMapping("/api")
public class SubjectResource {

  private final Logger log = LoggerFactory.getLogger(SubjectResource.class);

  private static final String ENTITY_NAME = "jHmicserDemoSubject";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final SubjectService subjectService;

  private final SubjectRepository subjectRepository;

  public SubjectResource(
    SubjectService subjectService,
    SubjectRepository subjectRepository
  ) {
    this.subjectService = subjectService;
    this.subjectRepository = subjectRepository;
  }

  /**
   * {@code POST  /subjects} : Create a new subject.
   *
   * @param subject the subject to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subject, or with status {@code 400 (Bad Request)} if the subject has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/subjects")
  public ResponseEntity<Subject> createSubject(@RequestBody Subject subject)
    throws URISyntaxException {
    log.debug("REST request to save Subject : {}", subject);
    if (subject.getId() != null) {
      throw new BadRequestAlertException(
        "A new subject cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    Subject result = subjectService.save(subject);
    return ResponseEntity
      .created(new URI("/api/subjects/" + result.getId()))
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
   * {@code PUT  /subjects/:id} : Updates an existing subject.
   *
   * @param id the id of the subject to save.
   * @param subject the subject to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subject,
   * or with status {@code 400 (Bad Request)} if the subject is not valid,
   * or with status {@code 500 (Internal Server Error)} if the subject couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/subjects/{id}")
  public ResponseEntity<Subject> updateSubject(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody Subject subject
  ) throws URISyntaxException {
    log.debug("REST request to update Subject : {}, {}", id, subject);
    if (subject.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, subject.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!subjectRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Subject result = subjectService.save(subject);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          subject.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /subjects/:id} : Partial updates given fields of an existing subject, field will ignore if it is null
   *
   * @param id the id of the subject to save.
   * @param subject the subject to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subject,
   * or with status {@code 400 (Bad Request)} if the subject is not valid,
   * or with status {@code 404 (Not Found)} if the subject is not found,
   * or with status {@code 500 (Internal Server Error)} if the subject couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/subjects/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<Subject> partialUpdateSubject(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody Subject subject
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update Subject partially : {}, {}",
      id,
      subject
    );
    if (subject.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, subject.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!subjectRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<Subject> result = subjectService.partialUpdate(subject);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        subject.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /subjects} : get all the subjects.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subjects in body.
   */
  @GetMapping("/subjects")
  public ResponseEntity<List<Subject>> getAllSubjects(Pageable pageable) {
    log.debug("REST request to get a page of Subjects");
    Page<Subject> page = subjectService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /subjects/:id} : get the "id" subject.
   *
   * @param id the id of the subject to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subject, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/subjects/{id}")
  public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
    log.debug("REST request to get Subject : {}", id);
    Optional<Subject> subject = subjectService.findOne(id);
    return ResponseUtil.wrapOrNotFound(subject);
  }

  /**
   * {@code DELETE  /subjects/:id} : delete the "id" subject.
   *
   * @param id the id of the subject to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/subjects/{id}")
  public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
    log.debug("REST request to delete Subject : {}", id);
    subjectService.delete(id);
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
