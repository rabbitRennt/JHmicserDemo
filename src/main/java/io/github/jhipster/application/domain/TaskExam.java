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
 * A TaskExam.
 */
@Entity
@Table(name = "task_exam")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaskExam implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "grade_level")
  private Integer gradeLevel;

  @Column(name = "frame_text_content_id")
  private Long frameTextContentId;

  @Column(name = "create_by")
  private String createBy;

  @Column(name = "create_by_user_name")
  private String createByUserName;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToMany(mappedBy = "taskExam")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "taskExam" }, allowSetters = true)
  private Set<TaskExamAnswer> taskExamAnswers = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "examPaperAnswers", "taskExams", "subject" },
    allowSetters = true
  )
  private ExamPaper examPaper;

  @ManyToOne
  @JsonIgnoreProperties(value = { "taskExams" }, allowSetters = true)
  private TextContent textContent;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public TaskExam id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public TaskExam title(String title) {
    this.setTitle(title);
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getGradeLevel() {
    return this.gradeLevel;
  }

  public TaskExam gradeLevel(Integer gradeLevel) {
    this.setGradeLevel(gradeLevel);
    return this;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public Long getFrameTextContentId() {
    return this.frameTextContentId;
  }

  public TaskExam frameTextContentId(Long frameTextContentId) {
    this.setFrameTextContentId(frameTextContentId);
    return this;
  }

  public void setFrameTextContentId(Long frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }

  public String getCreateBy() {
    return this.createBy;
  }

  public TaskExam createBy(String createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getCreateByUserName() {
    return this.createByUserName;
  }

  public TaskExam createByUserName(String createByUserName) {
    this.setCreateByUserName(createByUserName);
    return this;
  }

  public void setCreateByUserName(String createByUserName) {
    this.createByUserName = createByUserName;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public TaskExam createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public TaskExam deleted(Boolean deleted) {
    this.setDeleted(deleted);
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Set<TaskExamAnswer> getTaskExamAnswers() {
    return this.taskExamAnswers;
  }

  public void setTaskExamAnswers(Set<TaskExamAnswer> taskExamAnswers) {
    if (this.taskExamAnswers != null) {
      this.taskExamAnswers.forEach(i -> i.setTaskExam(null));
    }
    if (taskExamAnswers != null) {
      taskExamAnswers.forEach(i -> i.setTaskExam(this));
    }
    this.taskExamAnswers = taskExamAnswers;
  }

  public TaskExam taskExamAnswers(Set<TaskExamAnswer> taskExamAnswers) {
    this.setTaskExamAnswers(taskExamAnswers);
    return this;
  }

  public TaskExam addTaskExamAnswer(TaskExamAnswer taskExamAnswer) {
    this.taskExamAnswers.add(taskExamAnswer);
    taskExamAnswer.setTaskExam(this);
    return this;
  }

  public TaskExam removeTaskExamAnswer(TaskExamAnswer taskExamAnswer) {
    this.taskExamAnswers.remove(taskExamAnswer);
    taskExamAnswer.setTaskExam(null);
    return this;
  }

  public ExamPaper getExamPaper() {
    return this.examPaper;
  }

  public void setExamPaper(ExamPaper examPaper) {
    this.examPaper = examPaper;
  }

  public TaskExam examPaper(ExamPaper examPaper) {
    this.setExamPaper(examPaper);
    return this;
  }

  public TextContent getTextContent() {
    return this.textContent;
  }

  public void setTextContent(TextContent textContent) {
    this.textContent = textContent;
  }

  public TaskExam textContent(TextContent textContent) {
    this.setTextContent(textContent);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaskExam)) {
      return false;
    }
    return id != null && id.equals(((TaskExam) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
