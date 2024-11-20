package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.FrontEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String clienteID;
    private String nombre;
    private String telefono;
    private String observaciones;
}
