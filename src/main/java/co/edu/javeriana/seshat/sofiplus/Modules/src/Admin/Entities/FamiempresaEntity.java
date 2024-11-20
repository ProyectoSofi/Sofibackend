package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities;

import co.edu.javeriana.seshat.sofiplus.DataFacade.FrontEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Famiempresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamiempresaEntity implements FrontEntity<Famiempresa> {
    @Id
    private String nit;
    private String razonSocial;

    public FamiempresaEntity(Famiempresa famiempresa) {
        this.nit = famiempresa.getNit();
        this.razonSocial = famiempresa.getRazonSocial();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FamiempresaEntity that = (FamiempresaEntity) o;
        return nit != null && Objects.equals(nit, that.nit);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Famiempresa getFrontEntity() {
        return new Famiempresa(this.nit, this.razonSocial);
    }
}
