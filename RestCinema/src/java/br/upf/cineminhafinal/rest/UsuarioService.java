package br.upf.cineminhafinal.rest;

import br.upf.cineminhafinal.entity.Usuario;
import br.upf.cineminhafinal.facade.AbstractFacade;
import br.upf.cineminhafinal.jwt.TokenJwt;
import br.upf.cineminhafinal.utils.Utils;
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
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("usuario")
public class UsuarioService extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "RestCinemaFinalPU")
    private EntityManager em;

    @EJB
    private br.upf.cineminhafinal.facade.UsuarioFacade ejbUsuarioFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioService() {
        super(Usuario.class);
    }

    @GET
    @Path("/listAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Usuario> findAll(@HeaderParam("token") String token) {
        TokenJwt.validarToken(token);
        return super.findAll();
    }

    @POST
    @Path("/addValidate")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createValidate(Usuario entity, @HeaderParam("token") String token) {
        TokenJwt.validarToken(token); 
        if (entity.getNome() == null || entity.getSenha() == null
                || entity.getUsuario() == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else if (entity.getNome().isEmpty() || entity.getSenha().isEmpty()
                || entity.getUsuario().isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("ATENÇÃO! Campo obrigatório não enviado.").build());
        } else {
            super.create(entity);
            return Response.ok("Registro inserido com sucesso!").build();
        }
    }

    @PUT
    @Path("/edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void editUsuario(Usuario entity, @HeaderParam("token") String token) {
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
    @Path("/authorize")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario authorize(@HeaderParam("user") String user,
            @HeaderParam("password") String password) {
        Usuario entity;        
        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            entity = ejbUsuarioFacade.findByUsuario(user);
            if (entity.getIdUsuario()!= null) {   
                if (entity.getSenha().equals(password)) {
                    entity.setToken(Utils.processarTokenJWT(user));
                    return entity;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    


}
