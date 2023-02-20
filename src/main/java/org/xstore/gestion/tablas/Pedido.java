package org.xstore.gestion.tablas;

import javax.persistence.*;

@Entity
@Table(name = "Pedido", schema = "XStore", catalog = "")
public class Pedido {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdPedido", nullable = false)
    private int idPedido;
    @Basic
    @Column(name = "Cantidad", nullable = false)
    private int cantidad;
    @Basic
    @Column(name = "Producto", nullable = false)
    private int producto;
    @Basic
    @Column(name = "Carrito", nullable = false)
    private int carrito;

    public Pedido() {
    }

    public Pedido(int idPedido, int cantidad, int producto, int carrito) {
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.producto = producto;
        this.carrito = carrito;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCarrito() {
        return carrito;
    }

    public void setCarrito(int carrito) {
        this.carrito = carrito;
    }
}
