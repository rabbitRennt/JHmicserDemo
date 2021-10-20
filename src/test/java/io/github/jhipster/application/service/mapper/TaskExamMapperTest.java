package io.github.jhipster.application.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskExamMapperTest {

  private TaskExamMapper taskExamMapper;

  @BeforeEach
  public void setUp() {
    taskExamMapper = new TaskExamMapperImpl();
  }
}
