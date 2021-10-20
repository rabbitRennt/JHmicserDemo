package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "question_type")
  private String questionType;

  @Column(name = "score")
  private Integer score;

  @Column(name = "grade_level")
  private Integer gradeLevel;

  @Column(name = "difficult")
  private Integer difficult;

  @Column(name = "correct")
  private String correct;

  @Column(name = "info_text_content_id")
  private Integer infoTextContentId;

  @Column(name = "create_by")
  private String createBy;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "status")
  private Integer status;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToMany(mappedBy = "question")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "question", "examPaperAnswer" },
    allowSetters = true
  )
  private Set<ExamPaperAnswerDef> examPaperAnswerDefs = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "questions", "examPapers" },
    allowSetters = true
  )
  private Subject subject;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Question id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestionType() {
    return this.questionType;
  }

  public Question questionType(String questionType) {
    this.setQuestionType(questionType);
    return this;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public Integer getScore() {
    return this.score;
  }

  public Question score(Integer score) {
    this.setScore(score);
    return this;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getGradeLevel() {
    return this.gradeLevel;
  }

  public Question gradeLevel(Integer gradeLevel) {
    this.setGradeLevel(gradeLevel);
    return this;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Integer getDifficult() {
    return this.difficult;
  }

  public Question difficult(Integer difficult) {
    this.setDifficult(difficult);
    return this;
  }

  public void setDifficult(Integer difficult) {
    this.difficult = difficult;
  }

  public String getCorrect() {
    return this.correct;
  }

  public Question correct(String correct) {
    this.setCorrect(correct);
    return this;
  }

  public void setCorrect(String correct) {
    this.correct = correct;
  }

  public Integer getInfoTextContentId() {
    return this.infoTextContentId;
  }

  public Question infoTextContentId(Integer infoTextContentId) {
    this.setInfoTextContentId(infoTextContentId);
    return this;
  }

  public void setInfoTextContentId(Integer infoTextContentId) {
    this.infoTextContentId = infoTextContentId;
  }

  public String getCreateBy() {
    return this.createBy;
  }

  public Question createBy(String createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public Question createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Integer getStatus() {
    return this.status;
  }

  public Question status(Integer status) {
    this.setStatus(status);
    return this;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public Question deleted(Boolean deleted) {
    this.setDeleted(deleted);
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Set<ExamPaperAnswerDef> getExamPaperAnswerDefs() {
    return this.examPaperAnswerDefs;
  }

  public void setExamPaperAnswerDefs(
    Set<ExamPaperAnswerDef> examPaperAnswerDefs
  ) {
    if (this.examPaperAnswerDefs != null) {
      this.examPaperAnswerDefs.forEach(i -> i.setQuestion(null));
    }
    if (examPaperAnswerDefs != null) {
      examPaperAnswerDefs.forEach(i -> i.setQuestion(this));
    }
    this.examPaperAnswerDefs = examPaperAnswerDefs;
  }

  public Question examPaperAnswerDefs(
    Set<ExamPaperAnswerDef> examPaperAnswerDefs
  ) {
    this.setExamPaperAnswerDefs(examPaperAnswerDefs);
    return this;
  }

  public Question addExamPaperAnswerDef(ExamPaperAnswerDef examPaperAnswerDef) {
    this.examPaperAnswerDefs.add(examPaperAnswerDef);
    examPaperAnswerDef.setQuestion(this);
    return this;
  }

  public Question removeExamPaperAnswerDef(
    ExamPaperAnswerDef examPaperAnswerDef
  ) {
    this.examPaperAnswerDefs.remove(examPaperAnswerDef);
    examPaperAnswerDef.setQuestion(null);
    return this;
  }

  public Subject getSubject() {
    return this.subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Question subject(Subject subject) {
    this.setSubject(subject);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Question)) {
      return false;
    }
    return id != null && id.equals(((Question) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
