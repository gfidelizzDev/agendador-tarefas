package com.gfidelizzdev.agendadortarefas.business;

import com.gfidelizzdev.agendadortarefas.business.dto.TarefasDTO;
import com.gfidelizzdev.agendadortarefas.business.mapper.TarefasConverter;
import com.gfidelizzdev.agendadortarefas.infraestructure.entity.TarefasEntity;
import com.gfidelizzdev.agendadortarefas.infraestructure.enums.StatusNotificacaoEnum;
import com.gfidelizzdev.agendadortarefas.infraestructure.repository.TarefasRepository;
import com.gfidelizzdev.agendadortarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }
}
