package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.CartaAssociata;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IECartaAssociata;

@ApplicationScoped
public class CartaAssociataConverter implements IntegrationEventConverter<CartaAssociata, IECartaAssociata>, IntegrationEventConverterMarker {

    @Override
    public IECartaAssociata convert(CartaAssociata domainEvent) {
        return new IECartaAssociata(domainEvent.getNumeroCarta().getNumero());
    }

    @Override
    public Class<CartaAssociata> supportedDomainEvent() {
        return CartaAssociata.class;
    }

}
