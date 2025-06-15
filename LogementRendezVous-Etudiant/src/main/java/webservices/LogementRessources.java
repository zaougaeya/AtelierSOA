package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.core.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Logement", description = "API pour gérer les logements")
public class LogementRessources {

    private static LogementBusiness business = new LogementBusiness();

    @GET
    @Operation(summary = "Récupérer tous les logements")
    public List<Logement> getAll() {
        return business.getLogements();
    }

    @GET
    @Path("/{ref}")
    @Operation(summary = "Récupérer un logement par référence")
    public Response getByReference(@PathParam("ref") int reference) {
        Logement logement = business.getLogementsByReference(reference);
        if (logement != null) {
            return Response.ok(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement non trouvé pour la référence : " + reference)
                    .build();
        }
    }

    @GET
    @Path("/delegation/{deleg}")
    @Operation(summary = "Récupérer les logements par délégation")
    public List<Logement> getByDelegation(@PathParam("deleg") String delegation) {
        return business.getLogementsByDeleguation(delegation);
    }

    @POST
    @Operation(summary = "Ajouter un nouveau logement")
    public Response addLogement(Logement logement) {
        business.addLogement(logement);
        return Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"Logement ajouté\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @PUT
    @Path("/{ref}")
    @Operation(summary = "Mettre à jour un logement existant")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("ref") int ref, Logement logement) {
        boolean updated = business.updateLogement(ref, logement);
        if (updated) {
            MessageResponse response = new MessageResponse("Logement modifié avec succès");
            return Response.ok(response).build();
        } else {
            MessageResponse response = new MessageResponse("Aucun logement avec référence : " + ref);
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }

    @DELETE
    @Path("/{ref}")
    @Operation(summary = "Supprimer un logement")
    public Response deleteLogement(@PathParam("ref") int ref) {
        boolean deleted = business.deleteLogement(ref);
        if (deleted) {
            return Response.ok("Logement supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun logement avec référence : " + ref).build();
        }
    }
}
