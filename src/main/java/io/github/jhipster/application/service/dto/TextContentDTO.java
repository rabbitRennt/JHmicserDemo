package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TextContent} entity.
 */
public class TextContent implements Serializable {

  private Long id;

  @Lob
  private byte[] content;

  private String contentContentType;
  private Instant createDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getContentContentType() {
    return contentContentType;
  }

  public void setContentContentType(String contentContentType) {
    this.contentContentType = contentContentType;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TextContent)) {
      return false;
    }

    TextContent textContent = (TextContent) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, textContent.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TextContent{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
