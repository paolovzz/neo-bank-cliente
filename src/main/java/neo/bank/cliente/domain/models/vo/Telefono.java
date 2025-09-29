package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record Telefono(String numero){

    public Telefono(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new ValidazioneException(Telefono.class.getSimpleName(), CodiceErrore.TELEFONO_NON_VALIDO.getCodice());
        }
        this.numero = numero;
    }
}
