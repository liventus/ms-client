package com.lizana.msclient.service;


import com.lizana.msclient.api.ClientApiDelegate;

import com.lizana.msclient.model.ClientRequest;
import com.lizana.msclient.model.ClientResponse;
import com.lizana.msclient.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class ClientService implements ClientApiDelegate  {
    @Override
    public Mono<ResponseEntity<Status>> deleteClient(ServerWebExchange exchange) {
        return ClientApiDelegate.super.deleteClient(exchange);
    }

    @Override
    public Mono<ResponseEntity<ClientResponse>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {
        return ClientApiDelegate.super.getClient(tipoDeDocumento, numeroDeDocumento, exchange);
    }

    @Override
    public Mono<ResponseEntity<ClientResponse>> addClient(Mono<ClientRequest> clientRequest, ServerWebExchange exchange) {
        return ClientApiDelegate.super.addClient(clientRequest, exchange);
    }

    @Override
    public Mono<ResponseEntity<ClientResponse>> updateClient(Mono<ClientRequest> clientRequest, ServerWebExchange exchange) {
        return ClientApiDelegate.super.updateClient(clientRequest, exchange);
    }

}
