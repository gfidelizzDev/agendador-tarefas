package com.gfidelizzdev.agendadortarefas.infraestructure.security;

import com.gfidelizzdev.agendadortarefas.business.dto.UsuarioDTO;
import com.gfidelizzdev.agendadortarefas.infraestructure.security.Client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
private UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email, String token){

        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();

    }
}
