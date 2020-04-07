package com.udea.concesionario4.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producer for injectable EntityManager
 *
 */
@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "com.udea_Concesionario4_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager(){
        return em;
    }

}