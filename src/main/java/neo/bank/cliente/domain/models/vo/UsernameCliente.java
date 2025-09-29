package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record UsernameCliente(String username){

    public UsernameCliente(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidazioneException(UsernameCliente.class.getSimpleName(), CodiceErrore.USERNAME_CLIENTE_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.username = username;
    }
}
