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
 * A Message.
 */
@Entity
@Table(name = "message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "create_date")
  private Instant createDate;

  @Column(name = "send_userid")
  private String sendUserid;

  @Column(name = "send_username")
  private String sendUsername;

  @Column(name = "receive_user_count")
  private Integer receiveUserCount;

  @Column(name = "read_count")
  private Integer readCount;

  @OneToMany(mappedBy = "message")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "message" }, allowSetters = true)
  private Set<MessageUser> messageUsers = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Message id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public Message title(String title) {
    this.setTitle(title);
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public Message content(String content) {
    this.setContent(content);
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public Message createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public String getSendUserid() {
    return this.sendUserid;
  }

  public Message sendUserid(String sendUserid) {
    this.setSendUserid(sendUserid);
    return this;
  }

  public void setSendUserid(String sendUserid) {
    this.sendUserid = sendUserid;
  }

  public String getSendUsername() {
    return this.sendUsername;
  }

  public Message sendUsername(String sendUsername) {
    this.setSendUsername(sendUsername);
    return this;
  }

  public void setSendUsername(String sendUsername) {
    this.sendUsername = sendUsername;
  }

  public Integer getReceiveUserCount() {
    return this.receiveUserCount;
  }

  public Message receiveUserCount(Integer receiveUserCount) {
    this.setReceiveUserCount(receiveUserCount);
    return this;
  }

  public void setReceiveUserCount(Integer receiveUserCount) {
    this.receiveUserCount = receiveUserCount;
  }

  public Integer getReadCount() {
    return this.readCount;
  }

  public Message readCount(Integer readCount) {
    this.setReadCount(readCount);
    return this;
  }

  public void setReadCount(Integer readCount) {
    this.readCount = readCount;
  }

  public Set<MessageUser> getMessageUsers() {
    return this.messageUsers;
  }

  public void setMessageUsers(Set<MessageUser> messageUsers) {
    if (this.messageUsers != null) {
      this.messageUsers.forEach(i -> i.setMessage(null));
    }
    if (messageUsers != null) {
      messageUsers.forEach(i -> i.setMessage(this));
    }
    this.messageUsers = messageUsers;
  }

  public Message messageUsers(Set<MessageUser> messageUsers) {
    this.setMessageUsers(messageUsers);
    return this;
  }

  public Message addMessageUser(MessageUser messageUser) {
    this.messageUsers.add(messageUser);
    messageUser.setMessage(this);
    return this;
  }

  public Message removeMessageUser(MessageUser messageUser) {
    this.messageUsers.remove(messageUser);
    messageUser.setMessage(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Message)) {
      return false;
    }
    return id != null && id.equals(((Message) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", sendUserid='" + getSendUserid() + "'" +
            ", sendUsername='" + getSendUsername() + "'" +
            ", receiveUserCount=" + getReceiveUserCount() +
            ", readCount=" + getReadCount() +
            "}";
    }
}
