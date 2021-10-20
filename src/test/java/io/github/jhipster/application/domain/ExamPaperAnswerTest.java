package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExamPaperAnswerTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ExamPaperAnswer.class);
    ExamPaperAnswer examPaperAnswer1 = new ExamPaperAnswer();
    examPaperAnswer1.setId(1L);
    ExamPaperAnswer examPaperAnswer2 = new ExamPaperAnswer();
    examPaperAnswer2.setId(examPaperAnswer1.getId());
    assertThat(examPaperAnswer1).isEqualTo(examPaperAnswer2);
    examPaperAnswer2.setId(2L);
    assertThat(examPaperAnswer1).isNotEqualTo(examPaperAnswer2);
    examPaperAnswer1.setId(null);
    assertThat(examPaperAnswer1).isNotEqualTo(examPaperAnswer2);
  }
}
