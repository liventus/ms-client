package com.lizana.msclient.business;

import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClientBusiness {

    Mono<ResponseEntity<ClientObject>> saveClient(Mono<ClientObject> clientObject);

    Mono<ResponseEntity<Status>> deleteClient(ServerWebExchange exchange);

    Mono<ResponseEntity<ClientObject>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange);


    Mono<ResponseEntity<ClientObject>> updateClient(Mono<ClientObject> clientObject, ServerWebExchange exchange);

    Mono<ResponseEntity<Flux<ClientObject>>> getClientAll(ServerWebExchange exchange);


}
