package dev.vergil.service.mapper;


import dev.vergil.domain.*;
import dev.vergil.service.dto.TicketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ticket} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProjectMapper.class, UserMapper.class, LabelMapper.class})
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket> {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.name", target = "projectName")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "assignedTo.login", target = "assignedToLogin")
    @Mapping(source = "reportedBy.id", target = "reportedById")
    @Mapping(source = "reportedBy.login", target = "reportedByLogin")
    TicketDTO toDto(Ticket ticket);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "removeAttachment", ignore = true)
    @Mapping(source = "projectId", target = "project")
    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(source = "reportedById", target = "reportedBy")
    @Mapping(target = "removeLabel", ignore = true)
    Ticket toEntity(TicketDTO ticketDTO);

    default Ticket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setId(id);
        return ticket;
    }
}
