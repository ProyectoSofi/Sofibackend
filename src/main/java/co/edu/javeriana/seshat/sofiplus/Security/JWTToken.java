package co.edu.javeriana.seshat.sofiplus.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTToken {
    String id;
    String nombre;
    String email;
    String famiempresaID;
    String rol;
}
