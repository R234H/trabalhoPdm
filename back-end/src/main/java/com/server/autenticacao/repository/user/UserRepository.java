package com.server.autenticacao.repository.user;

import com.server.autenticacao.dtos.RegisterUserDTO;
import com.server.autenticacao.model.User;

public interface UserRepository {

    User registrarUsuario(RegisterUserDTO registerUserDTO) throws Exception;

    User buscarUsuario(String username) throws Exception;

    User buscarUsuarioPorId(Long id) throws Exception;
}
