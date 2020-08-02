package dev.vergil.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dev.vergil.domain.enumeration.Status;

import dev.vergil.domain.enumeration.Type;

import dev.vergil.domain.enumeration.Priority;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @CreationTimestamp
    @Column(name = "date", updatable = false)
    private Instant date = Instant.now();;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "tickets", allowSetters = true)
    private Project project;

    @ManyToOne
    @JsonIgnoreProperties(value = "tickets", allowSetters = true)
    private User assignedTo;

    @ManyToOne
    @JsonIgnoreProperties(value = "tickets", allowSetters = true)
    private User reportedBy;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "ticket_label",
               joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"))
    private Set<Label> labels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Ticket title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Ticket description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Ticket dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Instant getDate() {
        return date;
    }

    public Ticket date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public Ticket status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public Ticket type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public Ticket priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public Ticket attachments(Set<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Ticket addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setTicket(this);
        return this;
    }

    public Ticket removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setTicket(null);
        return this;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Project getProject() {
        return project;
    }

    public Ticket project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Ticket assignedTo(User user) {
        this.assignedTo = user;
        return this;
    }

    public void setAssignedTo(User user) {
        this.assignedTo = user;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public Ticket reportedBy(User user) {
        this.reportedBy = user;
        return this;
    }

    public void setReportedBy(User user) {
        this.reportedBy = user;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public Ticket labels(Set<Label> labels) {
        this.labels = labels;
        return this;
    }

    public Ticket addLabel(Label label) {
        this.labels.add(label);
        label.getTickets().add(this);
        return this;
    }

    public Ticket removeLabel(Label label) {
        this.labels.remove(label);
        label.getTickets().remove(this);
        return this;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }
}
