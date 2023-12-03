package com.lizana.msclient.service;



import com.lizana.msclient.api.ClientsApiDelegate;
import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ClientsService implements ClientsApiDelegate {

    @Autowired
    ClientBusiness clientBusiness;


    @Override
    public Mono<ResponseEntity<Flux<ClientObject>>> getClientAll(ServerWebExchange exchange) {
        return clientBusiness.getClientAll(exchange);
    }
}
