package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExamPaperAnswerDefTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ExamPaperAnswerDef.class);
    ExamPaperAnswerDef examPaperAnswerDef1 = new ExamPaperAnswerDef();
    examPaperAnswerDef1.setId(1L);
    ExamPaperAnswerDef examPaperAnswerDef2 = new ExamPaperAnswerDef();
    examPaperAnswerDef2.setId(examPaperAnswerDef1.getId());
    assertThat(examPaperAnswerDef1).isEqualTo(examPaperAnswerDef2);
    examPaperAnswerDef2.setId(2L);
    assertThat(examPaperAnswerDef1).isNotEqualTo(examPaperAnswerDef2);
    examPaperAnswerDef1.setId(null);
    assertThat(examPaperAnswerDef1).isNotEqualTo(examPaperAnswerDef2);
  }
}
