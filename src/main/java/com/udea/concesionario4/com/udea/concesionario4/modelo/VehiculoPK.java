package com.udea.concesionario4.com.udea.concesionario4.modelo;
import java.io.Serializable;
import java.util.Objects;

public class VehiculoPK implements Serializable {
    private Long id;
    private Long factura;
    public VehiculoPK(){
}
    public VehiculoPK(Long id,Long factura){
this.id=id;
this.factura=factura;
}
   public Long getId() {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }
   public Long getFactura() {
        return factura;
    }

    public void setFactura (Long factura) {
        this.factura = factura;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (!Objects.equals(getClass(), obj.getClass())) {return false;}
        final VehiculoPK other = (VehiculoPK) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {        return false;        }
        if (!java.util.Objects.equals(this.getFactura(), other.getFactura())) {        return false;        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        hash = 31 * hash + Objects.hashCode(this.getFactura());
        return hash;
    }

    @Override
    public String toString() {
        return "VehiculoPK{" + " id=" + id + ", factura=" + factura + '}';
    }

}