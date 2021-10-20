package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.ExamPaperAnswerDef;
import io.github.jhipster.application.repository.ExamPaperAnswerDefRepository;
import io.github.jhipster.application.service.dto.ExamPaperAnswerDef;
import io.github.jhipster.application.service.mapper.ExamPaperAnswerDefMapper;
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
 * Integration tests for the {@link ExamPaperAnswerDefResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExamPaperAnswerDefResourceIT {

  private static final Integer DEFAULT_QUESTION_TYPE = 1;
  private static final Integer UPDATED_QUESTION_TYPE = 2;

  private static final Integer DEFAULT_CUSTOMER_SCORE = 1;
  private static final Integer UPDATED_CUSTOMER_SCORE = 2;

  private static final Integer DEFAULT_QUESTION_SCORE = 1;
  private static final Integer UPDATED_QUESTION_SCORE = 2;

  private static final Long DEFAULT_QUESTION_TEXT_CONTENT_ID = 1L;
  private static final Long UPDATED_QUESTION_TEXT_CONTENT_ID = 2L;

  private static final String DEFAULT_ANSWER = "AAAAAAAAAA";
  private static final String UPDATED_ANSWER = "BBBBBBBBBB";

  private static final Boolean DEFAULT_EXT_CONTE = false;
  private static final Boolean UPDATED_EXT_CONTE = true;

  private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Integer DEFAULT_ITEM_ORDER = 1;
  private static final Integer UPDATED_ITEM_ORDER = 2;

  private static final String ENTITY_API_URL = "/api/exam-paper-answer-defs";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private ExamPaperAnswerDefRepository examPaperAnswerDefRepository;

  @Autowired
  private ExamPaperAnswerDefMapper examPaperAnswerDefMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restExamPaperAnswerDefMockMvc;

  private ExamPaperAnswerDef examPaperAnswerDef;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaperAnswerDef createEntity(EntityManager em) {
    ExamPaperAnswerDef examPaperAnswerDef = new ExamPaperAnswerDef()
      .questionType(DEFAULT_QUESTION_TYPE)
      .customerScore(DEFAULT_CUSTOMER_SCORE)
      .questionScore(DEFAULT_QUESTION_SCORE)
      .questionTextContentId(DEFAULT_QUESTION_TEXT_CONTENT_ID)
      .answer(DEFAULT_ANSWER)
      .extConte(DEFAULT_EXT_CONTE)
      .createBy(DEFAULT_CREATE_BY)
      .createDate(DEFAULT_CREATE_DATE)
      .itemOrder(DEFAULT_ITEM_ORDER);
    return examPaperAnswerDef;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaperAnswerDef createUpdatedEntity(EntityManager em) {
    ExamPaperAnswerDef examPaperAnswerDef = new ExamPaperAnswerDef()
      .questionType(UPDATED_QUESTION_TYPE)
      .customerScore(UPDATED_CUSTOMER_SCORE)
      .questionScore(UPDATED_QUESTION_SCORE)
      .questionTextContentId(UPDATED_QUESTION_TEXT_CONTENT_ID)
      .answer(UPDATED_ANSWER)
      .extConte(UPDATED_EXT_CONTE)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .itemOrder(UPDATED_ITEM_ORDER);
    return examPaperAnswerDef;
  }

  @BeforeEach
  public void initTest() {
    examPaperAnswerDef = createEntity(em);
  }

  @Test
  @Transactional
  void createExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeCreate = examPaperAnswerDefRepository
      .findAll()
      .size();
    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );
    restExamPaperAnswerDefMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDef))
      )
      .andExpect(status().isCreated());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeCreate + 1);
    ExamPaperAnswerDef testExamPaperAnswerDef = examPaperAnswerDefList.get(
      examPaperAnswerDefList.size() - 1
    );
    assertThat(testExamPaperAnswerDef.getQuestionType())
      .isEqualTo(DEFAULT_QUESTION_TYPE);
    assertThat(testExamPaperAnswerDef.getCustomerScore())
      .isEqualTo(DEFAULT_CUSTOMER_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionScore())
      .isEqualTo(DEFAULT_QUESTION_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionTextContentId())
      .isEqualTo(DEFAULT_QUESTION_TEXT_CONTENT_ID);
    assertThat(testExamPaperAnswerDef.getAnswer()).isEqualTo(DEFAULT_ANSWER);
    assertThat(testExamPaperAnswerDef.getExtConte())
      .isEqualTo(DEFAULT_EXT_CONTE);
    assertThat(testExamPaperAnswerDef.getCreateBy())
      .isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testExamPaperAnswerDef.getCreateDate())
      .isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testExamPaperAnswerDef.getItemOrder())
      .isEqualTo(DEFAULT_ITEM_ORDER);
  }

  @Test
  @Transactional
  void createExamPaperAnswerDefWithExistingId() throws Exception {
    // Create the ExamPaperAnswerDef with an existing ID
    examPaperAnswerDef.setId(1L);
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    int databaseSizeBeforeCreate = examPaperAnswerDefRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restExamPaperAnswerDefMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDef))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllExamPaperAnswerDefs() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    // Get all the examPaperAnswerDefList
    restExamPaperAnswerDefMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(examPaperAnswerDef.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].questionType").value(hasItem(DEFAULT_QUESTION_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].customerScore").value(hasItem(DEFAULT_CUSTOMER_SCORE))
      )
      .andExpect(
        jsonPath("$.[*].questionScore").value(hasItem(DEFAULT_QUESTION_SCORE))
      )
      .andExpect(
        jsonPath("$.[*].questionTextContentId")
          .value(hasItem(DEFAULT_QUESTION_TEXT_CONTENT_ID.intValue()))
      )
      .andExpect(jsonPath("$.[*].answer").value(hasItem(DEFAULT_ANSWER)))
      .andExpect(
        jsonPath("$.[*].extConte")
          .value(hasItem(DEFAULT_EXT_CONTE.booleanValue()))
      )
      .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].itemOrder").value(hasItem(DEFAULT_ITEM_ORDER))
      );
  }

  @Test
  @Transactional
  void getExamPaperAnswerDef() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    // Get the examPaperAnswerDef
    restExamPaperAnswerDefMockMvc
      .perform(get(ENTITY_API_URL_ID, examPaperAnswerDef.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(examPaperAnswerDef.getId().intValue()))
      .andExpect(jsonPath("$.questionType").value(DEFAULT_QUESTION_TYPE))
      .andExpect(jsonPath("$.customerScore").value(DEFAULT_CUSTOMER_SCORE))
      .andExpect(jsonPath("$.questionScore").value(DEFAULT_QUESTION_SCORE))
      .andExpect(
        jsonPath("$.questionTextContentId")
          .value(DEFAULT_QUESTION_TEXT_CONTENT_ID.intValue())
      )
      .andExpect(jsonPath("$.answer").value(DEFAULT_ANSWER))
      .andExpect(jsonPath("$.extConte").value(DEFAULT_EXT_CONTE.booleanValue()))
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(jsonPath("$.itemOrder").value(DEFAULT_ITEM_ORDER));
  }

  @Test
  @Transactional
  void getNonExistingExamPaperAnswerDef() throws Exception {
    // Get the examPaperAnswerDef
    restExamPaperAnswerDefMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewExamPaperAnswerDef() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();

    // Update the examPaperAnswerDef
    ExamPaperAnswerDef updatedExamPaperAnswerDef = examPaperAnswerDefRepository
      .findById(examPaperAnswerDef.getId())
      .get();
    // Disconnect from session so that the updates on updatedExamPaperAnswerDef are not directly saved in db
    em.detach(updatedExamPaperAnswerDef);
    updatedExamPaperAnswerDef
      .questionType(UPDATED_QUESTION_TYPE)
      .customerScore(UPDATED_CUSTOMER_SCORE)
      .questionScore(UPDATED_QUESTION_SCORE)
      .questionTextContentId(UPDATED_QUESTION_TEXT_CONTENT_ID)
      .answer(UPDATED_ANSWER)
      .extConte(UPDATED_EXT_CONTE)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .itemOrder(UPDATED_ITEM_ORDER);
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      updatedExamPaperAnswerDef
    );

    restExamPaperAnswerDefMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaperAnswerDefDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDefDTO))
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswerDef testExamPaperAnswerDef = examPaperAnswerDefList.get(
      examPaperAnswerDefList.size() - 1
    );
    assertThat(testExamPaperAnswerDef.getQuestionType())
      .isEqualTo(UPDATED_QUESTION_TYPE);
    assertThat(testExamPaperAnswerDef.getCustomerScore())
      .isEqualTo(UPDATED_CUSTOMER_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionScore())
      .isEqualTo(UPDATED_QUESTION_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionTextContentId())
      .isEqualTo(UPDATED_QUESTION_TEXT_CONTENT_ID);
    assertThat(testExamPaperAnswerDef.getAnswer()).isEqualTo(UPDATED_ANSWER);
    assertThat(testExamPaperAnswerDef.getExtConte())
      .isEqualTo(UPDATED_EXT_CONTE);
    assertThat(testExamPaperAnswerDef.getCreateBy())
      .isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaperAnswerDef.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaperAnswerDef.getItemOrder())
      .isEqualTo(UPDATED_ITEM_ORDER);
  }

  @Test
  @Transactional
  void putNonExistingExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaperAnswerDef.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDef))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDef))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDef examPaperAnswerDef = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDef))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateExamPaperAnswerDefWithPatch() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();

    // Update the examPaperAnswerDef using partial update
    ExamPaperAnswerDef partialUpdatedExamPaperAnswerDef = new ExamPaperAnswerDef();
    partialUpdatedExamPaperAnswerDef.setId(examPaperAnswerDef.getId());

    partialUpdatedExamPaperAnswerDef
      .customerScore(UPDATED_CUSTOMER_SCORE)
      .questionTextContentId(UPDATED_QUESTION_TEXT_CONTENT_ID)
      .answer(UPDATED_ANSWER)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE);

    restExamPaperAnswerDefMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaperAnswerDef.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaperAnswerDef)
          )
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswerDef testExamPaperAnswerDef = examPaperAnswerDefList.get(
      examPaperAnswerDefList.size() - 1
    );
    assertThat(testExamPaperAnswerDef.getQuestionType())
      .isEqualTo(DEFAULT_QUESTION_TYPE);
    assertThat(testExamPaperAnswerDef.getCustomerScore())
      .isEqualTo(UPDATED_CUSTOMER_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionScore())
      .isEqualTo(DEFAULT_QUESTION_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionTextContentId())
      .isEqualTo(UPDATED_QUESTION_TEXT_CONTENT_ID);
    assertThat(testExamPaperAnswerDef.getAnswer()).isEqualTo(UPDATED_ANSWER);
    assertThat(testExamPaperAnswerDef.getExtConte())
      .isEqualTo(DEFAULT_EXT_CONTE);
    assertThat(testExamPaperAnswerDef.getCreateBy())
      .isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaperAnswerDef.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaperAnswerDef.getItemOrder())
      .isEqualTo(DEFAULT_ITEM_ORDER);
  }

  @Test
  @Transactional
  void fullUpdateExamPaperAnswerDefWithPatch() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();

    // Update the examPaperAnswerDef using partial update
    ExamPaperAnswerDef partialUpdatedExamPaperAnswerDef = new ExamPaperAnswerDef();
    partialUpdatedExamPaperAnswerDef.setId(examPaperAnswerDef.getId());

    partialUpdatedExamPaperAnswerDef
      .questionType(UPDATED_QUESTION_TYPE)
      .customerScore(UPDATED_CUSTOMER_SCORE)
      .questionScore(UPDATED_QUESTION_SCORE)
      .questionTextContentId(UPDATED_QUESTION_TEXT_CONTENT_ID)
      .answer(UPDATED_ANSWER)
      .extConte(UPDATED_EXT_CONTE)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .itemOrder(UPDATED_ITEM_ORDER);

    restExamPaperAnswerDefMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaperAnswerDef.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaperAnswerDef)
          )
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswerDef testExamPaperAnswerDef = examPaperAnswerDefList.get(
      examPaperAnswerDefList.size() - 1
    );
    assertThat(testExamPaperAnswerDef.getQuestionType())
      .isEqualTo(UPDATED_QUESTION_TYPE);
    assertThat(testExamPaperAnswerDef.getCustomerScore())
      .isEqualTo(UPDATED_CUSTOMER_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionScore())
      .isEqualTo(UPDATED_QUESTION_SCORE);
    assertThat(testExamPaperAnswerDef.getQuestionTextContentId())
      .isEqualTo(UPDATED_QUESTION_TEXT_CONTENT_ID);
    assertThat(testExamPaperAnswerDef.getAnswer()).isEqualTo(UPDATED_ANSWER);
    assertThat(testExamPaperAnswerDef.getExtConte())
      .isEqualTo(UPDATED_EXT_CONTE);
    assertThat(testExamPaperAnswerDef.getCreateBy())
      .isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaperAnswerDef.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testExamPaperAnswerDef.getItemOrder())
      .isEqualTo(UPDATED_ITEM_ORDER);
  }

  @Test
  @Transactional
  void patchNonExistingExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDefDTO examPaperAnswerDefDTO = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, examPaperAnswerDefDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDefDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDefDTO examPaperAnswerDefDTO = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDefDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamExamPaperAnswerDef() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerDefRepository
      .findAll()
      .size();
    examPaperAnswerDef.setId(count.incrementAndGet());

    // Create the ExamPaperAnswerDef
    ExamPaperAnswerDefDTO examPaperAnswerDefDTO = examPaperAnswerDefMapper.toDto(
      examPaperAnswerDef
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerDefMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDefDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaperAnswerDef in the database
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteExamPaperAnswerDef() throws Exception {
    // Initialize the database
    examPaperAnswerDefRepository.saveAndFlush(examPaperAnswerDef);

    int databaseSizeBeforeDelete = examPaperAnswerDefRepository
      .findAll()
      .size();

    // Delete the examPaperAnswerDef
    restExamPaperAnswerDefMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, examPaperAnswerDef.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ExamPaperAnswerDef> examPaperAnswerDefList = examPaperAnswerDefRepository.findAll();
    assertThat(examPaperAnswerDefList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
