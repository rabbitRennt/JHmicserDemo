package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.TaskExam;
import io.github.jhipster.application.repository.TaskExamRepository;
import io.github.jhipster.application.service.dto.TaskExam;
import io.github.jhipster.application.service.mapper.TaskExamMapper;
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
 * Integration tests for the {@link TaskExamResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaskExamResourceIT {

  private static final String DEFAULT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_TITLE = "BBBBBBBBBB";

  private static final Integer DEFAULT_GRADE_LEVEL = 1;
  private static final Integer UPDATED_GRADE_LEVEL = 2;

  private static final Long DEFAULT_FRAME_TEXT_CONTENT_ID = 1L;
  private static final Long UPDATED_FRAME_TEXT_CONTENT_ID = 2L;

  private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

  private static final String DEFAULT_CREATE_BY_USER_NAME = "AAAAAAAAAA";
  private static final String UPDATED_CREATE_BY_USER_NAME = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final String ENTITY_API_URL = "/api/task-exams";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private TaskExamRepository taskExamRepository;

  @Autowired
  private TaskExamMapper taskExamMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTaskExamMockMvc;

  private TaskExam taskExam;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TaskExam createEntity(EntityManager em) {
    TaskExam taskExam = new TaskExam()
      .title(DEFAULT_TITLE)
      .gradeLevel(DEFAULT_GRADE_LEVEL)
      .frameTextContentId(DEFAULT_FRAME_TEXT_CONTENT_ID)
      .createBy(DEFAULT_CREATE_BY)
      .createByUserName(DEFAULT_CREATE_BY_USER_NAME)
      .createDate(DEFAULT_CREATE_DATE)
      .deleted(DEFAULT_DELETED);
    return taskExam;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TaskExam createUpdatedEntity(EntityManager em) {
    TaskExam taskExam = new TaskExam()
      .title(UPDATED_TITLE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createByUserName(UPDATED_CREATE_BY_USER_NAME)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);
    return taskExam;
  }

  @BeforeEach
  public void initTest() {
    taskExam = createEntity(em);
  }

  @Test
  @Transactional
  void createTaskExam() throws Exception {
    int databaseSizeBeforeCreate = taskExamRepository.findAll().size();
    // Create the TaskExam
    TaskExam taskExam = taskExamMapper.toDto(taskExam);
    restTaskExamMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExam))
      )
      .andExpect(status().isCreated());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeCreate + 1);
    TaskExam testTaskExam = taskExamList.get(taskExamList.size() - 1);
    assertThat(testTaskExam.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testTaskExam.getGradeLevel()).isEqualTo(DEFAULT_GRADE_LEVEL);
    assertThat(testTaskExam.getFrameTextContentId())
      .isEqualTo(DEFAULT_FRAME_TEXT_CONTENT_ID);
    assertThat(testTaskExam.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testTaskExam.getCreateByUserName())
      .isEqualTo(DEFAULT_CREATE_BY_USER_NAME);
    assertThat(testTaskExam.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testTaskExam.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void createTaskExamWithExistingId() throws Exception {
    // Create the TaskExam with an existing ID
    taskExam.setId(1L);
    TaskExam taskExam = taskExamMapper.toDto(taskExam);

    int databaseSizeBeforeCreate = taskExamRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTaskExamMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExam))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllTaskExams() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    // Get all the taskExamList
    restTaskExamMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(taskExam.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
      .andExpect(
        jsonPath("$.[*].gradeLevel").value(hasItem(DEFAULT_GRADE_LEVEL))
      )
      .andExpect(
        jsonPath("$.[*].frameTextContentId")
          .value(hasItem(DEFAULT_FRAME_TEXT_CONTENT_ID.intValue()))
      )
      .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
      .andExpect(
        jsonPath("$.[*].createByUserName")
          .value(hasItem(DEFAULT_CREATE_BY_USER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue()))
      );
  }

  @Test
  @Transactional
  void getTaskExam() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    // Get the taskExam
    restTaskExamMockMvc
      .perform(get(ENTITY_API_URL_ID, taskExam.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(taskExam.getId().intValue()))
      .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
      .andExpect(jsonPath("$.gradeLevel").value(DEFAULT_GRADE_LEVEL))
      .andExpect(
        jsonPath("$.frameTextContentId")
          .value(DEFAULT_FRAME_TEXT_CONTENT_ID.intValue())
      )
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
      .andExpect(
        jsonPath("$.createByUserName").value(DEFAULT_CREATE_BY_USER_NAME)
      )
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingTaskExam() throws Exception {
    // Get the taskExam
    restTaskExamMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTaskExam() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();

    // Update the taskExam
    TaskExam updatedTaskExam = taskExamRepository
      .findById(taskExam.getId())
      .get();
    // Disconnect from session so that the updates on updatedTaskExam are not directly saved in db
    em.detach(updatedTaskExam);
    updatedTaskExam
      .title(UPDATED_TITLE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createByUserName(UPDATED_CREATE_BY_USER_NAME)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);
    TaskExam taskExam = taskExamMapper.toDto(updatedTaskExam);

    restTaskExamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, taskExamDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExamDTO))
      )
      .andExpect(status().isOk());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
    TaskExam testTaskExam = taskExamList.get(taskExamList.size() - 1);
    assertThat(testTaskExam.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTaskExam.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testTaskExam.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testTaskExam.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testTaskExam.getCreateByUserName())
      .isEqualTo(UPDATED_CREATE_BY_USER_NAME);
    assertThat(testTaskExam.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testTaskExam.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void putNonExistingTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExam taskExam = taskExamMapper.toDto(taskExam);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, taskExam.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExam))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExam taskExam = taskExamMapper.toDto(taskExam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExam))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExam taskExam = taskExamMapper.toDto(taskExam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(taskExam))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateTaskExamWithPatch() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();

    // Update the taskExam using partial update
    TaskExam partialUpdatedTaskExam = new TaskExam();
    partialUpdatedTaskExam.setId(taskExam.getId());

    partialUpdatedTaskExam
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID);

    restTaskExamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTaskExam.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskExam))
      )
      .andExpect(status().isOk());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
    TaskExam testTaskExam = taskExamList.get(taskExamList.size() - 1);
    assertThat(testTaskExam.getTitle()).isEqualTo(DEFAULT_TITLE);
    assertThat(testTaskExam.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testTaskExam.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testTaskExam.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testTaskExam.getCreateByUserName())
      .isEqualTo(DEFAULT_CREATE_BY_USER_NAME);
    assertThat(testTaskExam.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testTaskExam.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void fullUpdateTaskExamWithPatch() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();

    // Update the taskExam using partial update
    TaskExam partialUpdatedTaskExam = new TaskExam();
    partialUpdatedTaskExam.setId(taskExam.getId());

    partialUpdatedTaskExam
      .title(UPDATED_TITLE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createByUserName(UPDATED_CREATE_BY_USER_NAME)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);

    restTaskExamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTaskExam.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTaskExam))
      )
      .andExpect(status().isOk());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
    TaskExam testTaskExam = taskExamList.get(taskExamList.size() - 1);
    assertThat(testTaskExam.getTitle()).isEqualTo(UPDATED_TITLE);
    assertThat(testTaskExam.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testTaskExam.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testTaskExam.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testTaskExam.getCreateByUserName())
      .isEqualTo(UPDATED_CREATE_BY_USER_NAME);
    assertThat(testTaskExam.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testTaskExam.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void patchNonExistingTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExamDTO taskExamDTO = taskExamMapper.toDto(taskExam);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, taskExamDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExamDTO taskExamDTO = taskExamMapper.toDto(taskExam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTaskExam() throws Exception {
    int databaseSizeBeforeUpdate = taskExamRepository.findAll().size();
    taskExam.setId(count.incrementAndGet());

    // Create the TaskExam
    TaskExamDTO taskExamDTO = taskExamMapper.toDto(taskExam);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTaskExamMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(taskExamDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TaskExam in the database
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteTaskExam() throws Exception {
    // Initialize the database
    taskExamRepository.saveAndFlush(taskExam);

    int databaseSizeBeforeDelete = taskExamRepository.findAll().size();

    // Delete the taskExam
    restTaskExamMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, taskExam.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TaskExam> taskExamList = taskExamRepository.findAll();
    assertThat(taskExamList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
