package com.udea.concesionario4.com.udea.concesionario4.modelo;

import com.udea.concesionario4.com.udea.concesionario4.modelo.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-08T09:42:29")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, String> marca;
    public static volatile SingularAttribute<Vehiculo, String> precio;
    public static volatile SingularAttribute<Vehiculo, Factura> factura;
    public static volatile SingularAttribute<Vehiculo, String> foto;
    public static volatile SingularAttribute<Vehiculo, Long> id;
    public static volatile SingularAttribute<Vehiculo, String> modelo;

}