package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MessageUserTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(MessageUser.class);
    MessageUser messageUser1 = new MessageUser();
    messageUser1.setId(1L);
    MessageUser messageUser2 = new MessageUser();
    messageUser2.setId(messageUser1.getId());
    assertThat(messageUser1).isEqualTo(messageUser2);
    messageUser2.setId(2L);
    assertThat(messageUser1).isNotEqualTo(messageUser2);
    messageUser1.setId(null);
    assertThat(messageUser1).isNotEqualTo(messageUser2);
  }
}
