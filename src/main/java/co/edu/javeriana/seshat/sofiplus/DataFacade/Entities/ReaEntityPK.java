package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReaEntityPK implements Serializable {
    @Id
    private String idEvento;
    @Id
    private String nitFamiempresa;
    @Id
    private String idRecurso;

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNitFamiempresa() {
        return nitFamiempresa;
    }

    public void setNitFamiempresa(String nitFamiempresa) {
        this.nitFamiempresa = nitFamiempresa;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaEntityPK that = (ReaEntityPK) o;
        return idRecurso == that.idRecurso && Objects.equals(idEvento, that.idEvento) && Objects.equals(nitFamiempresa, that.nitFamiempresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, nitFamiempresa, idRecurso);
    }
}
