package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ExamPaper} entity.
 */
public class ExamPaper implements Serializable {

  private Long id;

  private String name;

  private Integer paperType;

  private Integer gradeLevel;

  private Integer score;

  private Integer questionCount;

  private Integer suggestTime;

  private Instant limitStartDate;

  private Instant limitEneDate;

  private Integer frameTextContentId;

  private Long createBy;

  private Long createDate;

  private Boolean deleted;

  private SubjectDTO subject;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPaperType() {
    return paperType;
  }

  public void setPaperType(Integer paperType) {
    this.paperType = paperType;
  }

  public Integer getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Integer getSuggestTime() {
    return suggestTime;
  }

  public void setSuggestTime(Integer suggestTime) {
    this.suggestTime = suggestTime;
  }

  public Instant getLimitStartDate() {
    return limitStartDate;
  }

  public void setLimitStartDate(Instant limitStartDate) {
    this.limitStartDate = limitStartDate;
  }

  public Instant getLimitEneDate() {
    return limitEneDate;
  }

  public void setLimitEneDate(Instant limitEneDate) {
    this.limitEneDate = limitEneDate;
  }

  public Integer getFrameTextContentId() {
    return frameTextContentId;
  }

  public void setFrameTextContentId(Integer frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }

  public Long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Long getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public SubjectDTO getSubject() {
    return subject;
  }

  public void setSubject(SubjectDTO subject) {
    this.subject = subject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaper)) {
      return false;
    }

    ExamPaper examPaper = (ExamPaper) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, examPaper.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ExamPaper{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", paperType=" + getPaperType() +
            ", gradeLevel=" + getGradeLevel() +
            ", score=" + getScore() +
            ", questionCount=" + getQuestionCount() +
            ", suggestTime=" + getSuggestTime() +
            ", limitStartDate='" + getLimitStartDate() + "'" +
            ", limitEneDate='" + getLimitEneDate() + "'" +
            ", frameTextContentId=" + getFrameTextContentId() +
            ", createBy=" + getCreateBy() +
            ", createDate=" + getCreateDate() +
            ", deleted='" + getDeleted() + "'" +
            ", subject=" + getSubject() +
            "}";
    }
}
