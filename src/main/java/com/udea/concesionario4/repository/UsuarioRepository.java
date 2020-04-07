package com.udea.concesionario4.repository;



import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.udea.concesionario4.com.udea.concesionario4.modelo.Usuario;





public class UsuarioRepository extends AbstractRepository<Usuario, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRepository() {
        super(Usuario.class);
    }
    
}
