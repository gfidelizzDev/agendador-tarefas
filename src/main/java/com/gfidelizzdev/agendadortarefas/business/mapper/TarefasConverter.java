package com.gfidelizzdev.agendadortarefas.business.mapper;

import com.gfidelizzdev.agendadortarefas.business.dto.TarefasDTO;
import com.gfidelizzdev.agendadortarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
