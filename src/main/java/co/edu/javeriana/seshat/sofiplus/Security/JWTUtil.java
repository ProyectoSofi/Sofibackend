package co.edu.javeriana.seshat.sofiplus.Security;

import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(UsuarioEntity usuario) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("Auth Token")
                .withClaim("id", usuario.getId())
                .withClaim("nombre", usuario.getNombre())
                .withClaim("email", usuario.getEmail())
                .withClaim("famiempresaID", usuario.getFamiempresaID())
                .withClaim("rol", usuario.getRol())
                .withIssuedAt(new Date())
                .withIssuer("SOFI+")
                .sign(Algorithm.HMAC256(secret));
    }

    public JWTToken validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Auth Token")
                .withIssuer("SOFI+")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        JWTToken tokenRet = new JWTToken(jwt.getClaim("id").asString(), jwt.getClaim("nombre").asString(), jwt.getClaim("email").asString(), jwt.getClaim("famiempresaID").asString(), jwt.getClaim("rol").asString());
        return tokenRet;
    }
}
