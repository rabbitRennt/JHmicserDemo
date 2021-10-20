package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.MessageUser;
import io.github.jhipster.application.repository.MessageUserRepository;
import io.github.jhipster.application.service.dto.MessageUser;
import io.github.jhipster.application.service.mapper.MessageUserMapper;
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
 * Integration tests for the {@link MessageUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MessageUserResourceIT {

  private static final Long DEFAULT_RECEIVE_USER_ID = 1L;
  private static final Long UPDATED_RECEIVE_USER_ID = 2L;

  private static final String DEFAULT_RECEIVE_USERNAME = "AAAAAAAAAA";
  private static final String UPDATED_RECEIVE_USERNAME = "BBBBBBBBBB";

  private static final String DEFAULT_RECEIVE_REAL_NAME = "AAAAAAAAAA";
  private static final String UPDATED_RECEIVE_REAL_NAME = "BBBBBBBBBB";

  private static final Boolean DEFAULT_READ = false;
  private static final Boolean UPDATED_READ = true;

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_READ_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_READ_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final String ENTITY_API_URL = "/api/message-users";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private MessageUserRepository messageUserRepository;

  @Autowired
  private MessageUserMapper messageUserMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restMessageUserMockMvc;

  private MessageUser messageUser;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MessageUser createEntity(EntityManager em) {
    MessageUser messageUser = new MessageUser()
      .receiveUserId(DEFAULT_RECEIVE_USER_ID)
      .receiveUsername(DEFAULT_RECEIVE_USERNAME)
      .receiveRealName(DEFAULT_RECEIVE_REAL_NAME)
      .read(DEFAULT_READ)
      .createDate(DEFAULT_CREATE_DATE)
      .readDate(DEFAULT_READ_DATE);
    return messageUser;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static MessageUser createUpdatedEntity(EntityManager em) {
    MessageUser messageUser = new MessageUser()
      .receiveUserId(UPDATED_RECEIVE_USER_ID)
      .receiveUsername(UPDATED_RECEIVE_USERNAME)
      .receiveRealName(UPDATED_RECEIVE_REAL_NAME)
      .read(UPDATED_READ)
      .createDate(UPDATED_CREATE_DATE)
      .readDate(UPDATED_READ_DATE);
    return messageUser;
  }

  @BeforeEach
  public void initTest() {
    messageUser = createEntity(em);
  }

  @Test
  @Transactional
  void createMessageUser() throws Exception {
    int databaseSizeBeforeCreate = messageUserRepository.findAll().size();
    // Create the MessageUser
    MessageUser messageUser = messageUserMapper.toDto(messageUser);
    restMessageUserMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUser))
      )
      .andExpect(status().isCreated());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeCreate + 1);
    MessageUser testMessageUser = messageUserList.get(
      messageUserList.size() - 1
    );
    assertThat(testMessageUser.getReceiveUserId())
      .isEqualTo(DEFAULT_RECEIVE_USER_ID);
    assertThat(testMessageUser.getReceiveUsername())
      .isEqualTo(DEFAULT_RECEIVE_USERNAME);
    assertThat(testMessageUser.getReceiveRealName())
      .isEqualTo(DEFAULT_RECEIVE_REAL_NAME);
    assertThat(testMessageUser.getRead()).isEqualTo(DEFAULT_READ);
    assertThat(testMessageUser.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testMessageUser.getReadDate()).isEqualTo(DEFAULT_READ_DATE);
  }

  @Test
  @Transactional
  void createMessageUserWithExistingId() throws Exception {
    // Create the MessageUser with an existing ID
    messageUser.setId(1L);
    MessageUser messageUser = messageUserMapper.toDto(messageUser);

    int databaseSizeBeforeCreate = messageUserRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restMessageUserMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUser))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllMessageUsers() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    // Get all the messageUserList
    restMessageUserMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(messageUser.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].receiveUserId")
          .value(hasItem(DEFAULT_RECEIVE_USER_ID.intValue()))
      )
      .andExpect(
        jsonPath("$.[*].receiveUsername")
          .value(hasItem(DEFAULT_RECEIVE_USERNAME))
      )
      .andExpect(
        jsonPath("$.[*].receiveRealName")
          .value(hasItem(DEFAULT_RECEIVE_REAL_NAME))
      )
      .andExpect(
        jsonPath("$.[*].read").value(hasItem(DEFAULT_READ.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].readDate").value(hasItem(DEFAULT_READ_DATE.toString()))
      );
  }

  @Test
  @Transactional
  void getMessageUser() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    // Get the messageUser
    restMessageUserMockMvc
      .perform(get(ENTITY_API_URL_ID, messageUser.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(messageUser.getId().intValue()))
      .andExpect(
        jsonPath("$.receiveUserId").value(DEFAULT_RECEIVE_USER_ID.intValue())
      )
      .andExpect(jsonPath("$.receiveUsername").value(DEFAULT_RECEIVE_USERNAME))
      .andExpect(jsonPath("$.receiveRealName").value(DEFAULT_RECEIVE_REAL_NAME))
      .andExpect(jsonPath("$.read").value(DEFAULT_READ.booleanValue()))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(jsonPath("$.readDate").value(DEFAULT_READ_DATE.toString()));
  }

  @Test
  @Transactional
  void getNonExistingMessageUser() throws Exception {
    // Get the messageUser
    restMessageUserMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewMessageUser() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();

    // Update the messageUser
    MessageUser updatedMessageUser = messageUserRepository
      .findById(messageUser.getId())
      .get();
    // Disconnect from session so that the updates on updatedMessageUser are not directly saved in db
    em.detach(updatedMessageUser);
    updatedMessageUser
      .receiveUserId(UPDATED_RECEIVE_USER_ID)
      .receiveUsername(UPDATED_RECEIVE_USERNAME)
      .receiveRealName(UPDATED_RECEIVE_REAL_NAME)
      .read(UPDATED_READ)
      .createDate(UPDATED_CREATE_DATE)
      .readDate(UPDATED_READ_DATE);
    MessageUser messageUser = messageUserMapper.toDto(updatedMessageUser);

    restMessageUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, messageUserDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUserDTO))
      )
      .andExpect(status().isOk());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
    MessageUser testMessageUser = messageUserList.get(
      messageUserList.size() - 1
    );
    assertThat(testMessageUser.getReceiveUserId())
      .isEqualTo(UPDATED_RECEIVE_USER_ID);
    assertThat(testMessageUser.getReceiveUsername())
      .isEqualTo(UPDATED_RECEIVE_USERNAME);
    assertThat(testMessageUser.getReceiveRealName())
      .isEqualTo(UPDATED_RECEIVE_REAL_NAME);
    assertThat(testMessageUser.getRead()).isEqualTo(UPDATED_READ);
    assertThat(testMessageUser.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testMessageUser.getReadDate()).isEqualTo(UPDATED_READ_DATE);
  }

  @Test
  @Transactional
  void putNonExistingMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUser messageUser = messageUserMapper.toDto(messageUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, messageUser.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUser))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUser messageUser = messageUserMapper.toDto(messageUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUser))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUser messageUser = messageUserMapper.toDto(messageUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(messageUser))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateMessageUserWithPatch() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();

    // Update the messageUser using partial update
    MessageUser partialUpdatedMessageUser = new MessageUser();
    partialUpdatedMessageUser.setId(messageUser.getId());

    partialUpdatedMessageUser.createDate(UPDATED_CREATE_DATE);

    restMessageUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessageUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageUser))
      )
      .andExpect(status().isOk());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
    MessageUser testMessageUser = messageUserList.get(
      messageUserList.size() - 1
    );
    assertThat(testMessageUser.getReceiveUserId())
      .isEqualTo(DEFAULT_RECEIVE_USER_ID);
    assertThat(testMessageUser.getReceiveUsername())
      .isEqualTo(DEFAULT_RECEIVE_USERNAME);
    assertThat(testMessageUser.getReceiveRealName())
      .isEqualTo(DEFAULT_RECEIVE_REAL_NAME);
    assertThat(testMessageUser.getRead()).isEqualTo(DEFAULT_READ);
    assertThat(testMessageUser.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testMessageUser.getReadDate()).isEqualTo(DEFAULT_READ_DATE);
  }

  @Test
  @Transactional
  void fullUpdateMessageUserWithPatch() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();

    // Update the messageUser using partial update
    MessageUser partialUpdatedMessageUser = new MessageUser();
    partialUpdatedMessageUser.setId(messageUser.getId());

    partialUpdatedMessageUser
      .receiveUserId(UPDATED_RECEIVE_USER_ID)
      .receiveUsername(UPDATED_RECEIVE_USERNAME)
      .receiveRealName(UPDATED_RECEIVE_REAL_NAME)
      .read(UPDATED_READ)
      .createDate(UPDATED_CREATE_DATE)
      .readDate(UPDATED_READ_DATE);

    restMessageUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedMessageUser.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessageUser))
      )
      .andExpect(status().isOk());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
    MessageUser testMessageUser = messageUserList.get(
      messageUserList.size() - 1
    );
    assertThat(testMessageUser.getReceiveUserId())
      .isEqualTo(UPDATED_RECEIVE_USER_ID);
    assertThat(testMessageUser.getReceiveUsername())
      .isEqualTo(UPDATED_RECEIVE_USERNAME);
    assertThat(testMessageUser.getReceiveRealName())
      .isEqualTo(UPDATED_RECEIVE_REAL_NAME);
    assertThat(testMessageUser.getRead()).isEqualTo(UPDATED_READ);
    assertThat(testMessageUser.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testMessageUser.getReadDate()).isEqualTo(UPDATED_READ_DATE);
  }

  @Test
  @Transactional
  void patchNonExistingMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUserDTO messageUserDTO = messageUserMapper.toDto(messageUser);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, messageUserDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUserDTO messageUserDTO = messageUserMapper.toDto(messageUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageUserDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamMessageUser() throws Exception {
    int databaseSizeBeforeUpdate = messageUserRepository.findAll().size();
    messageUser.setId(count.incrementAndGet());

    // Create the MessageUser
    MessageUserDTO messageUserDTO = messageUserMapper.toDto(messageUser);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restMessageUserMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(messageUserDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the MessageUser in the database
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteMessageUser() throws Exception {
    // Initialize the database
    messageUserRepository.saveAndFlush(messageUser);

    int databaseSizeBeforeDelete = messageUserRepository.findAll().size();

    // Delete the messageUser
    restMessageUserMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, messageUser.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<MessageUser> messageUserList = messageUserRepository.findAll();
    assertThat(messageUserList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
