package com.udea.concesionario4.repository;



import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.udea.concesionario4.com.udea.concesionario4.modelo.Vehiculo;





public class VehiculoRepository extends AbstractRepository<Vehiculo, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoRepository() {
        super(Vehiculo.class);
    }
    
}
