package org.xstore.gestion.tablas;

import javax.persistence.*;

@Entity
public class Carrito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Cliente", nullable = false)
    private int cliente;
    @Basic
    @Column(name = "Total", nullable = false)
    private int total;

    public Carrito(int id, int cliente, int total) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
    }

    public Carrito() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
