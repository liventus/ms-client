package com.lizana.msclient.business;

import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ClientBusiness.
 */
public interface ClientBusiness {

  Mono<ResponseEntity<StatusResponse>> saveClient(Mono<ClientObject> clientObject,
                                                  ServerWebExchange exchange);

  Mono<ResponseEntity<StatusResponse>> deleteClient(String tipoDeDocumento,
                                                    String numeroDeDocumento,
                                                    ServerWebExchange exchange);

  Mono<ResponseEntity<StatusResponse>> getClient(String tipoDeDocumento, String numeroDeDocumento,
                                                 ServerWebExchange exchange);


  Mono<ResponseEntity<StatusResponse>> updateClient(Mono<ClientObject> clientObject,
                                                    ServerWebExchange exchange);

  Mono<ResponseEntity<Flux<ClientObject>>> getClientAll(ServerWebExchange exchange);


}
