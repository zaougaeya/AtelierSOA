package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.RendezVousRepository;
import repository.LogementRepository;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        RendezVousRepository rdvRepo = new RendezVousRepository();
        LogementRepository logementRepo = new LogementRepository();

        return SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(
                        new Querries(rdvRepo, logementRepo)
                )
                .build()
                .makeExecutableSchema();
    }
}
