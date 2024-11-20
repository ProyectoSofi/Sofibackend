package co.edu.javeriana.seshat.sofiplus.Kernel;

import co.edu.javeriana.seshat.sofiplus.Security.JWTToken;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Setter
@ToString
public class RequestMessage {
    Object params;
    JWTToken credentials;

    public RequestMessage(Object params) {
        this.params = params;
    }

    public RequestMessage(Object params, JWTToken credentials) {
        this.params = params;
        this.credentials = credentials;
    }

    public Object getParams() {
        return params;
    }

    public Optional<JWTToken> getCredentials() {
        return Optional.ofNullable(credentials);
    }
}
