package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.Subject;
import io.github.jhipster.application.repository.SubjectRepository;
import io.github.jhipster.application.service.dto.Subject;
import io.github.jhipster.application.service.mapper.SubjectMapper;
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
 * Integration tests for the {@link SubjectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SubjectResourceIT {

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_LEVEL = 1;
  private static final Integer UPDATED_LEVEL = 2;

  private static final String DEFAULT_LEVEL_NAME = "AAAAAAAAAA";
  private static final String UPDATED_LEVEL_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_ITEM_ORDER = 1;
  private static final Integer UPDATED_ITEM_ORDER = 2;

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final String ENTITY_API_URL = "/api/subjects";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private SubjectMapper subjectMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restSubjectMockMvc;

  private Subject subject;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Subject createEntity(EntityManager em) {
    Subject subject = new Subject()
      .name(DEFAULT_NAME)
      .level(DEFAULT_LEVEL)
      .levelName(DEFAULT_LEVEL_NAME)
      .itemOrder(DEFAULT_ITEM_ORDER)
      .deleted(DEFAULT_DELETED);
    return subject;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Subject createUpdatedEntity(EntityManager em) {
    Subject subject = new Subject()
      .name(UPDATED_NAME)
      .level(UPDATED_LEVEL)
      .levelName(UPDATED_LEVEL_NAME)
      .itemOrder(UPDATED_ITEM_ORDER)
      .deleted(UPDATED_DELETED);
    return subject;
  }

  @BeforeEach
  public void initTest() {
    subject = createEntity(em);
  }

  @Test
  @Transactional
  void createSubject() throws Exception {
    int databaseSizeBeforeCreate = subjectRepository.findAll().size();
    // Create the Subject
    Subject subject = subjectMapper.toDto(subject);
    restSubjectMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subject))
      )
      .andExpect(status().isCreated());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeCreate + 1);
    Subject testSubject = subjectList.get(subjectList.size() - 1);
    assertThat(testSubject.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testSubject.getLevel()).isEqualTo(DEFAULT_LEVEL);
    assertThat(testSubject.getLevelName()).isEqualTo(DEFAULT_LEVEL_NAME);
    assertThat(testSubject.getItemOrder()).isEqualTo(DEFAULT_ITEM_ORDER);
    assertThat(testSubject.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void createSubjectWithExistingId() throws Exception {
    // Create the Subject with an existing ID
    subject.setId(1L);
    Subject subject = subjectMapper.toDto(subject);

    int databaseSizeBeforeCreate = subjectRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restSubjectMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subject))
      )
      .andExpect(status().isBadRequest());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllSubjects() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    // Get all the subjectList
    restSubjectMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(subject.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
      .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
      .andExpect(jsonPath("$.[*].levelName").value(hasItem(DEFAULT_LEVEL_NAME)))
      .andExpect(jsonPath("$.[*].itemOrder").value(hasItem(DEFAULT_ITEM_ORDER)))
      .andExpect(
        jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue()))
      );
  }

  @Test
  @Transactional
  void getSubject() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    // Get the subject
    restSubjectMockMvc
      .perform(get(ENTITY_API_URL_ID, subject.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(subject.getId().intValue()))
      .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
      .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
      .andExpect(jsonPath("$.levelName").value(DEFAULT_LEVEL_NAME))
      .andExpect(jsonPath("$.itemOrder").value(DEFAULT_ITEM_ORDER))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()));
  }

  @Test
  @Transactional
  void getNonExistingSubject() throws Exception {
    // Get the subject
    restSubjectMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewSubject() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();

    // Update the subject
    Subject updatedSubject = subjectRepository.findById(subject.getId()).get();
    // Disconnect from session so that the updates on updatedSubject are not directly saved in db
    em.detach(updatedSubject);
    updatedSubject
      .name(UPDATED_NAME)
      .level(UPDATED_LEVEL)
      .levelName(UPDATED_LEVEL_NAME)
      .itemOrder(UPDATED_ITEM_ORDER)
      .deleted(UPDATED_DELETED);
    Subject subject = subjectMapper.toDto(updatedSubject);

    restSubjectMockMvc
      .perform(
        put(ENTITY_API_URL_ID, subjectDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subjectDTO))
      )
      .andExpect(status().isOk());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
    Subject testSubject = subjectList.get(subjectList.size() - 1);
    assertThat(testSubject.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSubject.getLevel()).isEqualTo(UPDATED_LEVEL);
    assertThat(testSubject.getLevelName()).isEqualTo(UPDATED_LEVEL_NAME);
    assertThat(testSubject.getItemOrder()).isEqualTo(UPDATED_ITEM_ORDER);
    assertThat(testSubject.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void putNonExistingSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    Subject subject = subjectMapper.toDto(subject);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        put(ENTITY_API_URL_ID, subject.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subject))
      )
      .andExpect(status().isBadRequest());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    Subject subject = subjectMapper.toDto(subject);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subject))
      )
      .andExpect(status().isBadRequest());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    Subject subject = subjectMapper.toDto(subject);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(subject))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateSubjectWithPatch() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();

    // Update the subject using partial update
    Subject partialUpdatedSubject = new Subject();
    partialUpdatedSubject.setId(subject.getId());

    partialUpdatedSubject.name(UPDATED_NAME).itemOrder(UPDATED_ITEM_ORDER);

    restSubjectMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSubject.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubject))
      )
      .andExpect(status().isOk());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
    Subject testSubject = subjectList.get(subjectList.size() - 1);
    assertThat(testSubject.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSubject.getLevel()).isEqualTo(DEFAULT_LEVEL);
    assertThat(testSubject.getLevelName()).isEqualTo(DEFAULT_LEVEL_NAME);
    assertThat(testSubject.getItemOrder()).isEqualTo(UPDATED_ITEM_ORDER);
    assertThat(testSubject.getDeleted()).isEqualTo(DEFAULT_DELETED);
  }

  @Test
  @Transactional
  void fullUpdateSubjectWithPatch() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();

    // Update the subject using partial update
    Subject partialUpdatedSubject = new Subject();
    partialUpdatedSubject.setId(subject.getId());

    partialUpdatedSubject
      .name(UPDATED_NAME)
      .level(UPDATED_LEVEL)
      .levelName(UPDATED_LEVEL_NAME)
      .itemOrder(UPDATED_ITEM_ORDER)
      .deleted(UPDATED_DELETED);

    restSubjectMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedSubject.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubject))
      )
      .andExpect(status().isOk());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
    Subject testSubject = subjectList.get(subjectList.size() - 1);
    assertThat(testSubject.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testSubject.getLevel()).isEqualTo(UPDATED_LEVEL);
    assertThat(testSubject.getLevelName()).isEqualTo(UPDATED_LEVEL_NAME);
    assertThat(testSubject.getItemOrder()).isEqualTo(UPDATED_ITEM_ORDER);
    assertThat(testSubject.getDeleted()).isEqualTo(UPDATED_DELETED);
  }

  @Test
  @Transactional
  void patchNonExistingSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    SubjectDTO subjectDTO = subjectMapper.toDto(subject);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, subjectDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(subjectDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    SubjectDTO subjectDTO = subjectMapper.toDto(subject);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(subjectDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamSubject() throws Exception {
    int databaseSizeBeforeUpdate = subjectRepository.findAll().size();
    subject.setId(count.incrementAndGet());

    // Create the Subject
    SubjectDTO subjectDTO = subjectMapper.toDto(subject);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restSubjectMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(subjectDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Subject in the database
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteSubject() throws Exception {
    // Initialize the database
    subjectRepository.saveAndFlush(subject);

    int databaseSizeBeforeDelete = subjectRepository.findAll().size();

    // Delete the subject
    restSubjectMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, subject.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Subject> subjectList = subjectRepository.findAll();
    assertThat(subjectList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
