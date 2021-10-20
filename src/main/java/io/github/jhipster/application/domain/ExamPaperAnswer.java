package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExamPaperAnswer.
 */
@Entity
@Table(name = "exam_paper_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamPaperAnswer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "paper_name")
  private String paperName;

  @Column(name = "paper_type")
  private Integer paperType;

  @Column(name = "system_score")
  private Integer systemScore;

  @Column(name = "user_score")
  private Integer userScore;

  @Column(name = "paper_score")
  private Integer paperScore;

  @Column(name = "question_correct")
  private Integer questionCorrect;

  @Column(name = "question_count")
  private Integer questionCount;

  @Column(name = "do_time")
  private Integer doTime;

  @Column(name = "status")
  private Integer status;

  @Column(name = "create_by")
  private Long createBy;

  @Column(name = "create_date")
  private Long createDate;

  @OneToMany(mappedBy = "examPaperAnswer")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "question", "examPaperAnswer" },
    allowSetters = true
  )
  private Set<ExamPaperAnswerDef> examPaperAnswerDefs = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "examPaperAnswers", "taskExams", "subject" },
    allowSetters = true
  )
  private ExamPaper examPaper;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public ExamPaperAnswer id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPaperName() {
    return this.paperName;
  }

  public ExamPaperAnswer paperName(String paperName) {
    this.setPaperName(paperName);
    return this;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }

  public Integer getPaperType() {
    return this.paperType;
  }

  public ExamPaperAnswer paperType(Integer paperType) {
    this.setPaperType(paperType);
    return this;
  }

  public void setPaperType(Integer paperType) {
    this.paperType = paperType;
  }

  public Integer getSystemScore() {
    return this.systemScore;
  }

  public ExamPaperAnswer systemScore(Integer systemScore) {
    this.setSystemScore(systemScore);
    return this;
  }

  public void setSystemScore(Integer systemScore) {
    this.systemScore = systemScore;
  }

  public Integer getUserScore() {
    return this.userScore;
  }

  public ExamPaperAnswer userScore(Integer userScore) {
    this.setUserScore(userScore);
    return this;
  }

  public void setUserScore(Integer userScore) {
    this.userScore = userScore;
  }

  public Integer getPaperScore() {
    return this.paperScore;
  }

  public ExamPaperAnswer paperScore(Integer paperScore) {
    this.setPaperScore(paperScore);
    return this;
  }

  public void setPaperScore(Integer paperScore) {
    this.paperScore = paperScore;
  }

  public Integer getQuestionCorrect() {
    return this.questionCorrect;
  }

  public ExamPaperAnswer questionCorrect(Integer questionCorrect) {
    this.setQuestionCorrect(questionCorrect);
    return this;
  }

  public void setQuestionCorrect(Integer questionCorrect) {
    this.questionCorrect = questionCorrect;
  }

  public Integer getQuestionCount() {
    return this.questionCount;
  }

  public ExamPaperAnswer questionCount(Integer questionCount) {
    this.setQuestionCount(questionCount);
    return this;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Integer getDoTime() {
    return this.doTime;
  }

  public ExamPaperAnswer doTime(Integer doTime) {
    this.setDoTime(doTime);
    return this;
  }

  public void setDoTime(Integer doTime) {
    this.doTime = doTime;
  }

  public Integer getStatus() {
    return this.status;
  }

  public ExamPaperAnswer status(Integer status) {
    this.setStatus(status);
    return this;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getCreateBy() {
    return this.createBy;
  }

  public ExamPaperAnswer createBy(Long createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Long getCreateDate() {
    return this.createDate;
  }

  public ExamPaperAnswer createDate(Long createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  public Set<ExamPaperAnswerDef> getExamPaperAnswerDefs() {
    return this.examPaperAnswerDefs;
  }

  public void setExamPaperAnswerDefs(
    Set<ExamPaperAnswerDef> examPaperAnswerDefs
  ) {
    if (this.examPaperAnswerDefs != null) {
      this.examPaperAnswerDefs.forEach(i -> i.setExamPaperAnswer(null));
    }
    if (examPaperAnswerDefs != null) {
      examPaperAnswerDefs.forEach(i -> i.setExamPaperAnswer(this));
    }
    this.examPaperAnswerDefs = examPaperAnswerDefs;
  }

  public ExamPaperAnswer examPaperAnswerDefs(
    Set<ExamPaperAnswerDef> examPaperAnswerDefs
  ) {
    this.setExamPaperAnswerDefs(examPaperAnswerDefs);
    return this;
  }

  public ExamPaperAnswer addExamPaperAnswerDef(
    ExamPaperAnswerDef examPaperAnswerDef
  ) {
    this.examPaperAnswerDefs.add(examPaperAnswerDef);
    examPaperAnswerDef.setExamPaperAnswer(this);
    return this;
  }

  public ExamPaperAnswer removeExamPaperAnswerDef(
    ExamPaperAnswerDef examPaperAnswerDef
  ) {
    this.examPaperAnswerDefs.remove(examPaperAnswerDef);
    examPaperAnswerDef.setExamPaperAnswer(null);
    return this;
  }

  public ExamPaper getExamPaper() {
    return this.examPaper;
  }

  public void setExamPaper(ExamPaper examPaper) {
    this.examPaper = examPaper;
  }

  public ExamPaperAnswer examPaper(ExamPaper examPaper) {
    this.setExamPaper(examPaper);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaperAnswer)) {
      return false;
    }
    return id != null && id.equals(((ExamPaperAnswer) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
