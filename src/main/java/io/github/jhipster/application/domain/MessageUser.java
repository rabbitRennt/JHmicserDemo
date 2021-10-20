package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MessageUser.
 */
@Entity
@Table(name = "message_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "receive_user_id")
  private Long receiveUserId;

  @Column(name = "receive_username")
  private String receiveUsername;

  @Column(name = "receive_real_name")
  private String receiveRealName;

  @Column(name = "jhi_read")
  private Boolean read;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "read_date")
  private Instant readDate;

  @ManyToOne
  @JsonIgnoreProperties(value = { "messageUsers" }, allowSetters = true)
  private Message message;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public MessageUser id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getReceiveUserId() {
    return this.receiveUserId;
  }

  public MessageUser receiveUserId(Long receiveUserId) {
    this.setReceiveUserId(receiveUserId);
    return this;
  }

  public void setReceiveUserId(Long receiveUserId) {
    this.receiveUserId = receiveUserId;
  }

  public String getReceiveUsername() {
    return this.receiveUsername;
  }

  public MessageUser receiveUsername(String receiveUsername) {
    this.setReceiveUsername(receiveUsername);
    return this;
  }

  public void setReceiveUsername(String receiveUsername) {
    this.receiveUsername = receiveUsername;
  }

  public String getReceiveRealName() {
    return this.receiveRealName;
  }

  public MessageUser receiveRealName(String receiveRealName) {
    this.setReceiveRealName(receiveRealName);
    return this;
  }

  public void setReceiveRealName(String receiveRealName) {
    this.receiveRealName = receiveRealName;
  }

  public Boolean getRead() {
    return this.read;
  }

  public MessageUser read(Boolean read) {
    this.setRead(read);
    return this;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public MessageUser createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public Instant getReadDate() {
    return this.readDate;
  }

  public MessageUser readDate(Instant readDate) {
    this.setReadDate(readDate);
    return this;
  }

  public void setReadDate(Instant readDate) {
    this.readDate = readDate;
  }

  public Message getMessage() {
    return this.message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public MessageUser message(Message message) {
    this.setMessage(message);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageUser)) {
      return false;
    }
    return id != null && id.equals(((MessageUser) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageUser{" +
            "id=" + getId() +
            ", receiveUserId=" + getReceiveUserId() +
            ", receiveUsername='" + getReceiveUsername() + "'" +
            ", receiveRealName='" + getReceiveRealName() + "'" +
            ", read='" + getRead() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", readDate='" + getReadDate() + "'" +
            "}";
    }
}
