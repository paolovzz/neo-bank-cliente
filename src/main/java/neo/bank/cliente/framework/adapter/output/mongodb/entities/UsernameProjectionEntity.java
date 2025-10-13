package neo.bank.cliente.framework.adapter.output.mongodb.entities;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MongoEntity(collection="username-projection")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UsernameProjectionEntity extends PanacheMongoEntityBase {

    @BsonId
    private String username;
    private String idCliente;

    public UsernameProjectionEntity(String username, String idCliente) {
        this.username = username;
        this.idCliente = idCliente;
    }
    
}