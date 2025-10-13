package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.Iban;

public record ContoCorrenteAssociato(Iban iban) implements EventPayload {

    @Override
    public String eventType() {
        return "ContoCorrenteAssociato";
    }
}
