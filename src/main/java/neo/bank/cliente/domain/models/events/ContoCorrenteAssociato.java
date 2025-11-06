package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Iban;

@Value
public class ContoCorrenteAssociato implements EventPayload {

    private Iban iban;
    @Override
    public String eventType() {
        return "ContoCorrenteAssociato";
    }
}
