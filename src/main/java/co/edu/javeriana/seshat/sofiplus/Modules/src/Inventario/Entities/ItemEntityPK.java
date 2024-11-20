package co.edu.javeriana.seshat.sofiplus.Modules.src.Inventario.Entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ItemEntityPK implements Serializable {
    @Id
    private String nitFamiempresa;
    @Id
    private String codigo;

    public String getNitFamiempresa() {
        return nitFamiempresa;
    }

    public void setNitFamiempresa(String nitFamiempresa) {
        this.nitFamiempresa = nitFamiempresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntityPK that = (ItemEntityPK) o;
        return Objects.equals(nitFamiempresa, that.nitFamiempresa) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nitFamiempresa, codigo);
    }
}
