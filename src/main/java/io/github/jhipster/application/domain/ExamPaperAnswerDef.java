package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExamPaperAnswerDef.
 */
@Entity
@Table(name = "exam_paper_answer_def")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamPaperAnswerDef implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "question_type")
  private Integer questionType;

  @Column(name = "customer_score")
  private Integer customerScore;

  @Column(name = "question_score")
  private Integer questionScore;

  @Column(name = "question_text_content_id")
  private Long questionTextContentId;

  @Column(name = "answer")
  private String answer;

  @Column(name = "ext_conte")
  private Boolean extConte;

  @Column(name = "create_by")
  private String createBy;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "item_order")
  private Integer itemOrder;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "examPaperAnswerDefs", "subject" },
    allowSetters = true
  )
  private Question question;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "examPaperAnswerDefs", "examPaper" },
    allowSetters = true
  )
  private ExamPaperAnswer examPaperAnswer;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public ExamPaperAnswerDef id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getQuestionType() {
    return this.questionType;
  }

  public ExamPaperAnswerDef questionType(Integer questionType) {
    this.setQuestionType(questionType);
    return this;
  }

  public void setQuestionType(Integer questionType) {
    this.questionType = questionType;
  }

  public Integer getCustomerScore() {
    return this.customerScore;
  }

  public ExamPaperAnswerDef customerScore(Integer customerScore) {
    this.setCustomerScore(customerScore);
    return this;
  }

  public void setCustomerScore(Integer customerScore) {
    this.customerScore = customerScore;
  }

  public Integer getQuestionScore() {
    return this.questionScore;
  }

  public ExamPaperAnswerDef questionScore(Integer questionScore) {
    this.setQuestionScore(questionScore);
    return this;
  }

  public void setQuestionScore(Integer questionScore) {
    this.questionScore = questionScore;
  }

  public Long getQuestionTextContentId() {
    return this.questionTextContentId;
  }

  public ExamPaperAnswerDef questionTextContentId(Long questionTextContentId) {
    this.setQuestionTextContentId(questionTextContentId);
    return this;
  }

  public void setQuestionTextContentId(Long questionTextContentId) {
    this.questionTextContentId = questionTextContentId;
  }

  public String getAnswer() {
    return this.answer;
  }

  public ExamPaperAnswerDef answer(String answer) {
    this.setAnswer(answer);
    return this;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Boolean getExtConte() {
    return this.extConte;
  }

  public ExamPaperAnswerDef extConte(Boolean extConte) {
    this.setExtConte(extConte);
    return this;
  }

  public void setExtConte(Boolean extConte) {
    this.extConte = extConte;
  }

  public String getCreateBy() {
    return this.createBy;
  }

  public ExamPaperAnswerDef createBy(String createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public ExamPaperAnswerDef createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Integer getItemOrder() {
    return this.itemOrder;
  }

  public ExamPaperAnswerDef itemOrder(Integer itemOrder) {
    this.setItemOrder(itemOrder);
    return this;
  }

  public void setItemOrder(Integer itemOrder) {
    this.itemOrder = itemOrder;
  }

  public Question getQuestion() {
    return this.question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public ExamPaperAnswerDef question(Question question) {
    this.setQuestion(question);
    return this;
  }

  public ExamPaperAnswer getExamPaperAnswer() {
    return this.examPaperAnswer;
  }

  public void setExamPaperAnswer(ExamPaperAnswer examPaperAnswer) {
    this.examPaperAnswer = examPaperAnswer;
  }

  public ExamPaperAnswerDef examPaperAnswer(ExamPaperAnswer examPaperAnswer) {
    this.setExamPaperAnswer(examPaperAnswer);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaperAnswerDef)) {
      return false;
    }
    return id != null && id.equals(((ExamPaperAnswerDef) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
