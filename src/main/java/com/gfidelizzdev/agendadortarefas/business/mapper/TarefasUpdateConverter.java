package com.gfidelizzdev.agendadortarefas.business.mapper;

import com.gfidelizzdev.agendadortarefas.business.dto.TarefasDTORecord;
import com.gfidelizzdev.agendadortarefas.infraestructure.Entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
