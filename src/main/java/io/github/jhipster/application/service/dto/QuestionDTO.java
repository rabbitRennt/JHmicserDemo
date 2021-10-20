package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Question} entity.
 */
public class Question implements Serializable {

  private Long id;

  private String questionType;

  private Integer score;

  private Integer gradeLevel;

  private Integer difficult;

  private String correct;

  private Integer infoTextContentId;

  private String createBy;

  private Instant createDate;

  private Integer status;

  private Boolean deleted;

  private SubjectDTO subject;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestionType() {
    return questionType;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Integer getDifficult() {
    return difficult;
  }

  public void setDifficult(Integer difficult) {
    this.difficult = difficult;
  }

  public String getCorrect() {
    return correct;
  }

  public void setCorrect(String correct) {
    this.correct = correct;
  }

  public Integer getInfoTextContentId() {
    return infoTextContentId;
  }

  public void setInfoTextContentId(Integer infoTextContentId) {
    this.infoTextContentId = infoTextContentId;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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
    if (!(o instanceof Question)) {
      return false;
    }

    Question question = (Question) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, question.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", questionType='" + getQuestionType() + "'" +
            ", score=" + getScore() +
            ", gradeLevel=" + getGradeLevel() +
            ", difficult=" + getDifficult() +
            ", correct='" + getCorrect() + "'" +
            ", infoTextContentId=" + getInfoTextContentId() +
            ", createBy='" + getCreateBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", status=" + getStatus() +
            ", deleted='" + getDeleted() + "'" +
            ", subject=" + getSubject() +
            "}";
    }
}
