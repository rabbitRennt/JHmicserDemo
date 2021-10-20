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
 * A TextContent.
 */
@Entity
@Table(name = "text_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TextContent implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Lob
  @Column(name = "content")
  private byte[] content;

  @Column(name = "content_content_type")
  private String contentContentType;

  @Column(name = "create_date")
  private Instant createDate;

  @OneToMany(mappedBy = "textContent")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "taskExamAnswers", "examPaper", "textContent" },
    allowSetters = true
  )
  private Set<TaskExam> taskExams = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public TextContent id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte[] getContent() {
    return this.content;
  }

  public TextContent content(byte[] content) {
    this.setContent(content);
    return this;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getContentContentType() {
    return this.contentContentType;
  }

  public TextContent contentContentType(String contentContentType) {
    this.contentContentType = contentContentType;
    return this;
  }

  public void setContentContentType(String contentContentType) {
    this.contentContentType = contentContentType;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public TextContent createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Set<TaskExam> getTaskExams() {
    return this.taskExams;
  }

  public void setTaskExams(Set<TaskExam> taskExams) {
    if (this.taskExams != null) {
      this.taskExams.forEach(i -> i.setTextContent(null));
    }
    if (taskExams != null) {
      taskExams.forEach(i -> i.setTextContent(this));
    }
    this.taskExams = taskExams;
  }

  public TextContent taskExams(Set<TaskExam> taskExams) {
    this.setTaskExams(taskExams);
    return this;
  }

  public TextContent addTaskExam(TaskExam taskExam) {
    this.taskExams.add(taskExam);
    taskExam.setTextContent(this);
    return this;
  }

  public TextContent removeTaskExam(TaskExam taskExam) {
    this.taskExams.remove(taskExam);
    taskExam.setTextContent(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TextContent)) {
      return false;
    }
    return id != null && id.equals(((TextContent) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TextContent{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", contentContentType='" + getContentContentType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
