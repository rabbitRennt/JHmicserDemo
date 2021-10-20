package io.github.jhipster.application.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageUserMapperTest {

  private MessageUserMapper messageUserMapper;

  @BeforeEach
  public void setUp() {
    messageUserMapper = new MessageUserMapperImpl();
  }
}
