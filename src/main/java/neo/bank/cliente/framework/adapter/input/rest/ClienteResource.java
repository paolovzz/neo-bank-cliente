package neo.bank.cliente.framework.adapter.input.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.cliente.application.ClienteUseCase;
import neo.bank.cliente.application.ports.input.dto.AggiornaEmailCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaResidenzaCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaTelefonoCmd;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.framework.adapter.input.rest.request.RichiediAggiornamentoEmailRequest;
import neo.bank.cliente.framework.adapter.input.rest.request.RichiediAggiornamentoResidenzaRequest;
import neo.bank.cliente.framework.adapter.input.rest.request.RichiediAggiornamentoTelefonoRequest;
import neo.bank.cliente.framework.adapter.input.rest.response.ClienteInfoResponse;

@Path("/clienti")
@ApplicationScoped
public class ClienteResource {

    @Inject
    private ClienteUseCase app;

    @Context
    private HttpHeaders headers;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaClienteDaUsername() {
        String username = recuperaUtenteAutenticato();
        Cliente cliente = app.recuperaClienteDaUsername(new UsernameCliente(username));
        ClienteInfoResponse bodyResponse = new ClienteInfoResponse(cliente);
        return Response.ok(bodyResponse).build();
    }

    @Path("/iban")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaIbanDaUsername() {
        String username = recuperaUtenteAutenticato();
        List<Iban> listaIban = app.recuperaCodiciIbanDelCliente(new UsernameCliente(username));
        return Response.ok(listaIban.stream().map(iban-> iban.codice())).build();
    }

    @Path("/residenza")
    @PUT // Sarebbe piu' corretto PATCH ma quarkus non lo supporta
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response aggiornaResidenza( RichiediAggiornamentoResidenzaRequest req) {
        String username = recuperaUtenteAutenticato();
        app.aggiornaResidenza(new AggiornaResidenzaCmd(new UsernameCliente(username), new Residenza(req.getResidenza())));
        return Response.noContent().build();
    }

    @Path("/telefono")
    @PUT // Sarebbe piu' corretto PATCH ma quarkus non lo supporta
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response aggiornaTelefono(RichiediAggiornamentoTelefonoRequest req) {
        String username = recuperaUtenteAutenticato();
        app.aggiornaTelefono(new AggiornaTelefonoCmd(new UsernameCliente(username), new Telefono(req.getTelefono())));
        return Response.noContent().build();
    }

    @Path("/email")
    @PUT // Sarebbe piu' corretto PATCH ma quarkus non lo supporta
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response aggiornaEmail( RichiediAggiornamentoEmailRequest req) {
        String username = recuperaUtenteAutenticato();
        app.aggiornaEmail(new AggiornaEmailCmd(new UsernameCliente(username), new Email(req.getEmail())));
        return Response.noContent().build();
    }

    private String recuperaUtenteAutenticato() {
        String username = headers.getHeaderString("X-Authenticated-User");
        if (username == null)
            throw new NotAuthorizedException("Richiesta non autenticata");
        return username;
    }
    
}
