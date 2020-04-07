package com.udea.concesionario4.repository;



import com.udea.concesionario4.com.udea.concesionario4.modelo.Authority;
import javax.inject.Inject;
import javax.persistence.EntityManager;



public class AuthorityRepository extends AbstractRepository<Authority, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorityRepository() {
        super(Authority.class);
    }
}
