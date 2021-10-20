package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Subject.
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Subject implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "level")
  private Integer level;

  @Column(name = "level_name")
  private String levelName;

  @Column(name = "item_order")
  private Integer itemOrder;

  @Column(name = "deleted")
  private Boolean deleted;

  @OneToMany(mappedBy = "subject")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "examPaperAnswerDefs", "subject" },
    allowSetters = true
  )
  private Set<Question> questions = new HashSet<>();

  @OneToMany(mappedBy = "subject")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "examPaperAnswers", "taskExams", "subject" },
    allowSetters = true
  )
  private Set<ExamPaper> examPapers = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Subject id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public Subject name(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getLevel() {
    return this.level;
  }

  public Subject level(Integer level) {
    this.setLevel(level);
    return this;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getLevelName() {
    return this.levelName;
  }

  public Subject levelName(String levelName) {
    this.setLevelName(levelName);
    return this;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public Integer getItemOrder() {
    return this.itemOrder;
  }

  public Subject itemOrder(Integer itemOrder) {
    this.setItemOrder(itemOrder);
    return this;
  }

  public void setItemOrder(Integer itemOrder) {
    this.itemOrder = itemOrder;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public Subject deleted(Boolean deleted) {
    this.setDeleted(deleted);
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Set<Question> getQuestions() {
    return this.questions;
  }

  public void setQuestions(Set<Question> questions) {
    if (this.questions != null) {
      this.questions.forEach(i -> i.setSubject(null));
    }
    if (questions != null) {
      questions.forEach(i -> i.setSubject(this));
    }
    this.questions = questions;
  }

  public Subject questions(Set<Question> questions) {
    this.setQuestions(questions);
    return this;
  }

  public Subject addQuestion(Question question) {
    this.questions.add(question);
    question.setSubject(this);
    return this;
  }

  public Subject removeQuestion(Question question) {
    this.questions.remove(question);
    question.setSubject(null);
    return this;
  }

  public Set<ExamPaper> getExamPapers() {
    return this.examPapers;
  }

  public void setExamPapers(Set<ExamPaper> examPapers) {
    if (this.examPapers != null) {
      this.examPapers.forEach(i -> i.setSubject(null));
    }
    if (examPapers != null) {
      examPapers.forEach(i -> i.setSubject(this));
    }
    this.examPapers = examPapers;
  }

  public Subject examPapers(Set<ExamPaper> examPapers) {
    this.setExamPapers(examPapers);
    return this;
  }

  public Subject addExamPaper(ExamPaper examPaper) {
    this.examPapers.add(examPaper);
    examPaper.setSubject(this);
    return this;
  }

  public Subject removeExamPaper(ExamPaper examPaper) {
    this.examPapers.remove(examPaper);
    examPaper.setSubject(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Subject)) {
      return false;
    }
    return id != null && id.equals(((Subject) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", levelName='" + getLevelName() + "'" +
            ", itemOrder=" + getItemOrder() +
            ", deleted='" + getDeleted() + "'" +
            "}";
    }
}
