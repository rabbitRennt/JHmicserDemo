package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.domain.TextContent;
import io.github.jhipster.application.repository.TextContentRepository;
import io.github.jhipster.application.service.TextContentService;
import io.github.jhipster.application.service.dto.TextContent;
import io.github.jhipster.application.service.mapper.TextContentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TextContent}.
 */
@Service
@Transactional
public class TextContentServiceImpl implements TextContentService {

  private final Logger log = LoggerFactory.getLogger(
    TextContentServiceImpl.class
  );

  private final TextContentRepository textContentRepository;

  private final TextContentMapper textContentMapper;

  public TextContentServiceImpl(
    TextContentRepository textContentRepository,
    TextContentMapper textContentMapper
  ) {
    this.textContentRepository = textContentRepository;
    this.textContentMapper = textContentMapper;
  }

  @Override
  public TextContent save(TextContent textContent) {
    log.debug("Request to save TextContent : {}", textContent);
    TextContent textContent = textContentMapper.toEntity(textContent);
    textContent = textContentRepository.save(textContent);
    return textContentMapper.toDto(textContent);
  }

  @Override
  public Optional<TextContent> partialUpdate(TextContent textContent) {
    log.debug("Request to partially update TextContent : {}", textContent);

    return textContentRepository
      .findById(textContent.getId())
      .map(existingTextContent -> {
        textContentMapper.partialUpdate(existingTextContent, textContent);

        return existingTextContent;
      })
      .map(textContentRepository::save)
      .map(textContentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TextContent> findAll() {
    log.debug("Request to get all TextContents");
    return textContentRepository
      .findAll()
      .stream()
      .map(textContentMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TextContent> findOne(Long id) {
    log.debug("Request to get TextContent : {}", id);
    return textContentRepository.findById(id).map(textContentMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete TextContent : {}", id);
    textContentRepository.deleteById(id);
  }
}
