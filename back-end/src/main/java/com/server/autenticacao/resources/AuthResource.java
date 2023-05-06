package com.server.autenticacao.resources;

import com.server.autenticacao.dtos.LoginDTO;
import com.server.autenticacao.dtos.RegisterUserDTO;
import com.server.autenticacao.dtos.TokenDTO;
import com.server.autenticacao.model.User;
import com.server.autenticacao.security.TokenSecurity;
import com.server.autenticacao.services.UserService;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/")
@RequestScoped
public class AuthResource {

    private static final UserService service = new UserService();

    @Context
    ResourceInfo headers;

    @Context
    HttpServletRequest servletRequest;

    @Context
    HttpServletResponse servletResponse;

    @POST
    @PermitAll
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        try {
            User user = service.autentica(loginDTO);
            return Response.status(Response.Status.OK).entity(new TokenDTO(
                    TokenSecurity.generateJwtToken(user),
                    TokenSecurity.timeToExpire,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(TokenSecurity.timeToExpire))).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("login failed").build();
        }
    }

    @POST
    @PermitAll
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterUserDTO registerUserDTO) {
        try {
            User user = service.registra(registerUserDTO);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("register failed").build();
        }
    }

    @POST
    @PermitAll
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public String logout(User user) {
        return servletRequest.getHeader("Authorization");
    }

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(@Context HttpHeaders headers) {
        try {
            User loggedUser = service.buscaUsuarioLogado(headers);
            return Response.ok(loggedUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Not authenticated").build();
        }
    }
}