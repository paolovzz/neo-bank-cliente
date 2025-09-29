package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record IdContoCorrente(String id) {
    public IdContoCorrente {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                IdContoCorrente.class.getSimpleName(),
                CodiceErrore.ID_NON_VALIDO.getCodice()
            );
        }
    }
}
