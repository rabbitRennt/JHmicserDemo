package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.Message;
import io.github.jhipster.application.repository.MessageRepository;
import io.github.jhipster.application.service.dto.Message;
import io.github.jhipster.application.service.mapper.MessageMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MessageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MessageResourceIT {

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_SEND_USERID = "AAAAAAAAAA";
  private static final String UPDATED_SEND_USERID = "BBBBBBBBBB";

  private static final String DEFAULT_SEND_USERNAME = "AAAAAAAAAA";
  private static final String UPDATED_SEND_USERNAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_RECEIVE_USER_COUNT = 1;
  private static final Integer UPDATED_RECEIVE_USER_COUNT = 2;

  private static final Integer DEFAULT_READ_COUNT = 1;
  private static final Integer UPDATED_READ_COUNT = 2;

  private static final String ENTITY_API_URL = "/api/messages";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private MessageMapper messageMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restMessageMockMvc;

  private Message message;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Message createEntity(EntityManager em) {
    Message message = new Message()
      .title(DEFAULT_TITLE)
      .content(DEFAULT_CONTENT)
      .createDate(DEFAULT_CREATE_DATE)
      .sendUserid(DEFAULT_SEND_USERID)
      .sendUsername(DEFAULT_SEND_USERNAME)
      .receiveUserCount(DEFAULT_RECEIVE_USER_COUNT)
      .readCount(DEFAULT_READ_COUNT);
    return message;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Message createUpdatedEntity(EntityManager em) {
    Message message = new Message()
      .title(UPDATED_TITLE)
      .content(UPDATED_CONTENT)
      .createDate(UPDATED_CREATE_DATE)
      .sendUserid(UPDATED_SEND_USERID)
      .sendUsername(UPDATED_SEND_USERNAME)
      .receiveUserCount(UPDATED_RECEIVE_USER_COUNT)
      .readCount(UPDATED_READ_COUNT);
    return message;
  }

  @BeforeEach
  public void initTest() {
    message = createEntity(em);
  }

  @Test
  @Transactional
  void createMessage() throws Exception {
    int databaseSizeBeforeCreate = messageRepository.findAll().size();
    // Create the Message
    Message message = messageMapper.toDto(message);
    restMessageMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(message))
      )
      .andExpect(status().isCreated());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeCreate + 1);
    Message testMessage = messageList.get(messageList.size() - 1);
    assertThat(testMessage.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testMessage.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessage.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testMessage.getSendUserid()).isEqualTo(DEFAULT_SEND_USERID);
    assertThat(testMessage.getSendUsername()).isEqualTo(DEFAULT_SEND_USERNAME);
    assertThat(testMessage.getReceiveUserCount())
      .isEqualTo(DEFAULT_RECEIVE_USER_COUNT);
    assertThat(testMessage.getReadCount()).isEqualTo(DEFAULT_READ_COUNT);
  }

  @Test
  @Transactional
  void createMessageWithExistingId() throws Exception {
    // Create the Message with an existing ID
    message.setId(1L);
    Message message = messageMapper.toDto(message);

    int databaseSizeBeforeCreate = messageRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restMessageMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(message))
      )
      .andExpect(status().isBadRequest());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllMessages() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    // Get all the messageList
    restMessageMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(message.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].sendUserid").value(hasItem(DEFAULT_SEND_USERID))
      )
      .andExpect(
        jsonPath("$.[*].sendUsername").value(hasItem(DEFAULT_SEND_USERNAME))
      )
      .andExpect(
        jsonPath("$.[*].receiveUserCount")
          .value(hasItem(DEFAULT_RECEIVE_USER_COUNT))
      )
      .andExpect(
        jsonPath("$.[*].readCount").value(hasItem(DEFAULT_READ_COUNT))
      );
  }

  @Test
  @Transactional
  void getMessage() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    // Get the message
    restMessageMockMvc
      .perform(get(ENTITY_API_URL_ID, message.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(message.getId().intValue()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(jsonPath("$.sendUserid").value(DEFAULT_SEND_USERID))
      .andExpect(jsonPath("$.sendUsername").value(DEFAULT_SEND_USERNAME))
      .andExpect(
        jsonPath("$.receiveUserCount").value(DEFAULT_RECEIVE_USER_COUNT)
      )
      .andExpect(jsonPath("$.readCount").value(DEFAULT_READ_COUNT));
  }

  @Test
  @Transactional
  void getNonExistingMessage() throws Exception {
    // Get the message
    restMessageMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewMessage() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    int databaseSizeBeforeUpdate = messageRepository.findAll().size();

    // Update the message
    Message updatedMessage = messageRepository.findById(message.getId()).get();
    // Disconnect from session so that the updates on updatedMessage are not directly saved in db
    em.detach(updatedMessage);
    updatedMessage
      .title(UPDATED_TITLE)
      .content(UPDATED_CONTENT)
      .createDate(UPDATED_CREATE_DATE)
      .sendUserid(UPDATED_SEND_USERID)
      .sendUsername(UPDATED_SEND_USERNAME)
      .receiveUserCount(UPDATED_RECEIVE_USER_COUNT)
      .readCount(UPDATED_READ_COUNT);
    Message message = messageMapper.toDto(updatedMessage);

    restMessageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, messageDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageDTO))
      )
      .andExpect(status().isOk());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
    Message testMessage = messageList.get(messageList.size() - 1);
    assertThat(testMessage.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testMessage.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessage.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testMessage.getSendUserid()).isEqualTo(UPDATED_SEND_USERID);
    assertThat(testMessage.getSendUsername()).isEqualTo(UPDATED_SEND_USERNAME);
    assertThat(testMessage.getReceiveUserCount())
      .isEqualTo(UPDATED_RECEIVE_USER_COUNT);
    assertThat(testMessage.getReadCount()).isEqualTo(UPDATED_READ_COUNT);
  }

  @Test
  @Transactional
  void putNonExistingMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    Message message = messageMapper.toDto(message);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, message.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(message))
      )
      .andExpect(status().isBadRequest());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    Message message = messageMapper.toDto(message);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(message))
      )
      .andExpect(status().isBadRequest());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    Message message = messageMapper.toDto(message);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(message))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateMessageWithPatch() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    int databaseSizeBeforeUpdate = messageRepository.findAll().size();

    // Update the message using partial update
    Message partialUpdatedMessage = new Message();
    partialUpdatedMessage.setId(message.getId());

    partialUpdatedMessage
      .sendUsername(UPDATED_SEND_USERNAME)
      .receiveUserCount(UPDATED_RECEIVE_USER_COUNT);

    restMessageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessage))
      )
      .andExpect(status().isOk());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
    Message testMessage = messageList.get(messageList.size() - 1);
    assertThat(testMessage.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testMessage.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testMessage.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testMessage.getSendUserid()).isEqualTo(DEFAULT_SEND_USERID);
    assertThat(testMessage.getSendUsername()).isEqualTo(UPDATED_SEND_USERNAME);
    assertThat(testMessage.getReceiveUserCount())
      .isEqualTo(UPDATED_RECEIVE_USER_COUNT);
    assertThat(testMessage.getReadCount()).isEqualTo(DEFAULT_READ_COUNT);
  }

  @Test
  @Transactional
  void fullUpdateMessageWithPatch() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    int databaseSizeBeforeUpdate = messageRepository.findAll().size();

    // Update the message using partial update
    Message partialUpdatedMessage = new Message();
    partialUpdatedMessage.setId(message.getId());

    partialUpdatedMessage
      .title(UPDATED_TITLE)
      .content(UPDATED_CONTENT)
      .createDate(UPDATED_CREATE_DATE)
      .sendUserid(UPDATED_SEND_USERID)
      .sendUsername(UPDATED_SEND_USERNAME)
      .receiveUserCount(UPDATED_RECEIVE_USER_COUNT)
      .readCount(UPDATED_READ_COUNT);

    restMessageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessage.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessage))
      )
      .andExpect(status().isOk());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
    Message testMessage = messageList.get(messageList.size() - 1);
    assertThat(testMessage.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testMessage.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testMessage.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testMessage.getSendUserid()).isEqualTo(UPDATED_SEND_USERID);
    assertThat(testMessage.getSendUsername()).isEqualTo(UPDATED_SEND_USERNAME);
    assertThat(testMessage.getReceiveUserCount())
      .isEqualTo(UPDATED_RECEIVE_USER_COUNT);
    assertThat(testMessage.getReadCount()).isEqualTo(UPDATED_READ_COUNT);
  }

  @Test
  @Transactional
  void patchNonExistingMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    MessageDTO messageDTO = messageMapper.toDto(message);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, messageDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    MessageDTO messageDTO = messageMapper.toDto(message);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamMessage() throws Exception {
    int databaseSizeBeforeUpdate = messageRepository.findAll().size();
    message.setId(count.incrementAndGet());

    // Create the Message
    MessageDTO messageDTO = messageMapper.toDto(message);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Message in the database
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteMessage() throws Exception {
    // Initialize the database
    messageRepository.saveAndFlush(message);

    int databaseSizeBeforeDelete = messageRepository.findAll().size();

    // Delete the message
    restMessageMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, message.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Message> messageList = messageRepository.findAll();
    assertThat(messageList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
