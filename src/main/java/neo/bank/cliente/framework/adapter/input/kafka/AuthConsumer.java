package neo.bank.cliente.framework.adapter.input.kafka;

import java.time.LocalDate;

// import java.util.concurrent.CompletionStage;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ClienteUseCase;
import neo.bank.cliente.application.ports.input.dto.CreaClienteCmd;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.DatiAnagraficiCliente;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.framework.adapter.output.kafka.ClienteEventPublisher;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IECreazioneClienteFallita;

@ApplicationScoped
@Slf4j
public class AuthConsumer {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private ClienteUseCase app;

    @Inject
    private ClienteEventPublisher clienteEventPublisher;
    

    private static final String EVENT_OWNER = "NEO_BANK_AUTH";
    private static final String EVENT_UTENTE_REGISTRATO = "UtenteRegistrato";

    @Incoming("auth-notifications")
    public CompletionStage<Void> consume(Message<String> msg) {
            var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
            String eventType = new String(metadata.getHeaders().lastHeader("eventType").value());
            String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value());
            String payload = msg.getPayload();
            log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
            if (aggregateName.equals(EVENT_OWNER)) {
                JsonNode json = convertToJsonNode(payload);
                switch (eventType) {
                    case EVENT_UTENTE_REGISTRATO:{
                        UsernameCliente usernameCliente = new UsernameCliente(json.get("username").asText());
                        try {
                            NomeCliente nomeCliente = new NomeCliente(json.get("nome").asText());
                            CognomeCliente cognomeCliente = new CognomeCliente(json.get("cognome").asText());
                            Email emailCliente = new Email(json.get("email").asText());
                            DataNascita dataNascita = new DataNascita(LocalDate.parse(json.get("dataNascita").asText()));
                            Telefono telefono = new Telefono(json.get("telefono").asText());
                            Residenza residenza = new Residenza(json.get("residenza").asText());
                            CodiceFiscale codiceFiscale = new CodiceFiscale(json.get("codiceFiscale").asText());
                            app.creaCliente(new CreaClienteCmd(new DatiAnagraficiCliente(usernameCliente, nomeCliente, cognomeCliente, dataNascita, codiceFiscale, emailCliente, telefono, residenza)));
                            
                        }catch(RuntimeException ex) {
                            log.error("Errore.", ex);
                            clienteEventPublisher.publishError(Cliente.AGGREGATE_NAME, "CreazioneClienteFallita", new IECreazioneClienteFallita(usernameCliente.username()));
                        }
                        break;
                    }
                    default:
                        log.warn("Evento [{}] non gestito...", eventType);
                        break;
                }
            } else {
                log.warn("Owner non gestito [{}]", aggregateName);
            }
            
        return msg.ack();
    }

    private JsonNode convertToJsonNode(String payload) {
        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Errore durante la conversione json del messaggio kafka", e);
        }
    }
}
