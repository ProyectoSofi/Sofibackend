package co.edu.javeriana.seshat.sofiplus.Security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    String token;
    String nombre;
    String id;
    String rol;
}
