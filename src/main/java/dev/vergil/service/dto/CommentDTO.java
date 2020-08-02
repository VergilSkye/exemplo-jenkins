package dev.vergil.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link dev.vergil.domain.Comment} entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    private Instant date;

    @NotNull
    private String text;


    private Long createById;

    private String createByLogin;

    private Long ticketId;

    private String ticketTitle;

    private Long childId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long userId) {
        this.createById = userId;
    }

    public String getCreateByLogin() {
        return createByLogin;
    }

    public void setCreateByLogin(String userLogin) {
        this.createByLogin = userLogin;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long commentId) {
        this.childId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentDTO)) {
            return false;
        }

        return id != null && id.equals(((CommentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", text='" + getText() + "'" +
            ", createById=" + getCreateById() +
            ", createByLogin='" + getCreateByLogin() + "'" +
            ", ticketId=" + getTicketId() +
            ", ticketTitle='" + getTicketTitle() + "'" +
            ", childId=" + getChildId() +
            "}";
    }
}
