package dev.vergil.service.mapper;


import dev.vergil.domain.*;
import dev.vergil.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "login.id", target = "loginId")
    @Mapping(source = "child.id", target = "childId")
    CommentDTO toDto(Comment comment);

    @Mapping(target = "parents", ignore = true)
    @Mapping(target = "removeParent", ignore = true)
    @Mapping(source = "loginId", target = "login")
    @Mapping(source = "childId", target = "child")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
