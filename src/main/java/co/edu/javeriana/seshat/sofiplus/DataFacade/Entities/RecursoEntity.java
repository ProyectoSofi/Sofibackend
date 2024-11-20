package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RecursoEntity {
    @Id
    private String idRecurso;
    private String cuenta;

    public RecursoEntity() {
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecursoEntity)) return false;
        RecursoEntity that = (RecursoEntity) o;
        return getIdRecurso() == that.getIdRecurso() && Objects.equals(getCuenta(), that.getCuenta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRecurso(), getCuenta());
    }
}
