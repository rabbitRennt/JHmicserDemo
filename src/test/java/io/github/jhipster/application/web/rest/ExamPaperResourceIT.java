package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.ExamPaper;
import io.github.jhipster.application.repository.ExamPaperRepository;
import io.github.jhipster.application.service.dto.ExamPaper;
import io.github.jhipster.application.service.mapper.ExamPaperMapper;
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
 * Integration tests for the {@link ExamPaperResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExamPaperResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_PAPER_TYPE = 1;
  private static final Integer UPDATED_PAPER_TYPE = 2;

  private static final Integer DEFAULT_GRADE_LEVEL = 1;
  private static final Integer UPDATED_GRADE_LEVEL = 2;

  private static final Integer DEFAULT_SCORE = 1;
  private static final Integer UPDATED_SCORE = 2;

  private static final Integer DEFAULT_QUESTION_COUNT = 1;
  private static final Integer UPDATED_QUESTION_COUNT = 2;

  private static final Integer DEFAULT_SUGGEST_TIME = 1;
  private static final Integer UPDATED_SUGGEST_TIME = 2;

  private static final Instant DEFAULT_LIMIT_START_DATE = Instant.ofEpochMilli(
    0L
  );
  private static final Instant UPDATED_LIMIT_START_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_LIMIT_ENE_DATE = Instant.ofEpochMilli(
    0L
  );
  private static final Instant UPDATED_LIMIT_ENE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Integer DEFAULT_FRAME_TEXT_CONTENT_ID = 1;
  private static final Integer UPDATED_FRAME_TEXT_CONTENT_ID = 2;

  private static final Long DEFAULT_CREATE_BY = 1L;
  private static final Long UPDATED_CREATE_BY = 2L;

  private static final Long DEFAULT_CREATE_DATE = 1L;
  private static final Long UPDATED_CREATE_DATE = 2L;

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final String ENTITY_API_URL = "/api/exam-papers";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private ExamPaperRepository examPaperRepository;

  @Autowired
  private ExamPaperMapper examPaperMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restExamPaperMockMvc;

  private ExamPaper examPaper;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaper createEntity(EntityManager em) {
    ExamPaper examPaper = new ExamPaper()
      .name(DEFAULT_NAME)
      .paperType(DEFAULT_PAPER_TYPE)
      .gradeLevel(DEFAULT_GRADE_LEVEL)
      .score(DEFAULT_SCORE)
      .questionCount(DEFAULT_QUESTION_COUNT)
      .suggestTime(DEFAULT_SUGGEST_TIME)
      .limitStartDate(DEFAULT_LIMIT_START_DATE)
      .limitEneDate(DEFAULT_LIMIT_ENE_DATE)
      .frameTextContentId(DEFAULT_FRAME_TEXT_CONTENT_ID)
      .createBy(DEFAULT_CREATE_BY)
      .createDate(DEFAULT_CREATE_DATE)
      .deleted(DEFAULT_DELETED);
    return examPaper;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaper createUpdatedEntity(EntityManager em) {
    ExamPaper examPaper = new ExamPaper()
      .name(UPDATED_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .score(UPDATED_SCORE)
      .questionCount(UPDATED_QUESTION_COUNT)
      .suggestTime(UPDATED_SUGGEST_TIME)
      .limitStartDate(UPDATED_LIMIT_START_DATE)
      .limitEneDate(UPDATED_LIMIT_ENE_DATE)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);
    return examPaper;
  }

  @BeforeEach
  public void initTest() {
    examPaper = createEntity(em);
  }

  @Test
  @Transactional
  void createExamPaper() throws Exception {
    int databaseSizeBeforeCreate = examPaperRepository.findAll().size();
    // Create the ExamPaper
    ExamPaper examPaper = examPaperMapper.toDto(examPaper);
    restExamPaperMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaper))
      )
      .andExpect(status().isCreated());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeCreate + 1);
    ExamPaper testExamPaper = examPaperList.get(examPaperList.size() - 1);
    assertThat(testExamPaper.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testExamPaper.getPaperType()).isEqualTo(DEFAULT_PAPER_TYPE);
    assertThat(testExamPaper.getGradeLevel()).isEqualTo(DEFAULT_GRADE_LEVEL);
    assertThat(testExamPaper.getScore()).isEqualTo(DEFAULT_SCORE);
    assertThat(testExamPaper.getQuestionCount())
      .isEqualTo(DEFAULT_QUESTION_COUNT);
    assertThat(testExamPaper.getSuggestTime()).isEqualTo(DEFAULT_SUGGEST_TIME);
    assertThat(testExamPaper.getLimitStartDate())
      .isEqualTo(DEFAULT_LIMIT_START_DATE);
    assertThat(testExamPaper.getLimitEneDate())
      .isEqualTo(DEFAULT_LIMIT_ENE_DATE);
    assertThat(testExamPaper.getFrameTextContentId())
      .isEqualTo(DEFAULT_FRAME_TEXT_CONTENT_ID);
    assertThat(testExamPaper.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testExamPaper.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testExamPaper.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void createExamPaperWithExistingId() throws Exception {
    // Create the ExamPaper with an existing ID
    examPaper.setId(1L);
    ExamPaper examPaper = examPaperMapper.toDto(examPaper);

    int databaseSizeBeforeCreate = examPaperRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restExamPaperMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaper))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllExamPapers() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    // Get all the examPaperList
    restExamPaperMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(examPaper.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].paperType").value(hasItem(DEFAULT_PAPER_TYPE)))
      .andExpect(
        jsonPath("$.[*].gradeLevel").value(hasItem(DEFAULT_GRADE_LEVEL))
      )
      .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
      .andExpect(
        jsonPath("$.[*].questionCount").value(hasItem(DEFAULT_QUESTION_COUNT))
      )
      .andExpect(
        jsonPath("$.[*].suggestTime").value(hasItem(DEFAULT_SUGGEST_TIME))
      )
      .andExpect(
        jsonPath("$.[*].limitStartDate")
          .value(hasItem(DEFAULT_LIMIT_START_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].limitEneDate")
          .value(hasItem(DEFAULT_LIMIT_ENE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].frameTextContentId")
          .value(hasItem(DEFAULT_FRAME_TEXT_CONTENT_ID))
      )
      .andExpect(
        jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.intValue()))
      )
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.intValue()))
      )
      .andExpect(
        jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue()))
      );
  }

  @Test
  @Transactional
  void getExamPaper() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    // Get the examPaper
    restExamPaperMockMvc
      .perform(get(ENTITY_API_URL_ID, examPaper.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(examPaper.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.paperType").value(DEFAULT_PAPER_TYPE))
      .andExpect(jsonPath("$.gradeLevel").value(DEFAULT_GRADE_LEVEL))
      .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
      .andExpect(jsonPath("$.questionCount").value(DEFAULT_QUESTION_COUNT))
      .andExpect(jsonPath("$.suggestTime").value(DEFAULT_SUGGEST_TIME))
      .andExpect(
        jsonPath("$.limitStartDate").value(DEFAULT_LIMIT_START_DATE.toString())
      )
      .andExpect(
        jsonPath("$.limitEneDate").value(DEFAULT_LIMIT_ENE_DATE.toString())
      )
      .andExpect(
        jsonPath("$.frameTextContentId").value(DEFAULT_FRAME_TEXT_CONTENT_ID)
      )
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.intValue()))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.intValue()))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingExamPaper() throws Exception {
    // Get the examPaper
    restExamPaperMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewExamPaper() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();

    // Update the examPaper
    ExamPaper updatedExamPaper = examPaperRepository
      .findById(examPaper.getId())
      .get();
    // Disconnect from session so that the updates on updatedExamPaper are not directly saved in db
    em.detach(updatedExamPaper);
    updatedExamPaper
      .name(UPDATED_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .score(UPDATED_SCORE)
      .questionCount(UPDATED_QUESTION_COUNT)
      .suggestTime(UPDATED_SUGGEST_TIME)
      .limitStartDate(UPDATED_LIMIT_START_DATE)
      .limitEneDate(UPDATED_LIMIT_ENE_DATE)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);
    ExamPaper examPaper = examPaperMapper.toDto(updatedExamPaper);

    restExamPaperMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaperDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperDTO))
      )
      .andExpect(status().isOk());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
    ExamPaper testExamPaper = examPaperList.get(examPaperList.size() - 1);
    assertThat(testExamPaper.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testExamPaper.getPaperType()).isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaper.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testExamPaper.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testExamPaper.getQuestionCount())
      .isEqualTo(UPDATED_QUESTION_COUNT);
    assertThat(testExamPaper.getSuggestTime()).isEqualTo(UPDATED_SUGGEST_TIME);
    assertThat(testExamPaper.getLimitStartDate())
      .isEqualTo(UPDATED_LIMIT_START_DATE);
    assertThat(testExamPaper.getLimitEneDate())
      .isEqualTo(UPDATED_LIMIT_ENE_DATE);
    assertThat(testExamPaper.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testExamPaper.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaper.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaper.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void putNonExistingExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaper examPaper = examPaperMapper.toDto(examPaper);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaper.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaper))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaper examPaper = examPaperMapper.toDto(examPaper);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaper))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaper examPaper = examPaperMapper.toDto(examPaper);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaper))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateExamPaperWithPatch() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();

    // Update the examPaper using partial update
    ExamPaper partialUpdatedExamPaper = new ExamPaper();
    partialUpdatedExamPaper.setId(examPaper.getId());

    partialUpdatedExamPaper
      .paperType(UPDATED_PAPER_TYPE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .score(UPDATED_SCORE)
      .suggestTime(UPDATED_SUGGEST_TIME)
      .limitEneDate(UPDATED_LIMIT_ENE_DATE)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);

    restExamPaperMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaper.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaper))
      )
      .andExpect(status().isOk());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
    ExamPaper testExamPaper = examPaperList.get(examPaperList.size() - 1);
    assertThat(testExamPaper.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testExamPaper.getPaperType()).isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaper.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testExamPaper.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testExamPaper.getQuestionCount())
      .isEqualTo(DEFAULT_QUESTION_COUNT);
    assertThat(testExamPaper.getSuggestTime()).isEqualTo(UPDATED_SUGGEST_TIME);
    assertThat(testExamPaper.getLimitStartDate())
      .isEqualTo(DEFAULT_LIMIT_START_DATE);
    assertThat(testExamPaper.getLimitEneDate())
      .isEqualTo(UPDATED_LIMIT_ENE_DATE);
    assertThat(testExamPaper.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testExamPaper.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testExamPaper.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaper.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void fullUpdateExamPaperWithPatch() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();

    // Update the examPaper using partial update
    ExamPaper partialUpdatedExamPaper = new ExamPaper();
    partialUpdatedExamPaper.setId(examPaper.getId());

    partialUpdatedExamPaper
      .name(UPDATED_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .score(UPDATED_SCORE)
      .questionCount(UPDATED_QUESTION_COUNT)
      .suggestTime(UPDATED_SUGGEST_TIME)
      .limitStartDate(UPDATED_LIMIT_START_DATE)
      .limitEneDate(UPDATED_LIMIT_ENE_DATE)
      .frameTextContentId(UPDATED_FRAME_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);

    restExamPaperMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaper.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaper))
      )
      .andExpect(status().isOk());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
    ExamPaper testExamPaper = examPaperList.get(examPaperList.size() - 1);
    assertThat(testExamPaper.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testExamPaper.getPaperType()).isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaper.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testExamPaper.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testExamPaper.getQuestionCount())
      .isEqualTo(UPDATED_QUESTION_COUNT);
    assertThat(testExamPaper.getSuggestTime()).isEqualTo(UPDATED_SUGGEST_TIME);
    assertThat(testExamPaper.getLimitStartDate())
      .isEqualTo(UPDATED_LIMIT_START_DATE);
    assertThat(testExamPaper.getLimitEneDate())
      .isEqualTo(UPDATED_LIMIT_ENE_DATE);
    assertThat(testExamPaper.getFrameTextContentId())
      .isEqualTo(UPDATED_FRAME_TEXT_CONTENT_ID);
    assertThat(testExamPaper.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaper.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaper.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void patchNonExistingExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaperDTO examPaperDTO = examPaperMapper.toDto(examPaper);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, examPaperDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaperDTO examPaperDTO = examPaperMapper.toDto(examPaper);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamExamPaper() throws Exception {
    int databaseSizeBeforeUpdate = examPaperRepository.findAll().size();
    examPaper.setId(count.incrementAndGet());

    // Create the ExamPaper
    ExamPaperDTO examPaperDTO = examPaperMapper.toDto(examPaper);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaper in the database
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteExamPaper() throws Exception {
    // Initialize the database
    examPaperRepository.saveAndFlush(examPaper);

    int databaseSizeBeforeDelete = examPaperRepository.findAll().size();

    // Delete the examPaper
    restExamPaperMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, examPaper.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ExamPaper> examPaperList = examPaperRepository.findAll();
    assertThat(examPaperList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
