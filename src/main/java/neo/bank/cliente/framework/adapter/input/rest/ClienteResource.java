package neo.bank.cliente.framework.adapter.input.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ClienteUseCase;
import neo.bank.cliente.application.ports.input.dto.AggiornaEmailCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaResidenzaCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaTelefonoCmd;
import neo.bank.cliente.application.ports.input.dto.RecuperaCodiciIbanDelClienteCmd;
import neo.bank.cliente.application.ports.input.dto.RecuperaDatiClienteCmd;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.NumeroCarta;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.framework.adapter.input.rest.api.ClienteApi;
import neo.bank.cliente.framework.adapter.input.rest.model.ClienteInfoResponse;
import neo.bank.cliente.framework.adapter.input.rest.model.RichiediAggiornamentoEmailRequest;
import neo.bank.cliente.framework.adapter.input.rest.model.RichiediAggiornamentoResidenzaRequest;
import neo.bank.cliente.framework.adapter.input.rest.model.RichiediAggiornamentoTelefonoRequest;

@ApplicationScoped
@Slf4j
public class ClienteResource implements ClienteApi {

    @Inject
    private ClienteUseCase app;

    @Override
    public Response aggiornaEmail(String xAuthenticatedUser, RichiediAggiornamentoEmailRequest request) {
        log.info("Richiesto aggiornamento email cliente: [{}] - {}", xAuthenticatedUser, request);
        app.aggiornaEmail(new AggiornaEmailCmd(new UsernameCliente(xAuthenticatedUser), new Email(request.getEmail())));
        return Response.noContent().build();
    }

    @Override
    public Response aggiornaResidenza(String xAuthenticatedUser, RichiediAggiornamentoResidenzaRequest request) {
        log.info("Richiesto aggiornamento residenza cliente: [{}] - {}", xAuthenticatedUser, request);
        app.aggiornaResidenza(new AggiornaResidenzaCmd(new UsernameCliente(xAuthenticatedUser), new Residenza(request.getResidenza())));
        return Response.noContent().build();
    }

    @Override
    public Response aggiornaTelefono(String xAuthenticatedUser, RichiediAggiornamentoTelefonoRequest request) {
        log.info("Richiesto aggiornamento telefono cliente: [{}] - {}", xAuthenticatedUser, request);
        app.aggiornaTelefono(new AggiornaTelefonoCmd(new UsernameCliente(xAuthenticatedUser), new Telefono(request.getTelefono())));
        return Response.noContent().build();
    }

    @Override
    public Response recuperaClienteDaUsername(String xAuthenticatedUser) {
        log.info("Richiesto recupero dati cliente: [{}]", xAuthenticatedUser);
        Cliente cliente = app.recuperaClienteDaUsername(new RecuperaDatiClienteCmd(new UsernameCliente(xAuthenticatedUser)));
        ClienteInfoResponse bodyResponse = ClienteInfoResponse.builder()
        .carteAssociate(cliente.getCarteAssociate().stream().map(NumeroCarta::getNumero).toList())
        .codiceFiscale(cliente.getCodiceFiscale().getCodice())
        .cognome(cliente.getCognomeCliente().getCognome())
        .contiAssociati(cliente.getContiAssociati().stream().map(Iban::getCodice).toList())
        .dataNascita(cliente.getDataNascita().getData())
        .email(cliente.getEmail().getIndirizzo())
        .nome(cliente.getNomeCliente().getNome())
        .residenza(cliente.getResidenza().getResidenza())
        .telefono(cliente.getTelefono().getNumero())
        .build();
        return Response.ok(bodyResponse).build();
    }

    @Override
    public Response recuperaIbanDaUsername(String xAuthenticatedUser) {
        log.info("Richiesta lista iban cliente: [{}]", xAuthenticatedUser);
        List<Iban> listaIban = app.recuperaCodiciIbanDelCliente(new RecuperaCodiciIbanDelClienteCmd(new UsernameCliente(xAuthenticatedUser)));
        return Response.ok(listaIban.stream().map(iban-> iban.getCodice())).build();
    }

}
