package dev.vergil.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;

/**
 * A DTO for the {@link dev.vergil.domain.Comment} entity.
 */
public class CommentDTO implements Serializable {
    
    private Long id;

    private ZonedDateTime date;

    private String text;


    private Long loginId;

    private Long childId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long userId) {
        this.loginId = userId;
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
            ", loginId=" + getLoginId() +
            ", childId=" + getChildId() +
            "}";
    }
}
