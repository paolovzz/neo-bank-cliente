package neo.bank.cliente.application.exceptions;

import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

public class ClienteNonTrovatoException extends RuntimeException {
    
    public ClienteNonTrovatoException(IdCliente idCliente) {
        super(String.format("Cliente con id [%s] non trovato...", idCliente));
    }

    public ClienteNonTrovatoException(UsernameCliente usernameCliente) {
        super(String.format("Cliente con username [%s] non trovato...", usernameCliente.getUsername()));
    }
}
