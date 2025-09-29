package neo.bank.cliente.framework.adapter.output.kafka.integration_events;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Value;

@Value
public class IEClienteCreato implements Serializable {
    private String idCliente;
    private String usernameCliente;
    private String nomeCliente;
    private String cognomeCliente;
    private LocalDate dataNascita;
    private String codiceFiscale;
    private String email;
    private String telefono;
    private String residenza;
}
