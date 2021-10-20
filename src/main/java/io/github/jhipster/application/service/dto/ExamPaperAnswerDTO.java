package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ExamPaperAnswer} entity.
 */
public class ExamPaperAnswer implements Serializable {

  private Long id;

  private String paperName;

  private Integer paperType;

  private Integer systemScore;

  private Integer userScore;

  private Integer paperScore;

  private Integer questionCorrect;

  private Integer questionCount;

  private Integer doTime;

  private Integer status;

  private Long createBy;

  private Long createDate;

  private ExamPaperDTO examPaper;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPaperName() {
    return paperName;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }

  public Integer getPaperType() {
    return paperType;
  }

  public void setPaperType(Integer paperType) {
    this.paperType = paperType;
  }

  public Integer getSystemScore() {
    return systemScore;
  }

  public void setSystemScore(Integer systemScore) {
    this.systemScore = systemScore;
  }

  public Integer getUserScore() {
    return userScore;
  }

  public void setUserScore(Integer userScore) {
    this.userScore = userScore;
  }

  public Integer getPaperScore() {
    return paperScore;
  }

  public void setPaperScore(Integer paperScore) {
    this.paperScore = paperScore;
  }

  public Integer getQuestionCorrect() {
    return questionCorrect;
  }

  public void setQuestionCorrect(Integer questionCorrect) {
    this.questionCorrect = questionCorrect;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Integer getDoTime() {
    return doTime;
  }

  public void setDoTime(Integer doTime) {
    this.doTime = doTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public ExamPaperDTO getExamPaper() {
    return examPaper;
  }

  public void setExamPaper(ExamPaperDTO examPaper) {
    this.examPaper = examPaper;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaperAnswer)) {
      return false;
    }

    ExamPaperAnswer examPaperAnswer = (ExamPaperAnswer) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, examPaperAnswer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ExamPaperAnswer{" +
            "id=" + getId() +
            ", paperName='" + getPaperName() + "'" +
            ", paperType=" + getPaperType() +
            ", systemScore=" + getSystemScore() +
            ", userScore=" + getUserScore() +
            ", paperScore=" + getPaperScore() +
            ", questionCorrect=" + getQuestionCorrect() +
            ", questionCount=" + getQuestionCount() +
            ", doTime=" + getDoTime() +
            ", status=" + getStatus() +
            ", createBy=" + getCreateBy() +
            ", createDate=" + getCreateDate() +
            ", examPaper=" + getExamPaper() +
            "}";
    }
}
