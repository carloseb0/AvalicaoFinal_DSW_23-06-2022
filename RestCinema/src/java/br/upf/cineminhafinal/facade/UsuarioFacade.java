/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.cineminhafinal.facade;

import br.upf.cineminhafinal.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "RestCinemaFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByUsuario(String usuario) {
        Usuario entity = new Usuario();
        try {
            Query query = getEntityManager().createNamedQuery("Usuario.findByUsuario");
            query.setParameter("usuario", usuario);
            query.setMaxResults(1);
            entity = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return entity;
    }
    
    
}
