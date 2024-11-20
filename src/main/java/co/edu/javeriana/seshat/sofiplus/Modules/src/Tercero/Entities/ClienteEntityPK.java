package co.edu.javeriana.seshat.sofiplus.Modules.src.Tercero.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ClienteEntityPK implements Serializable {
    @Id
    private String nitFamiempresa;
    @Column(name = "idPersona")
    private String idPersona;

    public String getNitFamiempresa() {
        return nitFamiempresa;
    }

    public void setNitFamiempresa(String nitFamiempresa) {
        this.nitFamiempresa = nitFamiempresa;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntityPK that = (ClienteEntityPK) o;
        return Objects.equals(nitFamiempresa, that.nitFamiempresa) && Objects.equals(idPersona, that.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nitFamiempresa, idPersona);
    }
}
