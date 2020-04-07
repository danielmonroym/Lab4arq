package com.udea.concesionario4.com.udea.concesionario4.modelo;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/**
 * @author Usuario
 */


@Entity
public class Factura { 



    @Id
    @GeneratedValue
    @Column(name="id",    nullable=false)
    @NotNull
    private Long id;


    @Basic
    @Column(name="total",    nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private String total;



    public Factura(){
}

    public Factura(Long id,String total){
this.id=id;
this.total=total;
}




    public Long getId()  {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTotal()  {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (!Objects.equals(getClass(), obj.getClass())) {return false;}
        final Factura other = (Factura) obj;
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
        return "Factura{" + " total=" + total + '}';
    }



}