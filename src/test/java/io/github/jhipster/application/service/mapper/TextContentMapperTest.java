package io.github.jhipster.application.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextContentMapperTest {

  private TextContentMapper textContentMapper;

  @BeforeEach
  public void setUp() {
    textContentMapper = new TextContentMapperImpl();
  }
}
