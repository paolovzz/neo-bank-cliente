package neo.bank.cliente.framework.adapter.input.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.cliente.application.ClienteUseCase;
import neo.bank.cliente.application.ports.input.dto.AggiornaResidenzaCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaTelefonoCmd;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.framework.adapter.input.rest.request.RichiediAggiornamentoResidenzaRequest;
import neo.bank.cliente.framework.adapter.input.rest.request.RichiediAggiornamentoTelefonoRequest;
import neo.bank.cliente.framework.adapter.input.rest.response.ClienteInfoResponse;

@Path("/cliente")
@ApplicationScoped
public class ClienteResource {

    @Inject
    private ClienteUseCase app;

    @Path("/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperaClienteDaId(@PathParam(value = "id") String idCliente) {

        Cliente cliente = app.recuperaClienteDaId(new IdCliente(idCliente));
        ClienteInfoResponse bodyResponse = new ClienteInfoResponse(cliente);
        return Response.ok(bodyResponse).build();
    }
    
    @Path("/{id}/residenza")
    @POST // Sarebbe piu' corretto PATCH ma quarkus non lo supporta
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response aggiornaResidenza(@PathParam(value = "id") String idCliente,  RichiediAggiornamentoResidenzaRequest req) {
        app.aggiornaResidenza(new AggiornaResidenzaCmd(new IdCliente(idCliente), new Residenza(req.getResidenza())));
        return Response.noContent().build();
    }

    @Path("/{id}/telefono")
    @POST // Sarebbe piu' corretto PATCH ma quarkus non lo supporta
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response aggiornaTelefono(@PathParam(value = "id") String idCliente,  RichiediAggiornamentoTelefonoRequest req) {
        app.aggiornaTelefono(new AggiornaTelefonoCmd(new IdCliente(idCliente), new Telefono(req.getTelefono())));
        return Response.noContent().build();
    }
    
}
