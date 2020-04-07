package com.udea.concesionario4.com.udea.concesionario4.modelo;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
/**
 * @author Usuario
 */


@Entity
@IdClass(VehiculoPK.class)
public class Vehiculo { 



    @Id
    @GeneratedValue
    @Column(name="id",  unique=true,  nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private Long id;


    @Basic
    @Column(name="marca")
    private String marca;


    @Basic
    @Column(name="modelo")
    private String modelo;


    @Basic
    @Column(name="precio")
    private String precio;


    @Id@ManyToOne
    private Factura factura;



    public Vehiculo(){
}

    public Vehiculo(Long id,String marca,String modelo,String precio,Factura factura){
this.id=id;
this.marca=marca;
this.modelo=modelo;
this.precio=precio;
this.factura=factura;
}




    public Long getId()  {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getMarca()  {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }



    public String getModelo()  {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getPrecio()  {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }



    public Factura getFactura()  {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (!Objects.equals(getClass(), obj.getClass())) {return false;}
        final Vehiculo other = (Vehiculo) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {        return false;        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        return hash;
    }


    @Override
    public String toString() {
        return "Vehiculo{" + " id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", factura=" + factura + '}';
    }



}