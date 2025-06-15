package webservices;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(OpenApiResource.class);          // ressource Swagger pour la doc
        classes.add(LogementRessources.class);       // ta ressource REST logement
        classes.add(RendezvousRessources.class);     // ta ressource REST rendez-vous
        return classes;
    }
}
