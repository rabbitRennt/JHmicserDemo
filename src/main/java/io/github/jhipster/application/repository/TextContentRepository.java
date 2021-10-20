package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TextContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TextContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TextContentRepository
  extends JpaRepository<TextContent, Long> {}
