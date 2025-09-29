package neo.bank.cliente.domain.models.aggregates;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.domain.models.events.EventPayload;
import neo.bank.cliente.domain.models.vo.CodiceCliente;
import neo.bank.cliente.domain.models.vo.DatiAnagraficiCliente;
import neo.bank.cliente.domain.models.vo.IamId;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.IdContoCorrente;
import neo.bank.cliente.domain.services.GeneratoreIdClienteService;


@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends AggregateRoot<Cliente> implements Applier  {

    public static final String AGGREGATE_NAME = "CLIENTE";
    private IdCliente idCliente;
    private CodiceCliente codiceCliente;
    private DatiAnagraficiCliente datiAnagrafici;
    private IamId iamId;
    private Map<IdContoCorrente, Boolean> contiAssociati = new HashMap<>();

    public static Cliente crea(GeneratoreIdClienteService generatoreIdCliente, DatiAnagraficiCliente datiAnagrafici) {

        IdCliente idCliente = generatoreIdCliente.genera();
        Cliente cliente = new Cliente();
        cliente.idCliente = idCliente;
        cliente.events(new ClienteCreato(idCliente, datiAnagrafici));
        return cliente;
    }

    private void apply(ClienteCreato event) {
        this.idCliente = event.idCliente();
        this.datiAnagrafici = event.datiAnagrafici();
    }

    @Override
    public void apply(EventPayload event) {
         switch (event) {
            case ClienteCreato ev -> apply((ClienteCreato) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }
}