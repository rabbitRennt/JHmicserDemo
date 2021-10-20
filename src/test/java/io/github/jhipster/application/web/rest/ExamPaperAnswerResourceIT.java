package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.ExamPaperAnswer;
import io.github.jhipster.application.repository.ExamPaperAnswerRepository;
import io.github.jhipster.application.service.dto.ExamPaperAnswer;
import io.github.jhipster.application.service.mapper.ExamPaperAnswerMapper;
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
 * Integration tests for the {@link ExamPaperAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExamPaperAnswerResourceIT {

  private static final String DEFAULT_PAPER_NAME = "AAAAAAAAAA";
  private static final String UPDATED_PAPER_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_PAPER_TYPE = 1;
  private static final Integer UPDATED_PAPER_TYPE = 2;

  private static final Integer DEFAULT_SYSTEM_SCORE = 1;
  private static final Integer UPDATED_SYSTEM_SCORE = 2;

  private static final Integer DEFAULT_USER_SCORE = 1;
  private static final Integer UPDATED_USER_SCORE = 2;

  private static final Integer DEFAULT_PAPER_SCORE = 1;
  private static final Integer UPDATED_PAPER_SCORE = 2;

  private static final Integer DEFAULT_QUESTION_CORRECT = 1;
  private static final Integer UPDATED_QUESTION_CORRECT = 2;

  private static final Integer DEFAULT_QUESTION_COUNT = 1;
  private static final Integer UPDATED_QUESTION_COUNT = 2;

  private static final Integer DEFAULT_DO_TIME = 1;
  private static final Integer UPDATED_DO_TIME = 2;

  private static final Integer DEFAULT_STATUS = 1;
  private static final Integer UPDATED_STATUS = 2;

  private static final Long DEFAULT_CREATE_BY = 1L;
  private static final Long UPDATED_CREATE_BY = 2L;

  private static final Long DEFAULT_CREATE_DATE = 1L;
  private static final Long UPDATED_CREATE_DATE = 2L;

  private static final String ENTITY_API_URL = "/api/exam-paper-answers";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private ExamPaperAnswerRepository examPaperAnswerRepository;

  @Autowired
  private ExamPaperAnswerMapper examPaperAnswerMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restExamPaperAnswerMockMvc;

  private ExamPaperAnswer examPaperAnswer;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaperAnswer createEntity(EntityManager em) {
    ExamPaperAnswer examPaperAnswer = new ExamPaperAnswer()
      .paperName(DEFAULT_PAPER_NAME)
      .paperType(DEFAULT_PAPER_TYPE)
      .systemScore(DEFAULT_SYSTEM_SCORE)
      .userScore(DEFAULT_USER_SCORE)
      .paperScore(DEFAULT_PAPER_SCORE)
      .questionCorrect(DEFAULT_QUESTION_CORRECT)
      .questionCount(DEFAULT_QUESTION_COUNT)
      .doTime(DEFAULT_DO_TIME)
      .status(DEFAULT_STATUS)
      .createBy(DEFAULT_CREATE_BY)
      .createDate(DEFAULT_CREATE_DATE);
    return examPaperAnswer;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static ExamPaperAnswer createUpdatedEntity(EntityManager em) {
    ExamPaperAnswer examPaperAnswer = new ExamPaperAnswer()
      .paperName(UPDATED_PAPER_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .systemScore(UPDATED_SYSTEM_SCORE)
      .userScore(UPDATED_USER_SCORE)
      .paperScore(UPDATED_PAPER_SCORE)
      .questionCorrect(UPDATED_QUESTION_CORRECT)
      .questionCount(UPDATED_QUESTION_COUNT)
      .doTime(UPDATED_DO_TIME)
      .status(UPDATED_STATUS)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE);
    return examPaperAnswer;
  }

  @BeforeEach
  public void initTest() {
    examPaperAnswer = createEntity(em);
  }

  @Test
  @Transactional
  void createExamPaperAnswer() throws Exception {
    int databaseSizeBeforeCreate = examPaperAnswerRepository.findAll().size();
    // Create the ExamPaperAnswer
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );
    restExamPaperAnswerMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswer))
      )
      .andExpect(status().isCreated());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeCreate + 1);
    ExamPaperAnswer testExamPaperAnswer = examPaperAnswerList.get(
      examPaperAnswerList.size() - 1
    );
    assertThat(testExamPaperAnswer.getPaperName())
      .isEqualTo(DEFAULT_PAPER_NAME);
    assertThat(testExamPaperAnswer.getPaperType())
      .isEqualTo(DEFAULT_PAPER_TYPE);
    assertThat(testExamPaperAnswer.getSystemScore())
      .isEqualTo(DEFAULT_SYSTEM_SCORE);
    assertThat(testExamPaperAnswer.getUserScore())
      .isEqualTo(DEFAULT_USER_SCORE);
    assertThat(testExamPaperAnswer.getPaperScore())
      .isEqualTo(DEFAULT_PAPER_SCORE);
    assertThat(testExamPaperAnswer.getQuestionCorrect())
      .isEqualTo(DEFAULT_QUESTION_CORRECT);
    assertThat(testExamPaperAnswer.getQuestionCount())
      .isEqualTo(DEFAULT_QUESTION_COUNT);
    assertThat(testExamPaperAnswer.getDoTime()).isEqualTo(DEFAULT_DO_TIME);
    assertThat(testExamPaperAnswer.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testExamPaperAnswer.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testExamPaperAnswer.getCreateDate())
      .isEqualTo(DEFAULT_CREATE_DATE);
  }

  @Test
  @Transactional
  void createExamPaperAnswerWithExistingId() throws Exception {
    // Create the ExamPaperAnswer with an existing ID
    examPaperAnswer.setId(1L);
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    int databaseSizeBeforeCreate = examPaperAnswerRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restExamPaperAnswerMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllExamPaperAnswers() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    // Get all the examPaperAnswerList
    restExamPaperAnswerMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(examPaperAnswer.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].paperName").value(hasItem(DEFAULT_PAPER_NAME)))
      .andExpect(jsonPath("$.[*].paperType").value(hasItem(DEFAULT_PAPER_TYPE)))
      .andExpect(
        jsonPath("$.[*].systemScore").value(hasItem(DEFAULT_SYSTEM_SCORE))
      )
      .andExpect(jsonPath("$.[*].userScore").value(hasItem(DEFAULT_USER_SCORE)))
      .andExpect(
        jsonPath("$.[*].paperScore").value(hasItem(DEFAULT_PAPER_SCORE))
      )
      .andExpect(
        jsonPath("$.[*].questionCorrect")
          .value(hasItem(DEFAULT_QUESTION_CORRECT))
      )
      .andExpect(
        jsonPath("$.[*].questionCount").value(hasItem(DEFAULT_QUESTION_COUNT))
      )
      .andExpect(jsonPath("$.[*].doTime").value(hasItem(DEFAULT_DO_TIME)))
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(
        jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.intValue()))
      )
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.intValue()))
      );
  }

  @Test
  @Transactional
  void getExamPaperAnswer() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    // Get the examPaperAnswer
    restExamPaperAnswerMockMvc
      .perform(get(ENTITY_API_URL_ID, examPaperAnswer.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(examPaperAnswer.getId().intValue()))
      .andExpect(jsonPath("$.paperName").value(DEFAULT_PAPER_NAME))
      .andExpect(jsonPath("$.paperType").value(DEFAULT_PAPER_TYPE))
      .andExpect(jsonPath("$.systemScore").value(DEFAULT_SYSTEM_SCORE))
      .andExpect(jsonPath("$.userScore").value(DEFAULT_USER_SCORE))
      .andExpect(jsonPath("$.paperScore").value(DEFAULT_PAPER_SCORE))
      .andExpect(jsonPath("$.questionCorrect").value(DEFAULT_QUESTION_CORRECT))
      .andExpect(jsonPath("$.questionCount").value(DEFAULT_QUESTION_COUNT))
      .andExpect(jsonPath("$.doTime").value(DEFAULT_DO_TIME))
      .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.intValue()))
      .andExpect(
        jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.intValue())
      );
  }

  @Test
  @Transactional
  void getNonExistingExamPaperAnswer() throws Exception {
    // Get the examPaperAnswer
    restExamPaperAnswerMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewExamPaperAnswer() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();

    // Update the examPaperAnswer
    ExamPaperAnswer updatedExamPaperAnswer = examPaperAnswerRepository
      .findById(examPaperAnswer.getId())
      .get();
    // Disconnect from session so that the updates on updatedExamPaperAnswer are not directly saved in db
    em.detach(updatedExamPaperAnswer);
    updatedExamPaperAnswer
      .paperName(UPDATED_PAPER_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .systemScore(UPDATED_SYSTEM_SCORE)
      .userScore(UPDATED_USER_SCORE)
      .paperScore(UPDATED_PAPER_SCORE)
      .questionCorrect(UPDATED_QUESTION_CORRECT)
      .questionCount(UPDATED_QUESTION_COUNT)
      .doTime(UPDATED_DO_TIME)
      .status(UPDATED_STATUS)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE);
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      updatedExamPaperAnswer
    );

    restExamPaperAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaperAnswerDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDTO))
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswer testExamPaperAnswer = examPaperAnswerList.get(
      examPaperAnswerList.size() - 1
    );
    assertThat(testExamPaperAnswer.getPaperName())
      .isEqualTo(UPDATED_PAPER_NAME);
    assertThat(testExamPaperAnswer.getPaperType())
      .isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaperAnswer.getSystemScore())
      .isEqualTo(UPDATED_SYSTEM_SCORE);
    assertThat(testExamPaperAnswer.getUserScore())
      .isEqualTo(UPDATED_USER_SCORE);
    assertThat(testExamPaperAnswer.getPaperScore())
      .isEqualTo(UPDATED_PAPER_SCORE);
    assertThat(testExamPaperAnswer.getQuestionCorrect())
      .isEqualTo(UPDATED_QUESTION_CORRECT);
    assertThat(testExamPaperAnswer.getQuestionCount())
      .isEqualTo(UPDATED_QUESTION_COUNT);
    assertThat(testExamPaperAnswer.getDoTime()).isEqualTo(UPDATED_DO_TIME);
    assertThat(testExamPaperAnswer.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testExamPaperAnswer.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaperAnswer.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
  }

  @Test
  @Transactional
  void putNonExistingExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, examPaperAnswer.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswer))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswer))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateExamPaperAnswerWithPatch() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();

    // Update the examPaperAnswer using partial update
    ExamPaperAnswer partialUpdatedExamPaperAnswer = new ExamPaperAnswer();
    partialUpdatedExamPaperAnswer.setId(examPaperAnswer.getId());

    partialUpdatedExamPaperAnswer
      .paperType(UPDATED_PAPER_TYPE)
      .paperScore(UPDATED_PAPER_SCORE)
      .questionCorrect(UPDATED_QUESTION_CORRECT)
      .status(UPDATED_STATUS);

    restExamPaperAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaperAnswer.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaperAnswer)
          )
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswer testExamPaperAnswer = examPaperAnswerList.get(
      examPaperAnswerList.size() - 1
    );
    assertThat(testExamPaperAnswer.getPaperName())
      .isEqualTo(DEFAULT_PAPER_NAME);
    assertThat(testExamPaperAnswer.getPaperType())
      .isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaperAnswer.getSystemScore())
      .isEqualTo(DEFAULT_SYSTEM_SCORE);
    assertThat(testExamPaperAnswer.getUserScore())
      .isEqualTo(DEFAULT_USER_SCORE);
    assertThat(testExamPaperAnswer.getPaperScore())
      .isEqualTo(UPDATED_PAPER_SCORE);
    assertThat(testExamPaperAnswer.getQuestionCorrect())
      .isEqualTo(UPDATED_QUESTION_CORRECT);
    assertThat(testExamPaperAnswer.getQuestionCount())
      .isEqualTo(DEFAULT_QUESTION_COUNT);
    assertThat(testExamPaperAnswer.getDoTime()).isEqualTo(DEFAULT_DO_TIME);
    assertThat(testExamPaperAnswer.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testExamPaperAnswer.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testExamPaperAnswer.getCreateDate())
      .isEqualTo(DEFAULT_CREATE_DATE);
  }

  @Test
  @Transactional
  void fullUpdateExamPaperAnswerWithPatch() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();

    // Update the examPaperAnswer using partial update
    ExamPaperAnswer partialUpdatedExamPaperAnswer = new ExamPaperAnswer();
    partialUpdatedExamPaperAnswer.setId(examPaperAnswer.getId());

    partialUpdatedExamPaperAnswer
      .paperName(UPDATED_PAPER_NAME)
      .paperType(UPDATED_PAPER_TYPE)
      .systemScore(UPDATED_SYSTEM_SCORE)
      .userScore(UPDATED_USER_SCORE)
      .paperScore(UPDATED_PAPER_SCORE)
      .questionCorrect(UPDATED_QUESTION_CORRECT)
      .questionCount(UPDATED_QUESTION_COUNT)
      .doTime(UPDATED_DO_TIME)
      .status(UPDATED_STATUS)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE);

    restExamPaperAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedExamPaperAnswer.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedExamPaperAnswer)
          )
      )
      .andExpect(status().isOk());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
    ExamPaperAnswer testExamPaperAnswer = examPaperAnswerList.get(
      examPaperAnswerList.size() - 1
    );
    assertThat(testExamPaperAnswer.getPaperName())
      .isEqualTo(UPDATED_PAPER_NAME);
    assertThat(testExamPaperAnswer.getPaperType())
      .isEqualTo(UPDATED_PAPER_TYPE);
    assertThat(testExamPaperAnswer.getSystemScore())
      .isEqualTo(UPDATED_SYSTEM_SCORE);
    assertThat(testExamPaperAnswer.getUserScore())
      .isEqualTo(UPDATED_USER_SCORE);
    assertThat(testExamPaperAnswer.getPaperScore())
      .isEqualTo(UPDATED_PAPER_SCORE);
    assertThat(testExamPaperAnswer.getQuestionCorrect())
      .isEqualTo(UPDATED_QUESTION_CORRECT);
    assertThat(testExamPaperAnswer.getQuestionCount())
      .isEqualTo(UPDATED_QUESTION_COUNT);
    assertThat(testExamPaperAnswer.getDoTime()).isEqualTo(UPDATED_DO_TIME);
    assertThat(testExamPaperAnswer.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testExamPaperAnswer.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testExamPaperAnswer.getCreateDate())
      .isEqualTo(UPDATED_CREATE_DATE);
  }

  @Test
  @Transactional
  void patchNonExistingExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswerDTO examPaperAnswerDTO = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, examPaperAnswerDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswerDTO examPaperAnswerDTO = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamExamPaperAnswer() throws Exception {
    int databaseSizeBeforeUpdate = examPaperAnswerRepository.findAll().size();
    examPaperAnswer.setId(count.incrementAndGet());

    // Create the ExamPaperAnswer
    ExamPaperAnswerDTO examPaperAnswerDTO = examPaperAnswerMapper.toDto(
      examPaperAnswer
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restExamPaperAnswerMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(examPaperAnswerDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the ExamPaperAnswer in the database
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteExamPaperAnswer() throws Exception {
    // Initialize the database
    examPaperAnswerRepository.saveAndFlush(examPaperAnswer);

    int databaseSizeBeforeDelete = examPaperAnswerRepository.findAll().size();

    // Delete the examPaperAnswer
    restExamPaperAnswerMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, examPaperAnswer.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<ExamPaperAnswer> examPaperAnswerList = examPaperAnswerRepository.findAll();
    assertThat(examPaperAnswerList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
