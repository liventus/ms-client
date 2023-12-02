package com.lizana.msclient.service;



import com.lizana.msclient.api.ClientsApiDelegate;
import com.lizana.msclient.model.ClientObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class ClientsService implements ClientsApiDelegate {



    @Override
    public Mono<ResponseEntity<ClientObject>> getClientAll(ServerWebExchange exchange) {
        return ClientsApiDelegate.super.getClientAll(exchange);
    }
}
