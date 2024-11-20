package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoEntityPK implements Serializable {
    @Id
    private String idEvento;
    @Id
    private String nitFamiempresa;
}
