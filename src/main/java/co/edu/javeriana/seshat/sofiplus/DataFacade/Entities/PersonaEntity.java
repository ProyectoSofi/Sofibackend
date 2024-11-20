package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonaEntity {
    @Id
    private String idPersona;
    private String nombre;
    private String telefono;

    public PersonaEntity() {
    }

    public PersonaEntity(String idPersona, String nombre, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
