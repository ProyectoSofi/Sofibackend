package co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.Entities;

import co.edu.javeriana.seshat.sofiplus.DataFacade.FrontEntity;
import co.edu.javeriana.seshat.sofiplus.Modules.src.Admin.FrontEntities.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioEntity implements FrontEntity<Usuario> {
    @Id
    String id;
    String nombre;
    String email;
    String password;
    String famiempresaID;
    String rol;

    public UsuarioEntity(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.famiempresaID = usuario.getFamiempresaID();
        this.rol = "USUARIO";
    }

    @Override
    public Usuario getFrontEntity() {
        return new Usuario(this.id, this.getNombre(), this.getEmail(), "OCULTO", this.famiempresaID, this.getRol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
