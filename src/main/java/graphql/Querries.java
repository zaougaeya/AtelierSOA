package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.Logement;
import entite.RendezVous;
import repository.LogementRepository;
import repository.RendezVousRepository;

import java.util.List;

public class Querries implements GraphQLRootResolver {

    private final RendezVousRepository rdvRepo;
    private final LogementRepository logementRepo;

    public Querries(RendezVousRepository rdvRepo, LogementRepository logementRepo) {
        this.rdvRepo = rdvRepo;
        this.logementRepo = logementRepo;
    }

    // 🟢 Rendez-vous
    public List<RendezVous> allRendezVous() {
        return rdvRepo.getListeRendezVous();
    }

    // 🟢 Logement
    public List<Logement> allLogements() {
        return logementRepo.getAllLogements();
    }

    public Logement logementByReference(int reference) {
        return logementRepo.getLogementsByReference(reference);
    }

    public List<Logement> logementsByType(Logement.TypeL typeL) {
        return logementRepo.getLogementsByType(typeL);
    }
}
