package dev.vergil.service.mapper;


import dev.vergil.domain.*;
import dev.vergil.service.dto.AttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {

    @Mapping(source = "ticket.id", target = "ticketId")
    @Mapping(source = "ticket.title", target = "ticketTitle")
    AttachmentDTO toDto(Attachment attachment);

    @Mapping(source = "ticketId", target = "ticket")
    Attachment toEntity(AttachmentDTO attachmentDTO);

    default Attachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setId(id);
        return attachment;
    }
}
