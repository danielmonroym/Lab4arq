package com.udea.concesionario4.com.udea.concesionario4.modelo;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
/**
 * @author Usuario
 */


@Entity
@IdClass(UsuarioPK.class)

@NamedQuery(name="findAll",query="Select e from Usuario e")
public class Usuario { 



    @Id
    @Column(name="id",  unique=true,  nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private Long id;


    @Basic
    @Column(name="nombre",    nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private String nombre;


    @Basic
    @Column(name="email",    nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private String email;


    @Basic
    @Column(name="pass",    nullable=false)
    @NotNull(message="Se debe llenar este campo")
    private String pass;


    @Id@ManyToOne
    private Factura factura;



    public Usuario(){
}

    public Usuario(Long id,String nombre,String email,String pass){
this.id=id;
this.nombre=nombre;
this.email=email;
this.pass=pass;
}




    public Long getId()  {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre()  {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEmail()  {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPass()  {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        final Usuario other = (Usuario) obj;
        if (!java.util.Objects.equals(this.getId(), other.getId())) {        return false;        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
        return hash;
    }




}