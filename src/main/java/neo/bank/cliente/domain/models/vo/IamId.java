package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record IamId(String id){

    public IamId(String id) {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(CodiceCliente.class.getSimpleName(), CodiceErrore.ID_NON_VALIDO.getCodice());
        }
        this.id = id;
    }
}
