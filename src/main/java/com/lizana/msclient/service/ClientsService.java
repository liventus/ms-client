package com.lizana.msclient.service;


import com.lizana.msclient.api.ClientsApiDelegate;
import com.lizana.msclient.model.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class ClientsService implements ClientsApiDelegate {

    @Override
    public Mono<ResponseEntity<ClientResponse>> getClientAll(ServerWebExchange exchange) {
        return ClientsApiDelegate.super.getClientAll(exchange);
    }
}
