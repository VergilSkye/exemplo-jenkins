package dev.vergil.service.mapper;


import dev.vergil.domain.*;
import dev.vergil.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, TicketMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "createBy.id", target = "createById")
    @Mapping(source = "createBy.login", target = "createByLogin")
    @Mapping(source = "ticket.id", target = "ticketId")
    @Mapping(source = "ticket.title", target = "ticketTitle")
    @Mapping(source = "child.id", target = "childId")
    CommentDTO toDto(Comment comment);

    @Mapping(target = "parents", ignore = true)
    @Mapping(target = "removeParent", ignore = true)
    @Mapping(source = "createById", target = "createBy")
    @Mapping(source = "ticketId", target = "ticket")
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
