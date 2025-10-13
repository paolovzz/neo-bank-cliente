package neo.bank.cliente.application.ports.output;

import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

public interface UsernameProjectionRepositoryPort {
    
    public void salva(UsernameCliente username, IdCliente idCliente);
    public IdCliente recuperaDaUsername (UsernameCliente username);
}
