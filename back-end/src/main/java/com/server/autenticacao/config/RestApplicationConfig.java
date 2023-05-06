package com.server.autenticacao.config;

import com.server.autenticacao.filters.AuthenticationFilter;
import com.server.autenticacao.filters.CorsFilter;
import com.server.autenticacao.filters.ResponseFormatFilter;
import org.glassfish.jersey.server.ResourceConfig;

//@ApplicationPath("api")
public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages("com.server.autenticacao.resources");
        register(AuthenticationFilter.class);
        register(CorsFilter.class);
        register(ResponseFormatFilter.class);
    }
}
