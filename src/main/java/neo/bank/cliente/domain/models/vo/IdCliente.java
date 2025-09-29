package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record IdCliente(String id) {
    public IdCliente {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                    IdCliente.class.getSimpleName(),
                    CodiceErrore.ID_NON_VALIDO.getCodice());
        }
    }
}
