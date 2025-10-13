package neo.bank.cliente.framework.adapter.output.mongodb.impl;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ports.output.UsernameProjectionRepositoryPort;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.framework.adapter.output.mongodb.entities.UsernameProjectionEntity;

@ApplicationScoped
@Slf4j
public class UsernameProjectionRepositoryImpl implements PanacheMongoRepositoryBase<UsernameProjectionEntity, String>, UsernameProjectionRepositoryPort {
    
    
    @Override
    public void salva(UsernameCliente usernameCliente, IdCliente idCliente) {
        log.info("Aggiorno la projection...");
        persist(new UsernameProjectionEntity(usernameCliente.username(), idCliente.id()));
    }

    @Override
    public IdCliente recuperaDaUsername(UsernameCliente username) {
        UsernameProjectionEntity entity = findById(username.username());
        return entity == null ? null : new IdCliente(entity.getIdCliente());
    }


}
