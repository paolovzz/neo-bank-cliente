package neo.bank.cliente.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IEResidenzaAggiornata implements Serializable {
    private String residenza;
}
