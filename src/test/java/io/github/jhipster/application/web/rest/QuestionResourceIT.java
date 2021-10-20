package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.Question;
import io.github.jhipster.application.repository.QuestionRepository;
import io.github.jhipster.application.service.dto.Question;
import io.github.jhipster.application.service.mapper.QuestionMapper;
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
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionResourceIT {

  private static final String DEFAULT_QUESTION_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_QUESTION_TYPE = "BBBBBBBBBB";

  private static final Integer DEFAULT_SCORE = 1;
  private static final Integer UPDATED_SCORE = 2;

  private static final Integer DEFAULT_GRADE_LEVEL = 1;
  private static final Integer UPDATED_GRADE_LEVEL = 2;

  private static final Integer DEFAULT_DIFFICULT = 1;
  private static final Integer UPDATED_DIFFICULT = 2;

  private static final String DEFAULT_CORRECT = "AAAAAAAAAA";
  private static final String UPDATED_CORRECT = "BBBBBBBBBB";

  private static final Integer DEFAULT_INFO_TEXT_CONTENT_ID = 1;
  private static final Integer UPDATED_INFO_TEXT_CONTENT_ID = 2;

  private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final Integer DEFAULT_STATUS = 1;
  private static final Integer UPDATED_STATUS = 2;

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final String ENTITY_API_URL = "/api/questions";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private QuestionMapper questionMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restQuestionMockMvc;

  private Question question;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Question createEntity(EntityManager em) {
    Question question = new Question()
      .questionType(DEFAULT_QUESTION_TYPE)
      .score(DEFAULT_SCORE)
      .gradeLevel(DEFAULT_GRADE_LEVEL)
      .difficult(DEFAULT_DIFFICULT)
      .correct(DEFAULT_CORRECT)
      .infoTextContentId(DEFAULT_INFO_TEXT_CONTENT_ID)
      .createBy(DEFAULT_CREATE_BY)
      .createDate(DEFAULT_CREATE_DATE)
      .status(DEFAULT_STATUS)
      .deleted(DEFAULT_DELETED);
    return question;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Question createUpdatedEntity(EntityManager em) {
    Question question = new Question()
      .questionType(UPDATED_QUESTION_TYPE)
      .score(UPDATED_SCORE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .difficult(UPDATED_DIFFICULT)
      .correct(UPDATED_CORRECT)
      .infoTextContentId(UPDATED_INFO_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .status(UPDATED_STATUS)
      .deleted(UPDATED_DELETED);
    return question;
  }

  @BeforeEach
  public void initTest() {
    question = createEntity(em);
  }

  @Test
  @Transactional
  void createQuestion() throws Exception {
    int databaseSizeBeforeCreate = questionRepository.findAll().size();
    // Create the Question
    Question question = questionMapper.toDto(question);
    restQuestionMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(question))
      )
      .andExpect(status().isCreated());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
    Question testQuestion = questionList.get(questionList.size() - 1);
    assertThat(testQuestion.getQuestionType()).isEqualTo(DEFAULT_QUESTION_TYPE);
    assertThat(testQuestion.getScore()).isEqualTo(DEFAULT_SCORE);
    assertThat(testQuestion.getGradeLevel()).isEqualTo(DEFAULT_GRADE_LEVEL);
    assertThat(testQuestion.getDifficult()).isEqualTo(DEFAULT_DIFFICULT);
    assertThat(testQuestion.getCorrect()).isEqualTo(DEFAULT_CORRECT);
    assertThat(testQuestion.getInfoTextContentId())
      .isEqualTo(DEFAULT_INFO_TEXT_CONTENT_ID);
    assertThat(testQuestion.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
    assertThat(testQuestion.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    assertThat(testQuestion.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testQuestion.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void createQuestionWithExistingId() throws Exception {
    // Create the Question with an existing ID
    question.setId(1L);
    Question question = questionMapper.toDto(question);

    int databaseSizeBeforeCreate = questionRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restQuestionMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(question))
      )
      .andExpect(status().isBadRequest());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllQuestions() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    // Get all the questionList
    restQuestionMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(question.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].questionType").value(hasItem(DEFAULT_QUESTION_TYPE))
      )
      .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
      .andExpect(
        jsonPath("$.[*].gradeLevel").value(hasItem(DEFAULT_GRADE_LEVEL))
      )
      .andExpect(jsonPath("$.[*].difficult").value(hasItem(DEFAULT_DIFFICULT)))
      .andExpect(jsonPath("$.[*].correct").value(hasItem(DEFAULT_CORRECT)))
      .andExpect(
        jsonPath("$.[*].infoTextContentId")
          .value(hasItem(DEFAULT_INFO_TEXT_CONTENT_ID))
      )
      .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
      .andExpect(
        jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue()))
      );
  }

  @Test
  @Transactional
  void getQuestion() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    // Get the question
    restQuestionMockMvc
      .perform(get(ENTITY_API_URL_ID, question.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(question.getId().intValue()))
      .andExpect(jsonPath("$.questionType").value(DEFAULT_QUESTION_TYPE))
      .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
      .andExpect(jsonPath("$.gradeLevel").value(DEFAULT_GRADE_LEVEL))
      .andExpect(jsonPath("$.difficult").value(DEFAULT_DIFFICULT))
      .andExpect(jsonPath("$.correct").value(DEFAULT_CORRECT))
      .andExpect(
        jsonPath("$.infoTextContentId").value(DEFAULT_INFO_TEXT_CONTENT_ID)
      )
      .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
      .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingQuestion() throws Exception {
    // Get the question
    restQuestionMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewQuestion() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    int databaseSizeBeforeUpdate = questionRepository.findAll().size();

    // Update the question
    Question updatedQuestion = questionRepository
      .findById(question.getId())
      .get();
    // Disconnect from session so that the updates on updatedQuestion are not directly saved in db
    em.detach(updatedQuestion);
    updatedQuestion
      .questionType(UPDATED_QUESTION_TYPE)
      .score(UPDATED_SCORE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .difficult(UPDATED_DIFFICULT)
      .correct(UPDATED_CORRECT)
      .infoTextContentId(UPDATED_INFO_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .status(UPDATED_STATUS)
      .deleted(UPDATED_DELETED);
    Question question = questionMapper.toDto(updatedQuestion);

    restQuestionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, questionDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(questionDTO))
      )
      .andExpect(status().isOk());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    Question testQuestion = questionList.get(questionList.size() - 1);
    assertThat(testQuestion.getQuestionType()).isEqualTo(UPDATED_QUESTION_TYPE);
    assertThat(testQuestion.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testQuestion.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testQuestion.getDifficult()).isEqualTo(UPDATED_DIFFICULT);
    assertThat(testQuestion.getCorrect()).isEqualTo(UPDATED_CORRECT);
    assertThat(testQuestion.getInfoTextContentId())
      .isEqualTo(UPDATED_INFO_TEXT_CONTENT_ID);
    assertThat(testQuestion.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testQuestion.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testQuestion.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void putNonExistingQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    Question question = questionMapper.toDto(question);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, question.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(question))
      )
      .andExpect(status().isBadRequest());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    Question question = questionMapper.toDto(question);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(question))
      )
      .andExpect(status().isBadRequest());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    Question question = questionMapper.toDto(question);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(question))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateQuestionWithPatch() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    int databaseSizeBeforeUpdate = questionRepository.findAll().size();

    // Update the question using partial update
    Question partialUpdatedQuestion = new Question();
    partialUpdatedQuestion.setId(question.getId());

    partialUpdatedQuestion
      .questionType(UPDATED_QUESTION_TYPE)
      .score(UPDATED_SCORE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .infoTextContentId(UPDATED_INFO_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .deleted(UPDATED_DELETED);

    restQuestionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedQuestion.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestion))
      )
      .andExpect(status().isOk());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    Question testQuestion = questionList.get(questionList.size() - 1);
    assertThat(testQuestion.getQuestionType()).isEqualTo(UPDATED_QUESTION_TYPE);
    assertThat(testQuestion.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testQuestion.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testQuestion.getDifficult()).isEqualTo(DEFAULT_DIFFICULT);
    assertThat(testQuestion.getCorrect()).isEqualTo(DEFAULT_CORRECT);
    assertThat(testQuestion.getInfoTextContentId())
      .isEqualTo(UPDATED_INFO_TEXT_CONTENT_ID);
    assertThat(testQuestion.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testQuestion.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testQuestion.getStatus()).isEqualTo(DEFAULT_STATUS);
    assertThat(testQuestion.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void fullUpdateQuestionWithPatch() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    int databaseSizeBeforeUpdate = questionRepository.findAll().size();

    // Update the question using partial update
    Question partialUpdatedQuestion = new Question();
    partialUpdatedQuestion.setId(question.getId());

    partialUpdatedQuestion
      .questionType(UPDATED_QUESTION_TYPE)
      .score(UPDATED_SCORE)
      .gradeLevel(UPDATED_GRADE_LEVEL)
      .difficult(UPDATED_DIFFICULT)
      .correct(UPDATED_CORRECT)
      .infoTextContentId(UPDATED_INFO_TEXT_CONTENT_ID)
      .createBy(UPDATED_CREATE_BY)
      .createDate(UPDATED_CREATE_DATE)
      .status(UPDATED_STATUS)
      .deleted(UPDATED_DELETED);

    restQuestionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedQuestion.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestion))
      )
      .andExpect(status().isOk());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    Question testQuestion = questionList.get(questionList.size() - 1);
    assertThat(testQuestion.getQuestionType()).isEqualTo(UPDATED_QUESTION_TYPE);
    assertThat(testQuestion.getScore()).isEqualTo(UPDATED_SCORE);
    assertThat(testQuestion.getGradeLevel()).isEqualTo(UPDATED_GRADE_LEVEL);
    assertThat(testQuestion.getDifficult()).isEqualTo(UPDATED_DIFFICULT);
    assertThat(testQuestion.getCorrect()).isEqualTo(UPDATED_CORRECT);
    assertThat(testQuestion.getInfoTextContentId())
      .isEqualTo(UPDATED_INFO_TEXT_CONTENT_ID);
    assertThat(testQuestion.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    assertThat(testQuestion.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
    assertThat(testQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    assertThat(testQuestion.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void patchNonExistingQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    QuestionDTO questionDTO = questionMapper.toDto(question);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, questionDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(questionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    QuestionDTO questionDTO = questionMapper.toDto(question);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(questionDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamQuestion() throws Exception {
    int databaseSizeBeforeUpdate = questionRepository.findAll().size();
    question.setId(count.incrementAndGet());

    // Create the Question
    QuestionDTO questionDTO = questionMapper.toDto(question);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restQuestionMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(questionDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Question in the database
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteQuestion() throws Exception {
    // Initialize the database
    questionRepository.saveAndFlush(question);

    int databaseSizeBeforeDelete = questionRepository.findAll().size();

    // Delete the question
    restQuestionMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, question.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Question> questionList = questionRepository.findAll();
    assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
