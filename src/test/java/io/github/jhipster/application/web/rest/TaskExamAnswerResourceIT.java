package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.TaskExamAnswer;
import io.github.jhipster.application.repository.TaskExamAnswerRepository;
import io.github.jhipster.application.service.dto.TaskExamAnswer;
import io.github.jhipster.application.service.mapper.TaskExamAnswerMapper;
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
 * Integration tests for the {@link TaskExamAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskExamAnswerResourceIT {

  private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Long DEFAULT_TEXT_CONTENT_ID = 1L;
  private static final Long UPDATED_TEXT_CONTENT_ID = 2L;

  private static final String ENTITY_API_URL = "/api/task-exam-answers";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private TaskExamAnswerRepository taskExamAnswerRepository;

  @Autowired
  private TaskExamAnswerMapper taskExamAnswerMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTaskExamAnswerMockMvc;

  private TaskExamAnswer taskExamAnswer;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TaskExamAnswer createEntity(EntityManager em) {
    TaskExamAnswer taskExamAnswer = new TaskExamAnswer()
      .createBy(DEFAULT_CREATE_BY)
      .createDate(DEFAULT_CREATE_DATE)
      .textContentId(DEFAULT_TEXT_CONTENT_ID);
    return taskExamAnswer;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TaskExamAnswer createUpdatedEntity(EntityManager em) {
    TaskExamAnswer taskExamAnswer = new TaskExamAnswer()
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .textContentId(UPDATED_TEXT_CONTENT_ID);
    return taskExamAnswer;
  }

  @BeforeEach
  public void initTest() {
    taskExamAnswer = createEntity(em);
  }

  @Test
  @Transactional
  void createTaskExamAnswer() throws Exception {
    int databaseSizeBeforeCreate = taskExamAnswerRepository.findAll().size();
    // Create the TaskExamAnswer
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(taskExamAnswer);
    restTaskExamAnswerMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswer))
      )
      .andExpect(status().isCreated());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeCreate + 1);
    TaskExamAnswer testTaskExamAnswer = taskExamAnswerList.get(
      taskExamAnswerList.size() - 1
    );
    assertThat(testTaskExamAnswer.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testTaskExamAnswer.getCreateDate())
      .isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testTaskExamAnswer.getTextContentId())
      .isEqualTo(DEFAULT_TEXT_CONTENT_ID);
  }

  @Test
  @Transactional
  void createTaskExamAnswerWithExistingId() throws Exception {
    // Create the TaskExamAnswer with an existing ID
    taskExamAnswer.setId(1L);
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(taskExamAnswer);

    int databaseSizeBeforeCreate = taskExamAnswerRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTaskExamAnswerMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllTaskExamAnswers() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    // Get all the taskExamAnswerList
    restTaskExamAnswerMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(taskExamAnswer.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].textContentId")
          .value(hasItem(DEFAULT_TEXT_CONTENT_ID.intValue()))
      );
  }

  @Test
  @Transactional
  void getTaskExamAnswer() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    // Get the taskExamAnswer
    restTaskExamAnswerMockMvc
      .perform(get(ENTITY_API_URL_ID, taskExamAnswer.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(taskExamAnswer.getId().intValue()))
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(
        jsonPath("$.textContentId").value(DEFAULT_TEXT_CONTENT_ID.intValue())
      );
  }

  @Test
  @Transactional
  void getNonExistingTaskExamAnswer() throws Exception {
    // Get the taskExamAnswer
    restTaskExamAnswerMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTaskExamAnswer() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();

    // Update the taskExamAnswer
    TaskExamAnswer updatedTaskExamAnswer = taskExamAnswerRepository
      .findById(taskExamAnswer.getId())
      .get();
    // Disconnect from session so that the updates on updatedTaskExamAnswer are not directly saved in db
    em.detach(updatedTaskExamAnswer);
    updatedTaskExamAnswer
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .textContentId(UPDATED_TEXT_CONTENT_ID);
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(
      updatedTaskExamAnswer
    );

    restTaskExamAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, taskExamAnswerDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswerDTO))
      )
      .andExpect(status().isOk());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
    TaskExamAnswer testTaskExamAnswer = taskExamAnswerList.get(
      taskExamAnswerList.size() - 1
    );
    assertThat(testTaskExamAnswer.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testTaskExamAnswer.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testTaskExamAnswer.getTextContentId())
      .isEqualTo(UPDATED_TEXT_CONTENT_ID);
  }

  @Test
  @Transactional
  void putNonExistingTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(taskExamAnswer);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, taskExamAnswer.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(taskExamAnswer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswer taskExamAnswer = taskExamAnswerMapper.toDto(taskExamAnswer);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswer))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateTaskExamAnswerWithPatch() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();

    // Update the taskExamAnswer using partial update
    TaskExamAnswer partialUpdatedTaskExamAnswer = new TaskExamAnswer();
    partialUpdatedTaskExamAnswer.setId(taskExamAnswer.getId());

    partialUpdatedTaskExamAnswer
      .createBy(UPDATED_CREATE_BY)
      .textContentId(UPDATED_TEXT_CONTENT_ID);

    restTaskExamAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTaskExamAnswer.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedTaskExamAnswer)
          )
      )
      .andExpect(status().isOk());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
    TaskExamAnswer testTaskExamAnswer = taskExamAnswerList.get(
      taskExamAnswerList.size() - 1
    );
    assertThat(testTaskExamAnswer.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testTaskExamAnswer.getCreateDate())
      .isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testTaskExamAnswer.getTextContentId())
      .isEqualTo(UPDATED_TEXT_CONTENT_ID);
  }

  @Test
  @Transactional
  void fullUpdateTaskExamAnswerWithPatch() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();

    // Update the taskExamAnswer using partial update
    TaskExamAnswer partialUpdatedTaskExamAnswer = new TaskExamAnswer();
    partialUpdatedTaskExamAnswer.setId(taskExamAnswer.getId());

    partialUpdatedTaskExamAnswer
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .textContentId(UPDATED_TEXT_CONTENT_ID);

    restTaskExamAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTaskExamAnswer.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedTaskExamAnswer)
          )
      )
      .andExpect(status().isOk());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
    TaskExamAnswer testTaskExamAnswer = taskExamAnswerList.get(
      taskExamAnswerList.size() - 1
    );
    assertThat(testTaskExamAnswer.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testTaskExamAnswer.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testTaskExamAnswer.getTextContentId())
      .isEqualTo(UPDATED_TEXT_CONTENT_ID);
  }

  @Test
  @Transactional
  void patchNonExistingTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswerDTO taskExamAnswerDTO = taskExamAnswerMapper.toDto(
      taskExamAnswer
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, taskExamAnswerDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswerDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswerDTO taskExamAnswerDTO = taskExamAnswerMapper.toDto(
      taskExamAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswerDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTaskExamAnswer() throws Exception {
    int databaseSizeBeforeUpdate = taskExamAnswerRepository.findAll().size();
    taskExamAnswer.setId(count.incrementAndGet());

    // Create the TaskExamAnswer
    TaskExamAnswerDTO taskExamAnswerDTO = taskExamAnswerMapper.toDto(
      taskExamAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamAnswerDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TaskExamAnswer in the database
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteTaskExamAnswer() throws Exception {
    // Initialize the database
    taskExamAnswerRepository.saveAndFlush(taskExamAnswer);

    int databaseSizeBeforeDelete = taskExamAnswerRepository.findAll().size();

    // Delete the taskExamAnswer
    restTaskExamAnswerMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, taskExamAnswer.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TaskExamAnswer> taskExamAnswerList = taskExamAnswerRepository.findAll();
    assertThat(taskExamAnswerList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
