package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.ExamPaperAnswerDef} entity.
 */
public class ExamPaperAnswerDef implements Serializable {

  private Long id;

  private Integer questionType;

  private Integer customerScore;

  private Integer questionScore;

  private Long questionTextContentId;

  private String answer;

  private Boolean extConte;

  private String createBy;

  private Instant createDate;

  private Integer itemOrder;

  private QuestionDTO question;

  private ExamPaperAnswerDTO examPaperAnswer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getQuestionType() {
    return questionType;
  }

  public void setQuestionType(Integer questionType) {
    this.questionType = questionType;
  }

  public Integer getCustomerScore() {
    return customerScore;
  }

  public void setCustomerScore(Integer customerScore) {
    this.customerScore = customerScore;
  }

  public Integer getQuestionScore() {
    return questionScore;
  }

  public void setQuestionScore(Integer questionScore) {
    this.questionScore = questionScore;
  }

  public Long getQuestionTextContentId() {
    return questionTextContentId;
  }

  public void setQuestionTextContentId(Long questionTextContentId) {
    this.questionTextContentId = questionTextContentId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Boolean getExtConte() {
    return extConte;
  }

  public void setExtConte(Boolean extConte) {
    this.extConte = extConte;
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

  public Integer getItemOrder() {
    return itemOrder;
  }

  public void setItemOrder(Integer itemOrder) {
    this.itemOrder = itemOrder;
  }

  public QuestionDTO getQuestion() {
    return question;
  }

  public void setQuestion(QuestionDTO question) {
    this.question = question;
  }

  public ExamPaperAnswerDTO getExamPaperAnswer() {
    return examPaperAnswer;
  }

  public void setExamPaperAnswer(ExamPaperAnswerDTO examPaperAnswer) {
    this.examPaperAnswer = examPaperAnswer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaperAnswerDef)) {
      return false;
    }

    ExamPaperAnswerDef examPaperAnswerDef = (ExamPaperAnswerDef) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, examPaperAnswerDef.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ExamPaperAnswerDef{" +
            "id=" + getId() +
            ", questionType=" + getQuestionType() +
            ", customerScore=" + getCustomerScore() +
            ", questionScore=" + getQuestionScore() +
            ", questionTextContentId=" + getQuestionTextContentId() +
            ", answer='" + getAnswer() + "'" +
            ", extConte='" + getExtConte() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", itemOrder=" + getItemOrder() +
            ", question=" + getQuestion() +
            ", examPaperAnswer=" + getExamPaperAnswer() +
            "}";
    }
}
