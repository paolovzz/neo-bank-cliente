package neo.bank.cliente.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.cliente.application.exceptions.ClienteNonTrovatoException;
import neo.bank.cliente.framework.adapter.input.rest.response.ErrorResponse;

@Provider
public class ClienteNonTrovatoExceptionMapper implements ExceptionMapper<ClienteNonTrovatoException> {

    @Override
    public Response toResponse(ClienteNonTrovatoException exception) {
        ErrorResponse errorResponse = new ErrorResponse("[CLIENTE NOT FOUND]", exception.getMessage());
        return Response.status(404)
                       .entity(errorResponse)
                       .build();
    }
}
