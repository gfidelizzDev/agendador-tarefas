package com.gfidelizzdev.agendadortarefas.business;

import com.gfidelizzdev.agendadortarefas.business.dto.TarefasDTORecord;
import com.gfidelizzdev.agendadortarefas.business.mapper.TarefasConverter;
import com.gfidelizzdev.agendadortarefas.business.mapper.TarefasUpdateConverter;
import com.gfidelizzdev.agendadortarefas.infraestructure.Entity.TarefasEntity;
import com.gfidelizzdev.agendadortarefas.infraestructure.Repository.TarefasRepository;
import com.gfidelizzdev.agendadortarefas.infraestructure.enums.StatusNotificacaoEnum;
import com.gfidelizzdev.agendadortarefas.infraestructure.exceptions.ResourceNotFoundException;
import com.gfidelizzdev.agendadortarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

     TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nometarefa(),
             dto.descricao(), LocalDateTime.now(), dto.dataEvento(),email, null, StatusNotificacaoEnum.PENDENTE);

        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefasDTORecord(tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));


    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }

    public void deletaTarefaPorID(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }

    }

    public TarefasDTORecord alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefasUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}
