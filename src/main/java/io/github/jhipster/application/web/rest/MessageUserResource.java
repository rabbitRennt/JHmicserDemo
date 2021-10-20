package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.repository.MessageUserRepository;
import io.github.jhipster.application.service.MessageUserService;
import io.github.jhipster.application.service.dto.MessageUser;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.MessageUser}.
 */
@RestController
@RequestMapping("/api")
public class MessageUserResource {

  private final Logger log = LoggerFactory.getLogger(MessageUserResource.class);

  private static final String ENTITY_NAME = "jHmicserDemoMessageUser";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MessageUserService messageUserService;

  private final MessageUserRepository messageUserRepository;

  public MessageUserResource(
    MessageUserService messageUserService,
    MessageUserRepository messageUserRepository
  ) {
    this.messageUserService = messageUserService;
    this.messageUserRepository = messageUserRepository;
  }

  /**
   * {@code POST  /message-users} : Create a new messageUser.
   *
   * @param messageUser the messageUser to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageUser, or with status {@code 400 (Bad Request)} if the messageUser has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/message-users")
  public ResponseEntity<MessageUser> createMessageUser(
    @RequestBody MessageUser messageUser
  ) throws URISyntaxException {
    log.debug("REST request to save MessageUser : {}", messageUser);
    if (messageUser.getId() != null) {
      throw new BadRequestAlertException(
        "A new messageUser cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    MessageUser result = messageUserService.save(messageUser);
    return ResponseEntity
      .created(new URI("/api/message-users/" + result.getId()))
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
   * {@code PUT  /message-users/:id} : Updates an existing messageUser.
   *
   * @param id the id of the messageUser to save.
   * @param messageUser the messageUser to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageUser,
   * or with status {@code 400 (Bad Request)} if the messageUser is not valid,
   * or with status {@code 500 (Internal Server Error)} if the messageUser couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/message-users/{id}")
  public ResponseEntity<MessageUser> updateMessageUser(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody MessageUser messageUser
  ) throws URISyntaxException {
    log.debug("REST request to update MessageUser : {}, {}", id, messageUser);
    if (messageUser.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, messageUser.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!messageUserRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    MessageUser result = messageUserService.save(messageUser);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          messageUser.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /message-users/:id} : Partial updates given fields of an existing messageUser, field will ignore if it is null
   *
   * @param id the id of the messageUser to save.
   * @param messageUser the messageUser to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageUser,
   * or with status {@code 400 (Bad Request)} if the messageUser is not valid,
   * or with status {@code 404 (Not Found)} if the messageUser is not found,
   * or with status {@code 500 (Internal Server Error)} if the messageUser couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/message-users/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<MessageUser> partialUpdateMessageUser(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody MessageUser messageUser
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update MessageUser partially : {}, {}",
      id,
      messageUser
    );
    if (messageUser.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, messageUser.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!messageUserRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<MessageUser> result = messageUserService.partialUpdate(
      messageUser
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        messageUser.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /message-users} : get all the messageUsers.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageUsers in body.
   */
  @GetMapping("/message-users")
  public List<MessageUser> getAllMessageUsers() {
    log.debug("REST request to get all MessageUsers");
    return messageUserService.findAll();
  }

  /**
   * {@code GET  /message-users/:id} : get the "id" messageUser.
   *
   * @param id the id of the messageUser to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageUser, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/message-users/{id}")
  public ResponseEntity<MessageUser> getMessageUser(@PathVariable Long id) {
    log.debug("REST request to get MessageUser : {}", id);
    Optional<MessageUser> messageUser = messageUserService.findOne(id);
    return ResponseUtil.wrapOrNotFound(messageUser);
  }

  /**
   * {@code DELETE  /message-users/:id} : delete the "id" messageUser.
   *
   * @param id the id of the messageUser to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/message-users/{id}")
  public ResponseEntity<Void> deleteMessageUser(@PathVariable Long id) {
    log.debug("REST request to delete MessageUser : {}", id);
    messageUserService.delete(id);
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
