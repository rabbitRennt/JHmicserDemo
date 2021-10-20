package io.github.jhipster.application.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExamPaperTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(ExamPaper.class);
    ExamPaper examPaper1 = new ExamPaper();
    examPaper1.setId(1L);
    ExamPaper examPaper2 = new ExamPaper();
    assertThat(examPaper1).isNotEqualTo(examPaper2);
    examPaper2.setId(examPaper1.getId());
    assertThat(examPaper1).isEqualTo(examPaper2);
    examPaper2.setId(2L);
    assertThat(examPaper1).isNotEqualTo(examPaper2);
    examPaper1.setId(null);
    assertThat(examPaper1).isNotEqualTo(examPaper2);
  }
}
