package com.lizana.msclient.business;

import com.lizana.msclient.model.ClientObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public interface ClientBusiness {

    Mono<ResponseEntity<ClientObject>> saveClient(Mono<ClientObject> clientObject, ServerWebExchange exchange);









}
