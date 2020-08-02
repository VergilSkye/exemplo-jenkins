package dev.vergil.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import dev.vergil.domain.enumeration.Status;
import dev.vergil.domain.enumeration.Type;
import dev.vergil.domain.enumeration.Priority;

/**
 * A DTO for the {@link dev.vergil.domain.Ticket} entity.
 */
public class TicketDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private LocalDate dueDate;

    private Instant date;

    private Status status;

    private Type type;

    private Priority priority;


    private Long projectId;

    private String projectName;

    private Long assignedToId;

    private String assignedToLogin;

    private Long reportedById;

    private String reportedByLogin;
    private Set<LabelDTO> labels = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long userId) {
        this.assignedToId = userId;
    }

    public String getAssignedToLogin() {
        return assignedToLogin;
    }

    public void setAssignedToLogin(String userLogin) {
        this.assignedToLogin = userLogin;
    }

    public Long getReportedById() {
        return reportedById;
    }

    public void setReportedById(Long userId) {
        this.reportedById = userId;
    }

    public String getReportedByLogin() {
        return reportedByLogin;
    }

    public void setReportedByLogin(String userLogin) {
        this.reportedByLogin = userLogin;
    }

    public Set<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelDTO> labels) {
        this.labels = labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketDTO)) {
            return false;
        }

        return id != null && id.equals(((TicketDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", priority='" + getPriority() + "'" +
            ", projectId=" + getProjectId() +
            ", projectName='" + getProjectName() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", assignedToLogin='" + getAssignedToLogin() + "'" +
            ", reportedById=" + getReportedById() +
            ", reportedByLogin='" + getReportedByLogin() + "'" +
            ", labels='" + getLabels() + "'" +
            "}";
    }
}
