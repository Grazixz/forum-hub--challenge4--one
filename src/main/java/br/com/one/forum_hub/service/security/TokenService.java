package br.com.one.forum_hub.service.security;

import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.service.exceptions.ValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token")
    private String secret;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("forum-hub")
                    .withClaim("id", user.getId())
                    .withSubject(user.getEmail())
                    .withExpiresAt(time())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new ValidationException("Falha na geração de token.");
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("forum-hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new ValidationException("Token inválido ou expirado!");
        }
    }

    private Instant time() {
        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
