package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record NumeroCarta(String numero) {
    public NumeroCarta {
        if (numero == null) {
            throw new ValidazioneException(
                NumeroCarta.class.getSimpleName(),
                CodiceErrore.NUMERO_CARTA_NON_PUO_ESSERE_NULL.getCodice()
            );
        }
    }
}
