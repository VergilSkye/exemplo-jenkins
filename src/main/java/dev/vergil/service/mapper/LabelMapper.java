package dev.vergil.service.mapper;


import dev.vergil.domain.*;
import dev.vergil.service.dto.LabelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Label} and its DTO {@link LabelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LabelMapper extends EntityMapper<LabelDTO, Label> {


    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "removeTicket", ignore = true)
    Label toEntity(LabelDTO labelDTO);

    default Label fromId(Long id) {
        if (id == null) {
            return null;
        }
        Label label = new Label();
        label.setId(id);
        return label;
    }
}
