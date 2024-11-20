package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidadoEntityPK implements Serializable {
    @Id
    private String idRecurso;
    @Id
    private String nitFamiempresa;
}
