package com.lizana.msclient.service;


import com.lizana.msclient.api.ClientApiDelegate;
import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements ClientApiDelegate {

    @Autowired
    ClientBusiness clientBusiness;

    @Override
    public Mono<ResponseEntity<ClientObject>> addClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {
        return clientBusiness.saveClient(clientObject,exchange);
    }

    @Override
    public Mono<ResponseEntity<Status>> deleteClient(ServerWebExchange exchange) {
        return ClientApiDelegate.super.deleteClient(exchange);
    }

    @Override
    public Mono<ResponseEntity<ClientObject>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {
        return ClientApiDelegate.super.getClient(tipoDeDocumento, numeroDeDocumento, exchange);
    }

    @Override
    public Mono<ResponseEntity<ClientObject>> updateClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {
        return ClientApiDelegate.super.updateClient(clientObject, exchange);
    }
}
