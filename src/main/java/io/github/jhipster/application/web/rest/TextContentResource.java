package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.TextContentRepository;
import io.github.jhipster.application.service.TextContentService;
import io.github.jhipster.application.service.dto.TextContent;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TextContent}.
 */
@RestController
@RequestMapping("/api")
public class TextContentResource {

  private final Logger log = LoggerFactory.getLogger(TextContentResource.class);

  private static final String ENTITY_NAME = "jHmicserDemoTextContent";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TextContentService textContentService;

  private final TextContentRepository textContentRepository;

  public TextContentResource(
    TextContentService textContentService,
    TextContentRepository textContentRepository
  ) {
    this.textContentService = textContentService;
    this.textContentRepository = textContentRepository;
  }

  /**
   * {@code POST  /text-contents} : Create a new textContent.
   *
   * @param textContent the textContent to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new textContent, or with status {@code 400 (Bad Request)} if the textContent has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/text-contents")
  public ResponseEntity<TextContent> createTextContent(
    @RequestBody TextContent textContent
  ) throws URISyntaxException {
    log.debug("REST request to save TextContent : {}", textContent);
    if (textContent.getId() != null) {
      throw new BadRequestAlertException(
        "A new textContent cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    TextContent result = textContentService.save(textContent);
    return ResponseEntity
      .created(new URI("/api/text-contents/" + result.getId()))
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
   * {@code PUT  /text-contents/:id} : Updates an existing textContent.
   *
   * @param id the id of the textContent to save.
   * @param textContent the textContent to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated textContent,
   * or with status {@code 400 (Bad Request)} if the textContent is not valid,
   * or with status {@code 500 (Internal Server Error)} if the textContent couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/text-contents/{id}")
  public ResponseEntity<TextContent> updateTextContent(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TextContent textContent
  ) throws URISyntaxException {
    log.debug("REST request to update TextContent : {}, {}", id, textContent);
    if (textContent.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, textContent.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!textContentRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    TextContent result = textContentService.save(textContent);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          textContent.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /text-contents/:id} : Partial updates given fields of an existing textContent, field will ignore if it is null
   *
   * @param id the id of the textContent to save.
   * @param textContent the textContent to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated textContent,
   * or with status {@code 400 (Bad Request)} if the textContent is not valid,
   * or with status {@code 404 (Not Found)} if the textContent is not found,
   * or with status {@code 500 (Internal Server Error)} if the textContent couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/text-contents/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<TextContent> partialUpdateTextContent(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TextContent textContent
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update TextContent partially : {}, {}",
      id,
      textContent
    );
    if (textContent.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, textContent.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!textContentRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<TextContent> result = textContentService.partialUpdate(
      textContent
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        textContent.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /text-contents} : get all the textContents.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of textContents in body.
   */
  @GetMapping("/text-contents")
  public List<TextContent> getAllTextContents() {
    log.debug("REST request to get all TextContents");
    return textContentService.findAll();
  }

  /**
   * {@code GET  /text-contents/:id} : get the "id" textContent.
   *
   * @param id the id of the textContent to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the textContent, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/text-contents/{id}")
  public ResponseEntity<TextContent> getTextContent(@PathVariable Long id) {
    log.debug("REST request to get TextContent : {}", id);
    Optional<TextContent> textContent = textContentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(textContent);
  }

  /**
   * {@code DELETE  /text-contents/:id} : delete the "id" textContent.
   *
   * @param id the id of the textContent to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/text-contents/{id}")
  public ResponseEntity<Void> deleteTextContent(@PathVariable Long id) {
    log.debug("REST request to delete TextContent : {}", id);
    textContentService.delete(id);
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
