package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.TaskExamAnswer} entity.
 */
public class TaskExamAnswer implements Serializable {

  private Long id;

  private String createBy;

  private Instant createDate;

  private Long textContentId;

  private TaskExamDTO taskExam;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getTextContentId() {
    return textContentId;
  }

  public void setTextContentId(Long textContentId) {
    this.textContentId = textContentId;
  }

  public TaskExamDTO getTaskExam() {
    return taskExam;
  }

  public void setTaskExam(TaskExamDTO taskExam) {
    this.taskExam = taskExam;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaskExamAnswer)) {
      return false;
    }

    TaskExamAnswer taskExamAnswer = (TaskExamAnswer) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, taskExamAnswer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TaskExamAnswer{" +
            "id=" + getId() +
            ", createBy='" + getCreateBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", textContentId=" + getTextContentId() +
            ", taskExam=" + getTaskExam() +
            "}";
    }
}
