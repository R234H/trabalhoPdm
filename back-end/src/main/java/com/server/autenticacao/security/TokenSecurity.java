package com.server.autenticacao.security;

import com.server.autenticacao.model.User;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

public class TokenSecurity {

    private static RsaJsonWebKey rsaJsonWebKey = null;
    public static final Long timeToExpire = 30L;

    // 	Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
    static {
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

    public static String generateJwtToken(User user) throws JoseException {
        rsaJsonWebKey.setKeyId("KeyId");

        JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(timeToExpire);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(2);
        claims.setClaim("id", user.getId());
        claims.setClaim("email", user.getEmail());


        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        return jws.getCompactSerialization();
    }

    public static String validateJwtToken(String bearer) throws Exception {


        String[] token = bearer.split(" ");
        if (!token[0].equals("Bearer"))
            throw new Exception("Token malformated");
        String jwt = token[1];

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setMaxFutureValidityInMinutes(300)
                .setAllowedClockSkewInSeconds(30)
                .setVerificationKey(rsaJsonWebKey.getKey())
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        return jwtClaims.getClaimsMap().get("id").toString();
    }
}