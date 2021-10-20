package io.github.jhipster.application.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.IntegrationTest;
import io.github.jhipster.application.domain.TextContent;
import io.github.jhipster.application.repository.TextContentRepository;
import io.github.jhipster.application.service.dto.TextContent;
import io.github.jhipster.application.service.mapper.TextContentMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link TextContentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TextContentResourceIT {

  private static final byte[] DEFAULT_CONTENT = TestUtil.createByteArray(
    1,
    "0"
  );
  private static final byte[] UPDATED_CONTENT = TestUtil.createByteArray(
    1,
    "1"
  );
  private static final String DEFAULT_CONTENT_CONTENT_TYPE = "image/jpg";
  private static final String UPDATED_CONTENT_CONTENT_TYPE = "image/png";

  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATE_DATE = Instant
    .now()
    .truncatedTo(ChronoUnit.MILLIS);

  private static final String ENTITY_API_URL = "/api/text-contents";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private TextContentRepository textContentRepository;

  @Autowired
  private TextContentMapper textContentMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTextContentMockMvc;

  private TextContent textContent;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TextContent createEntity(EntityManager em) {
    TextContent textContent = new TextContent()
      .content(DEFAULT_CONTENT)
      .contentContentType(DEFAULT_CONTENT_CONTENT_TYPE)
      .createDate(DEFAULT_CREATE_DATE);
    return textContent;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TextContent createUpdatedEntity(EntityManager em) {
    TextContent textContent = new TextContent()
      .content(UPDATED_CONTENT)
      .contentContentType(UPDATED_CONTENT_CONTENT_TYPE)
      .createDate(UPDATED_CREATE_DATE);
    return textContent;
  }

  @BeforeEach
  public void initTest() {
    textContent = createEntity(em);
  }

  @Test
  @Transactional
  void createTextContent() throws Exception {
    int databaseSizeBeforeCreate = textContentRepository.findAll().size();
    // Create the TextContent
    TextContent textContent = textContentMapper.toDto(textContent);
    restTextContentMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContent))
      )
      .andExpect(status().isCreated());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeCreate + 1);
    TextContent testTextContent = textContentList.get(
      textContentList.size() - 1
    );
    assertThat(testTextContent.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testTextContent.getContentContentType())
      .isEqualTo(DEFAULT_CONTENT_CONTENT_TYPE);
    assertThat(testTextContent.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
  }

  @Test
  @Transactional
  void createTextContentWithExistingId() throws Exception {
    // Create the TextContent with an existing ID
    textContent.setId(1L);
    TextContent textContent = textContentMapper.toDto(textContent);

    int databaseSizeBeforeCreate = textContentRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTextContentMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContent))
      )
      .andExpect(status().isBadRequest());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllTextContents() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    // Get all the textContentList
    restTextContentMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(textContent.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].contentContentType")
          .value(hasItem(DEFAULT_CONTENT_CONTENT_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].content")
          .value(hasItem(Base64Utils.encodeToString(DEFAULT_CONTENT)))
      )
      .andExpect(
        jsonPath("$.[*].createDate")
          .value(hasItem(DEFAULT_CREATE_DATE.toString()))
      );
  }

  @Test
  @Transactional
  void getTextContent() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    // Get the textContent
    restTextContentMockMvc
      .perform(get(ENTITY_API_URL_ID, textContent.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(textContent.getId().intValue()))
      .andExpect(
        jsonPath("$.contentContentType").value(DEFAULT_CONTENT_CONTENT_TYPE)
      )
      .andExpect(
        jsonPath("$.content").value(Base64Utils.encodeToString(DEFAULT_CONTENT))
      )
      .andExpect(
        jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString())
      );
  }

  @Test
  @Transactional
  void getNonExistingTextContent() throws Exception {
    // Get the textContent
    restTextContentMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTextContent() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();

    // Update the textContent
    TextContent updatedTextContent = textContentRepository
      .findById(textContent.getId())
      .get();
    // Disconnect from session so that the updates on updatedTextContent are not directly saved in db
    em.detach(updatedTextContent);
    updatedTextContent
      .content(UPDATED_CONTENT)
      .contentContentType(UPDATED_CONTENT_CONTENT_TYPE)
      .createDate(UPDATED_CREATE_DATE);
    TextContent textContent = textContentMapper.toDto(updatedTextContent);

    restTextContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, textContentDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContentDTO))
      )
      .andExpect(status().isOk());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
    TextContent testTextContent = textContentList.get(
      textContentList.size() - 1
    );
    assertThat(testTextContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testTextContent.getContentContentType())
      .isEqualTo(UPDATED_CONTENT_CONTENT_TYPE);
    assertThat(testTextContent.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
  }

  @Test
  @Transactional
  void putNonExistingTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContent textContent = textContentMapper.toDto(textContent);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, textContent.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContent))
      )
      .andExpect(status().isBadRequest());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContent textContent = textContentMapper.toDto(textContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContent))
      )
      .andExpect(status().isBadRequest());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContent textContent = textContentMapper.toDto(textContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(textContent))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateTextContentWithPatch() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();

    // Update the textContent using partial update
    TextContent partialUpdatedTextContent = new TextContent();
    partialUpdatedTextContent.setId(textContent.getId());

    partialUpdatedTextContent
      .content(UPDATED_CONTENT)
      .contentContentType(UPDATED_CONTENT_CONTENT_TYPE)
      .createDate(UPDATED_CREATE_DATE);

    restTextContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTextContent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTextContent))
      )
      .andExpect(status().isOk());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
    TextContent testTextContent = textContentList.get(
      textContentList.size() - 1
    );
    assertThat(testTextContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testTextContent.getContentContentType())
      .isEqualTo(UPDATED_CONTENT_CONTENT_TYPE);
    assertThat(testTextContent.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
  }

  @Test
  @Transactional
  void fullUpdateTextContentWithPatch() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();

    // Update the textContent using partial update
    TextContent partialUpdatedTextContent = new TextContent();
    partialUpdatedTextContent.setId(textContent.getId());

    partialUpdatedTextContent
      .content(UPDATED_CONTENT)
      .contentContentType(UPDATED_CONTENT_CONTENT_TYPE)
      .createDate(UPDATED_CREATE_DATE);

    restTextContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTextContent.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTextContent))
      )
      .andExpect(status().isOk());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
    TextContent testTextContent = textContentList.get(
      textContentList.size() - 1
    );
    assertThat(testTextContent.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testTextContent.getContentContentType())
      .isEqualTo(UPDATED_CONTENT_CONTENT_TYPE);
    assertThat(testTextContent.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
  }

  @Test
  @Transactional
  void patchNonExistingTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContentDTO textContentDTO = textContentMapper.toDto(textContent);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, textContentDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(textContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContentDTO textContentDTO = textContentMapper.toDto(textContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(textContentDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTextContent() throws Exception {
    int databaseSizeBeforeUpdate = textContentRepository.findAll().size();
    textContent.setId(count.incrementAndGet());

    // Create the TextContent
    TextContentDTO textContentDTO = textContentMapper.toDto(textContent);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTextContentMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(textContentDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TextContent in the database
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteTextContent() throws Exception {
    // Initialize the database
    textContentRepository.saveAndFlush(textContent);

    int databaseSizeBeforeDelete = textContentRepository.findAll().size();

    // Delete the textContent
    restTextContentMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, textContent.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TextContent> textContentList = textContentRepository.findAll();
    assertThat(textContentList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
