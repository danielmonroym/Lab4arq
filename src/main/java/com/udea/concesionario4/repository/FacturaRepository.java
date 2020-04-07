package com.udea.concesionario4.repository;



import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.udea.concesionario4.com.udea.concesionario4.modelo.Factura;





public class FacturaRepository extends AbstractRepository<Factura, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaRepository() {
        super(Factura.class);
    }
    
}
