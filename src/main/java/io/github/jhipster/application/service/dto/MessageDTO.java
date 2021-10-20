package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Message} entity.
 */
public class Message implements Serializable {

  private Long id;

  private String title;

  private String content;

  private Instant createDate;

  private String sendUserid;

  private String sendUsername;

  private Integer receiveUserCount;

  private Integer readCount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public String getSendUserid() {
    return sendUserid;
  }

  public void setSendUserid(String sendUserid) {
    this.sendUserid = sendUserid;
  }

  public String getSendUsername() {
    return sendUsername;
  }

  public void setSendUsername(String sendUsername) {
    this.sendUsername = sendUsername;
  }

  public Integer getReceiveUserCount() {
    return receiveUserCount;
  }

  public void setReceiveUserCount(Integer receiveUserCount) {
    this.receiveUserCount = receiveUserCount;
  }

  public Integer getReadCount() {
    return readCount;
  }

  public void setReadCount(Integer readCount) {
    this.readCount = readCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Message)) {
      return false;
    }

    Message message = (Message) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, message.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", sendUserid='" + getSendUserid() + "'" +
            ", sendUsername='" + getSendUsername() + "'" +
            ", receiveUserCount=" + getReceiveUserCount() +
            ", readCount=" + getReadCount() +
            "}";
    }
}
