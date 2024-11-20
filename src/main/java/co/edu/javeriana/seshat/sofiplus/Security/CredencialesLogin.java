package co.edu.javeriana.seshat.sofiplus.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredencialesLogin {
    String email;
    String password;
}
