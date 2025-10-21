package neo.bank.cliente.framework.adapter.input.kafka;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ClienteUseCase;
import neo.bank.cliente.application.ports.input.dto.AssociaCartaCmd;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.NumeroCarta;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@ApplicationScoped
@Slf4j
public class CartaConsumer {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private ClienteUseCase app;

    private static final String EVENT_OWNER = "CARTA_PREPAGATA";
    private static final String CARTA_PREPAGATA_CREATA_EVENT_NAME = "CartaPrepagataCreata";

    @Incoming("carta-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
        var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
        String eventType = new String(metadata.getHeaders().lastHeader("eventType").value(), StandardCharsets.UTF_8);
        String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value(),
                StandardCharsets.UTF_8);
        String payload = msg.getPayload();
        log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
        if (aggregateName.equals(EVENT_OWNER)) {
            JsonNode json = convertToJsonNode(payload);
            switch (eventType) {
                case CARTA_PREPAGATA_CREATA_EVENT_NAME:{
                    String iban = json.get("iban").asText();
                    String usernameCliente = json.get("usernameCliente").asText();
                    String numeroCarta = json.get("numeroCarta").asText();
                    app.associaCarta(new AssociaCartaCmd(new NumeroCarta(numeroCarta), new Iban(iban), new UsernameCliente(usernameCliente)));
                    break;
                }
                default:
                    log.warn("Evento [{}] non gestito...", eventType);
                    break;
            }
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
