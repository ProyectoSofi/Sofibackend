package co.edu.javeriana.seshat.sofiplus.Modules.src.ReciboDeCaja.Entities;


public class ReciboDeCaja {//aca viene lo privado

    private String id;
    private String fecha;
    private double suma;
    private String sumaLetras;
    private String descripcion;
    private String clienteID;
    private String facturaID;
    private double debito;
    private double credito;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getSumaLetras() {
        return sumaLetras;
    }

    public void setSumaLetras(String sumaLetras) {
        this.sumaLetras = sumaLetras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(String facturaID) {
        this.facturaID = facturaID;
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

    public ReciboDeCaja() {
    }

    public ReciboDeCaja(String id, String fecha, double suma, String sumaLetras, String descripcion, String clienteID, String facturaID, double debito, double credito) {
        this.id = id;
        this.fecha = fecha;
        this.suma = suma;
        this.sumaLetras = sumaLetras;
        this.descripcion = descripcion;
        this.clienteID = clienteID;
        this.facturaID = facturaID;
        this.debito = debito;
        this.credito = credito;
    }
}
