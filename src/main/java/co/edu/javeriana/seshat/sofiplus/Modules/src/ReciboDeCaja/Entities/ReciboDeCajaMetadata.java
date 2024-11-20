package co.edu.javeriana.seshat.sofiplus.Modules.src.ReciboDeCaja.Entities;

import org.springframework.data.annotation.Id;

public class ReciboDeCajaMetadata {//viene lo publico
    @Id
    public String id;
    public double suma;
    public String sumaEnLetras;
    public String descripcion;
    public double debito;
    public double credito;

    public ReciboDeCajaMetadata() {
    }

    public ReciboDeCajaMetadata(String id, double suma, String sumaEnLetras, String descripcion, double debito, double credito) {
        this.id = id;
        this.suma = suma;
        this.sumaEnLetras = sumaEnLetras;
        this.descripcion = descripcion;
        this.debito = debito;
        this.credito = credito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getSumaEnLetras() {
        return sumaEnLetras;
    }

    public void setSumaEnLetras(String sumaEnLetras) {
        this.sumaEnLetras = sumaEnLetras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
}
