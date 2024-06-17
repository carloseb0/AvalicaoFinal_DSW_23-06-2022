package br.upf.cineminhafinal.rest;
import java.util.Set;
import javax.ws.rs.core.Application;


/**
 *
 * @author carlos
 */
//Define o caminho base para montagem da URL ".../webresources/.."
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.upf.cineminhafinal.rest.FilmeService.class);
        resources.add(br.upf.cineminhafinal.rest.ProdutoraService.class);
        resources.add(br.upf.cineminhafinal.rest.UsuarioService.class);
    } 
    
}
