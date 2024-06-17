package br.upf.cineminhafinal.rest;

import br.upf.cineminhafinal.entity.Produtora;
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
@Path("produtora")
public class ProdutoraService extends AbstractFacade<Produtora> {

    @PersistenceContext(unitName = "RestCinemaFinalPU")
    private EntityManager em;

    @EJB
    private br.upf.cineminhafinal.facade.ProdutoraFacade ejbProdutoraFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoraService() {
        super(Produtora.class);
    }

    @Context
    private UriInfo context;

    @GET
    @Path("/listAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produtora> findAll(@HeaderParam("token") String token) { 
        TokenJwt.validarToken(token);
        return super.findAll();
    }

    @POST
    @Path("/addValidate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createValidate(Produtora entity, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);    
        if (entity.getNome()== null || entity.getNomefundador()== null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else if (entity.getNome().isEmpty() || entity.getNomefundador().isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else {
            super.create(entity);
            return Response.ok("Registro inserido com sucesso!").build();
        }
    }
    
    @PUT
    @Path("/edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void editUsuario(Produtora entity, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        super.edit(entity);
    }

    @DELETE
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void remove(@HeaderParam("id") Integer id, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        super.remove(super.find(id));
    }
    
    @GET
    @Path("/findByPartTitulo/{partTitulo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Produtora> findByPartTitulo(@PathParam("partTitulo") String titulo, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        return ejbProdutoraFacade.findByPartTitulo(titulo);
    }
}