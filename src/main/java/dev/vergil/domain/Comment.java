package dev.vergil.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @CreationTimestamp
    @Column(name = "date", updatable = false)
    private Instant date = Instant.now();;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @OneToMany(mappedBy = "child")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Comment> parents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    private User createBy;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    private Ticket ticket;

    @ManyToOne
    @JsonIgnoreProperties(value = "parents", allowSetters = true)
    private Comment child;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public Comment date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public Comment text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Comment> getParents() {
        return parents;
    }

    public Comment parents(Set<Comment> comments) {
        this.parents = comments;
        return this;
    }

    public Comment addParent(Comment comment) {
        this.parents.add(comment);
        comment.setChild(this);
        return this;
    }

    public Comment removeParent(Comment comment) {
        this.parents.remove(comment);
        comment.setChild(null);
        return this;
    }

    public void setParents(Set<Comment> comments) {
        this.parents = comments;
    }

    public User getCreateBy() {
        return createBy;
    }

    public Comment createBy(User user) {
        this.createBy = user;
        return this;
    }

    public void setCreateBy(User user) {
        this.createBy = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Comment ticket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Comment getChild() {
        return child;
    }

    public Comment child(Comment comment) {
        this.child = comment;
        return this;
    }

    public void setChild(Comment comment) {
        this.child = comment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
