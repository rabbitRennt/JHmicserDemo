package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskExamAnswerTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(TaskExamAnswer.class);
    TaskExamAnswer taskExamAnswer1 = new TaskExamAnswer();
    taskExamAnswer1.setId(1L);
    TaskExamAnswer taskExamAnswer2 = new TaskExamAnswer();
    taskExamAnswer2.setId(taskExamAnswer1.getId());
    assertThat(taskExamAnswer1).isEqualTo(taskExamAnswer2);
    taskExamAnswer2.setId(2L);
    assertThat(taskExamAnswer1).isNotEqualTo(taskExamAnswer2);
    taskExamAnswer1.setId(null);
    assertThat(taskExamAnswer1).isNotEqualTo(taskExamAnswer2);
  }
}
