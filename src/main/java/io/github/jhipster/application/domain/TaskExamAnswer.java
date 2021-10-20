package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaskExamAnswer.
 */
@Entity
@Table(name = "task_exam_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaskExamAnswer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "create_by")
  private String createBy;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "text_content_id")
  private Long textContentId;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "taskExamAnswers", "examPaper", "textContent" },
    allowSetters = true
  )
  private TaskExam taskExam;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public TaskExamAnswer id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreateBy() {
    return this.createBy;
  }

  public TaskExamAnswer createBy(String createBy) {
    this.setCreateBy(createBy);
    return this;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public TaskExamAnswer createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Long getTextContentId() {
    return this.textContentId;
  }

  public TaskExamAnswer textContentId(Long textContentId) {
    this.setTextContentId(textContentId);
    return this;
  }

  public void setTextContentId(Long textContentId) {
    this.textContentId = textContentId;
  }

  public TaskExam getTaskExam() {
    return this.taskExam;
  }

  public void setTaskExam(TaskExam taskExam) {
    this.taskExam = taskExam;
  }

  public TaskExamAnswer taskExam(TaskExam taskExam) {
    this.setTaskExam(taskExam);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaskExamAnswer)) {
      return false;
    }
    return id != null && id.equals(((TaskExamAnswer) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TaskExamAnswer{" +
            "id=" + getId() +
            ", createBy='" + getCreateBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", textContentId=" + getTextContentId() +
            "}";
    }
}
