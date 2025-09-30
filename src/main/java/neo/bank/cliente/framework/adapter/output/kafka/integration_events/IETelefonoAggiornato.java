package neo.bank.cliente.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;

import lombok.Value;

@Value
public class IETelefonoAggiornato implements Serializable {
    private String telefono;
}
