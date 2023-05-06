package com.server.autenticacao.services;

import com.server.autenticacao.dtos.LoginDTO;
import com.server.autenticacao.dtos.RegisterUserDTO;
import com.server.autenticacao.model.User;
import com.server.autenticacao.repository.user.UserRepository;
import com.server.autenticacao.repository.user.UserRepositoryImpl;
import com.server.autenticacao.security.TokenSecurity;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

public class UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    public User autentica(LoginDTO loginDTO) throws Exception {
        return userRepository.buscarUsuario(loginDTO.getEmail());
    }

    public User registra(RegisterUserDTO registerUserDTO) throws Exception {
        return userRepository.registrarUsuario(registerUserDTO);
    }


    public User buscaUsuarioLogado(HttpHeaders headers) throws Exception {
        Long id = Long.parseLong(headers.getRequestHeader("id").get(0));
        return userRepository.buscarUsuarioPorId(id);
    }
}
