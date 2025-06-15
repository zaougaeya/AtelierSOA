package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "RendezVous", description = "API pour gérer les rendez-vous")
public class RendezvousRessources {

    private static RendezVousBusiness business = new RendezVousBusiness();

    @GET
    @Operation(summary = "Récupérer tous les rendez-vous")
    public List<RendezVous> getAll() {
        return business.getListeRendezVous();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Récupérer un rendez-vous par ID")
    public Response getById(@PathParam("id") int id) {
        RendezVous rdv = business.getRendezVousById(id);
        if (rdv != null) {
            return Response.ok(rdv).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé pour l'ID : " + id)
                    .build();
        }
    }

    @GET
    @Path("/logement/{ref}")
    @Operation(summary = "Récupérer les rendez-vous par référence logement")
    public List<RendezVous> getByLogementReference(@PathParam("ref") int refLogement) {
        return business.getListeRendezVousByLogementReference(refLogement);
    }

    @POST
    @Operation(summary = "Ajouter un nouveau rendez-vous")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        business.addRendezVous(rendezVous);
        // Retourne un vrai JSON
        String jsonMessage = "{\"message\":\"Rendez-vous ajouté avec succès\"}";
        return Response.status(Response.Status.CREATED)
                .entity(jsonMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Mettre à jour un rendez-vous")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean updated = business.updateRendezVous(id, rendezVous);
        if (updated) {
            String jsonMessage = "{\"message\":\"Rendez-vous mis à jour avec succès\"}";
            return Response.ok(jsonMessage, MediaType.APPLICATION_JSON).build();
        } else {
            String jsonMessage = "{\"message\":\"Échec de la mise à jour\"}";
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Operation(summary = "Supprimer un rendez-vous")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = business.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun rendez-vous avec ID : " + id).build();
        }
    }
}
