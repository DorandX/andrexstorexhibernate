package org.xstore.gestion.tablas.auxiliares;

import javax.persistence.*;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdCliente", nullable = false)
    private int idCliente;
    @Basic
    @Column(name = "NombreDeCliente", nullable = false, length = 50)
    private String nombreDeCliente;
    @Basic
    @Column(name = "DireccionDeEnvio", nullable = false, length = 260)
    private String direccionDeEnvio;
    @Basic
    @Column(name = "NumeroDeCuenta", nullable = false)
    private int numeroDeCuenta;
    @Basic
    @Column(name = "EmailDeCliente", nullable = true, length = 50)
    private String emailDeCliente;
    @Basic
    @Column(name = "TelefonoDeCliente", nullable = true)
    private Integer telefonoDeCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreDeCliente() {
        return nombreDeCliente;
    }

    public void setNombreDeCliente(String nombreDeCliente) {
        this.nombreDeCliente = nombreDeCliente;
    }

    public String getDireccionDeEnvio() {
        return direccionDeEnvio;
    }

    public void setDireccionDeEnvio(String direccionDeEnvio) {
        this.direccionDeEnvio = direccionDeEnvio;
    }

    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getEmailDeCliente() {
        return emailDeCliente;
    }

    public void setEmailDeCliente(String emailDeCliente) {
        this.emailDeCliente = emailDeCliente;
    }

    public Integer getTelefonoDeCliente() {
        return telefonoDeCliente;
    }

    public void setTelefonoDeCliente(Integer telefonoDeCliente) {
        this.telefonoDeCliente = telefonoDeCliente;
    }
}
