package neo.bank.cliente.application.exceptions;

public class ClienteNonTrovatoException extends RuntimeException {
    
    public ClienteNonTrovatoException(String idCliente) {
        super(String.format("Cliente con id [%s] non trovato...", idCliente));
    }
}
