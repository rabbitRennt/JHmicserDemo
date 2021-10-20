package io.github.jhipster.application.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskExamTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(TaskExam.class);
    TaskExam taskExam1 = new TaskExam();
    taskExam1.setId(1L);
    TaskExam taskExam2 = new TaskExam();
    assertThat(taskExam1).isNotEqualTo(taskExam2);
    taskExam2.setId(taskExam1.getId());
    assertThat(taskExam1).isEqualTo(taskExam2);
    taskExam2.setId(2L);
    assertThat(taskExam1).isNotEqualTo(taskExam2);
    taskExam1.setId(null);
    assertThat(taskExam1).isNotEqualTo(taskExam2);
  }
}
