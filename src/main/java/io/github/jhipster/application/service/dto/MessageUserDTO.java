package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.MessageUser} entity.
 */
public class MessageUser implements Serializable {

  private Long id;

  private Long receiveUserId;

  private String receiveUsername;

  private String receiveRealName;

  private Boolean read;

  private Instant createDate;

  private Instant readDate;

  private MessageDTO message;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getReceiveUserId() {
    return receiveUserId;
  }

  public void setReceiveUserId(Long receiveUserId) {
    this.receiveUserId = receiveUserId;
  }

  public String getReceiveUsername() {
    return receiveUsername;
  }

  public void setReceiveUsername(String receiveUsername) {
    this.receiveUsername = receiveUsername;
  }

  public String getReceiveRealName() {
    return receiveRealName;
  }

  public void setReceiveRealName(String receiveRealName) {
    this.receiveRealName = receiveRealName;
  }

  public Boolean getRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Instant getReadDate() {
    return readDate;
  }

  public void setReadDate(Instant readDate) {
    this.readDate = readDate;
  }

  public MessageDTO getMessage() {
    return message;
  }

  public void setMessage(MessageDTO message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageUser)) {
      return false;
    }

    MessageUser messageUser = (MessageUser) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, messageUser.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageUser{" +
            "id=" + getId() +
            ", receiveUserId=" + getReceiveUserId() +
            ", receiveUsername='" + getReceiveUsername() + "'" +
            ", receiveRealName='" + getReceiveRealName() + "'" +
            ", read='" + getRead() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", readDate='" + getReadDate() + "'" +
            ", message=" + getMessage() +
            "}";
    }
}
