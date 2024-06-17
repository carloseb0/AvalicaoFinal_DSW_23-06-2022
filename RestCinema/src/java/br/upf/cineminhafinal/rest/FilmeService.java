package br.upf.cineminhafinal.rest;

import br.upf.cineminhafinal.entity.Filme;
import br.upf.cineminhafinal.facade.AbstractFacade;
import br.upf.cineminhafinal.jwt.TokenJwt;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Carlos
 */
@Stateless
@Path("filme")
public class FilmeService extends AbstractFacade<Filme> {
    @PersistenceContext(unitName = "RestCinemaFinalPU")
    private EntityManager em;

    @EJB
    private br.upf.cineminhafinal.facade.FilmeFacade ejbFilmeFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmeService() {
        super(Filme.class);
    }

    @Context
    private UriInfo context;

    @GET
    @Path("/listAll") //adicionando caminho na URL
    @Produces(MediaType.APPLICATION_JSON)
    public List<Filme> findAll(@HeaderParam("token") String token) { 
        TokenJwt.validarToken(token);
        return super.findAll();
    
    }
    
    @POST
    @Path("/addValidate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createValidate(Filme entity, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token); 
        if (entity.getTitulo()== null || entity.getDaEstreia()== null || entity.getIdProdutora() == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else if (entity.getTitulo().isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else {
            super.create(entity);
            return Response.ok("Registro inserido com sucesso!").build();
        }
    }
    
    @DELETE
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void remove(@HeaderParam("id") Integer id, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        super.remove(super.find(id));
    }
    
    @PUT
    @Path("/edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void editUsuario(Filme entity, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        super.edit(entity);
    }

    @GET
    @Path("/findByPartTitulo/{partTitulo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Filme> findByPartTitulo(@PathParam("partTitulo") String titulo, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        return ejbFilmeFacade.findByPartTitulo(titulo);
    }
}