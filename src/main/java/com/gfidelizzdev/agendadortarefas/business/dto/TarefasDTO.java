package com.gfidelizzdev.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gfidelizzdev.agendadortarefas.infraestructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {
    private String id;
    private String nometarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
