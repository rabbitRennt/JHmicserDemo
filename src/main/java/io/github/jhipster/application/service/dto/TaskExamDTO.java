package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TaskExam} entity.
 */
public class TaskExam implements Serializable {

  private Long id;

  private String title;

  private Integer gradeLevel;

  private Long frameTextContentId;

  private String createBy;

  private String createByUserName;

  private Instant createDate;

  private Boolean deleted;

  private ExamPaperDTO examPaper;

  private TextContentDTO textContent;

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

  public Integer getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Long getFrameTextContentId() {
    return frameTextContentId;
  }

  public void setFrameTextContentId(Long frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getCreateByUserName() {
    return createByUserName;
  }

  public void setCreateByUserName(String createByUserName) {
    this.createByUserName = createByUserName;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public ExamPaperDTO getExamPaper() {
    return examPaper;
  }

  public void setExamPaper(ExamPaperDTO examPaper) {
    this.examPaper = examPaper;
  }

  public TextContentDTO getTextContent() {
    return textContent;
  }

  public void setTextContent(TextContentDTO textContent) {
    this.textContent = textContent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaskExam)) {
      return false;
    }

    TaskExam taskExam = (TaskExam) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, taskExam.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TaskExam{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", gradeLevel=" + getGradeLevel() +
            ", frameTextContentId=" + getFrameTextContentId() +
            ", createBy='" + getCreateBy() + "'" +
            ", createByUserName='" + getCreateByUserName() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", examPaper=" + getExamPaper() +
            ", textContent=" + getTextContent() +
            "}";
    }
}
