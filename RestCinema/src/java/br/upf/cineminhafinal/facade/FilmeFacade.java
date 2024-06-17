/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.cineminhafinal.facade;

import br.upf.cineminhafinal.entity.Filme;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
@Stateless
public class FilmeFacade extends AbstractFacade<Filme> {

    @PersistenceContext(unitName = "RestCinemaFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmeFacade() {
        super(Filme.class);
    }
    
    public List<Filme> findByPartTitulo(String partTitulo) {
        List<Filme> lista = new ArrayList<>();
        try {
            Query query = getEntityManager().createNamedQuery("Filme.findByPartTitulo");
            query.setParameter(1, partTitulo);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return lista;
    }
    
}
