package com.server.autenticacao.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TokenDTO {

    private String token;
    private Long timeToExpires;
    private LocalDateTime generated;
    private LocalDateTime expiresIn;

    public TokenDTO(String token, Long timeToExpires, LocalDateTime generated, LocalDateTime expiresIn) {
        this.token = token;
        this.timeToExpires = timeToExpires;
        this.generated = generated;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTimeToExpires() {
        return timeToExpires;
    }

    public void setTimeToExpires(Long timeToExpires) {
        this.timeToExpires = timeToExpires;
    }

    public LocalDateTime getGenerated() {
        return generated;
    }

    public void setGenerated(LocalDateTime generated) {
        this.generated = generated;
    }

    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(LocalDateTime expiresIn) {
        this.expiresIn = expiresIn;
    }
}
