package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TextContentTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(TextContent.class);
    TextContent textContent1 = new TextContent();
    textContent1.setId(1L);
    TextContent textContent2 = new TextContent();
    textContent2.setId(textContent1.getId());
    assertThat(textContent1).isEqualTo(textContent2);
    textContent2.setId(2L);
    assertThat(textContent1).isNotEqualTo(textContent2);
    textContent1.setId(null);
    assertThat(textContent1).isNotEqualTo(textContent2);
  }
}
