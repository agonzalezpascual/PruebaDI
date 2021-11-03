package modelo;

public class Cuenta {

    private int numCuenta;
    private String titular, fechaApertura;
    private double saldo;

    public Cuenta(int numCuenta, String titular, String fechaApertura, double saldo) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
