package org.xstore.gestion.tablas.auxiliares;

import javax.persistence.*;

@Entity
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdProducto", nullable = false)
    private int idProducto;
    @Basic
    @Column(name = "NombreDeProducto", nullable = false, length = 50)
    private String nombreDeProducto;
    @Basic
    @Column(name = "DescripcionDeProducto", nullable = true, length = 260)
    private String descripcionDeProducto;
    @Basic
    @Column(name = "PrecioDeProducto", nullable = false)
    private int precioDeProducto;
    @Basic
    @Column(name = "CategoriaDeProducto", nullable = true)
    private Integer categoriaDeProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreDeProducto() {
        return nombreDeProducto;
    }

    public void setNombreDeProducto(String nombreDeProducto) {
        this.nombreDeProducto = nombreDeProducto;
    }

    public String getDescripcionDeProducto() {
        return descripcionDeProducto;
    }

    public void setDescripcionDeProducto(String descripcionDeProducto) {
        this.descripcionDeProducto = descripcionDeProducto;
    }

    public int getPrecioDeProducto() {
        return precioDeProducto;
    }

    public void setPrecioDeProducto(int precioDeProducto) {
        this.precioDeProducto = precioDeProducto;
    }

    public Integer getCategoriaDeProducto() {
        return categoriaDeProducto;
    }

    public void setCategoriaDeProducto(Integer categoriaDeProducto) {
        this.categoriaDeProducto = categoriaDeProducto;
    }
}
