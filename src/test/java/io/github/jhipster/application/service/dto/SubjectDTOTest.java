package io.github.jhipster.application.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SubjectTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(Subject.class);
    Subject subject1 = new Subject();
    subject1.setId(1L);
    Subject subject2 = new Subject();
    assertThat(subject1).isNotEqualTo(subject2);
    subject2.setId(subject1.getId());
    assertThat(subject1).isEqualTo(subject2);
    subject2.setId(2L);
    assertThat(subject1).isNotEqualTo(subject2);
    subject1.setId(null);
    assertThat(subject1).isNotEqualTo(subject2);
  }
}
