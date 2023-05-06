package com.server.autenticacao.filters;

import com.server.autenticacao.dtos.ResponseDTO;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class ResponseFormatFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        if (request.getMethod().equals("OPTIONS"))
            return;
        ResponseDTO responseDTO = new ResponseDTO(response.getStatus(), response.getEntity());
        response.setEntity(responseDTO);
    }
}
