package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    String id;
    String nombre;
    String email;
    String password;
    String famiempresaID;
    String rol;
}
