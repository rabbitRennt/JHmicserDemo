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
 * A ExamPaper.
 */
@Entity
@Table(name = "exam_paper")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamPaper implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "paper_type")
  private Integer paperType;

  @Column(name = "grade_level")
  private Integer gradeLevel;

  @Column(name = "score")
  private Integer score;

  @Column(name = "question_count")
  private Integer questionCount;

  @Column(name = "suggest_time")
  private Integer suggestTime;

  @Column(name = "limit_start_date")
  private Instant limitStartDate;

  @Column(name = "limit_ene_date")
  private Instant limitEneDate;

  @Column(name = "frame_text_content_id")
  private Integer frameTextContentId;

  @Column(name = "create_by")
  private Long createBy;

  @Column(name = "create_date")
  private Long createDate;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToMany(mappedBy = "examPaper")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "examPaperAnswerDefs", "examPaper" },
    allowSetters = true
  )
  private Set<ExamPaperAnswer> examPaperAnswers = new HashSet<>();

  @OneToMany(mappedBy = "examPaper")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "taskExamAnswers", "examPaper", "textContent" },
    allowSetters = true
  )
  private Set<TaskExam> taskExams = new HashSet<>();

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

  public ExamPaper id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public ExamPaper name(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPaperType() {
    return this.paperType;
  }

  public ExamPaper paperType(Integer paperType) {
    this.setPaperType(paperType);
    return this;
  }

  public void setPaperType(Integer paperType) {
    this.paperType = paperType;
  }

  public Integer getGradeLevel() {
    return this.gradeLevel;
  }

  public ExamPaper gradeLevel(Integer gradeLevel) {
    this.setGradeLevel(gradeLevel);
    return this;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Integer getScore() {
    return this.score;
  }

  public ExamPaper score(Integer score) {
    this.setScore(score);
    return this;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getQuestionCount() {
    return this.questionCount;
  }

  public ExamPaper questionCount(Integer questionCount) {
    this.setQuestionCount(questionCount);
    return this;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Integer getSuggestTime() {
    return this.suggestTime;
  }

  public ExamPaper suggestTime(Integer suggestTime) {
    this.setSuggestTime(suggestTime);
    return this;
  }

  public void setSuggestTime(Integer suggestTime) {
    this.suggestTime = suggestTime;
  }

  public Instant getLimitStartDate() {
    return this.limitStartDate;
  }

  public ExamPaper limitStartDate(Instant limitStartDate) {
    this.setLimitStartDate(limitStartDate);
    return this;
  }

  public void setLimitStartDate(Instant limitStartDate) {
    this.limitStartDate = limitStartDate;
  }

  public Instant getLimitEneDate() {
    return this.limitEneDate;
  }

  public ExamPaper limitEneDate(Instant limitEneDate) {
    this.setLimitEneDate(limitEneDate);
    return this;
  }

  public void setLimitEneDate(Instant limitEneDate) {
    this.limitEneDate = limitEneDate;
  }

  public Integer getFrameTextContentId() {
    return this.frameTextContentId;
  }

  public ExamPaper frameTextContentId(Integer frameTextContentId) {
    this.setFrameTextContentId(frameTextContentId);
    return this;
  }

  public void setFrameTextContentId(Integer frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }

  public Long getCreateBy() {
    return this.createBy;
  }

  public ExamPaper createBy(Long createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Long getCreateDate() {
    return this.createDate;
  }

  public ExamPaper createDate(Long createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public ExamPaper deleted(Boolean deleted) {
    this.setDeleted(deleted);
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Set<ExamPaperAnswer> getExamPaperAnswers() {
    return this.examPaperAnswers;
  }

  public void setExamPaperAnswers(Set<ExamPaperAnswer> examPaperAnswers) {
    if (this.examPaperAnswers != null) {
      this.examPaperAnswers.forEach(i -> i.setExamPaper(null));
    }
    if (examPaperAnswers != null) {
      examPaperAnswers.forEach(i -> i.setExamPaper(this));
    }
    this.examPaperAnswers = examPaperAnswers;
  }

  public ExamPaper examPaperAnswers(Set<ExamPaperAnswer> examPaperAnswers) {
    this.setExamPaperAnswers(examPaperAnswers);
    return this;
  }

  public ExamPaper addExamPaperAnswer(ExamPaperAnswer examPaperAnswer) {
    this.examPaperAnswers.add(examPaperAnswer);
    examPaperAnswer.setExamPaper(this);
    return this;
  }

  public ExamPaper removeExamPaperAnswer(ExamPaperAnswer examPaperAnswer) {
    this.examPaperAnswers.remove(examPaperAnswer);
    examPaperAnswer.setExamPaper(null);
    return this;
  }

  public Set<TaskExam> getTaskExams() {
    return this.taskExams;
  }

  public void setTaskExams(Set<TaskExam> taskExams) {
    if (this.taskExams != null) {
      this.taskExams.forEach(i -> i.setExamPaper(null));
    }
    if (taskExams != null) {
      taskExams.forEach(i -> i.setExamPaper(this));
    }
    this.taskExams = taskExams;
  }

  public ExamPaper taskExams(Set<TaskExam> taskExams) {
    this.setTaskExams(taskExams);
    return this;
  }

  public ExamPaper addTaskExam(TaskExam taskExam) {
    this.taskExams.add(taskExam);
    taskExam.setExamPaper(this);
    return this;
  }

  public ExamPaper removeTaskExam(TaskExam taskExam) {
    this.taskExams.remove(taskExam);
    taskExam.setExamPaper(null);
    return this;
  }

  public Subject getSubject() {
    return this.subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public ExamPaper subject(Subject subject) {
    this.setSubject(subject);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExamPaper)) {
      return false;
    }
    return id != null && id.equals(((ExamPaper) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
