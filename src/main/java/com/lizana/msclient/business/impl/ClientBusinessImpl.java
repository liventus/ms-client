package com.lizana.msclient.business.impl;

import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;

import com.lizana.msclient.model.StatusResponse;
import com.lizana.msclient.repository.ClientRepository;
import com.lizana.msclient.util.ClientUtil;
import com.lizana.msclient.util.ClientValidator;
import com.lizana.msclient.util.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClientBusinessImpl implements ClientBusiness {

  @Autowired
  private ClientRepository clientrepository;

  @Override
  public Mono<ResponseEntity<StatusResponse>> saveClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {


    return clientObject
        .doOnNext(ClientValidator::validateClientObject)
        .map(ClientUtil::dtoToEntity)
        .flatMap(clientrepository::insert)
        .map(ClientUtil::entityToDto)
        .map(x -> ClientUtil.setStatusResponse(HttpStatus.CREATED, x))
        .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())))
        .onErrorResume(ValidationException.class, ex ->
            Mono.just(ResponseEntity.status(ex.getStatusResponse().getCode())
                .body(ex.getStatusResponse())));

  }

  @Override
  public Mono<ResponseEntity<StatusResponse>> deleteClient(String tipoDeDocumento,
                                                           String numeroDeDocumento,
                                                           ServerWebExchange exchange) {

    // debe buscar por tipo y numero de documento
    // si encontro, debe eliminar,
    // si no existe , debe devolver que existe elemento

    return clientrepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento,
            numeroDeDocumento)
        .flatMap(client -> {
          if (client != null) {
            return clientrepository.deleteById(client.getId())
                .then(Mono.just(ResponseEntity.ok().build()));
          } else {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
          }
        });

  }

  @Override
  public Mono<ResponseEntity<StatusResponse>> getClient(String tipoDeDocumento,
                                                        String numeroDeDocumento,
                                                        ServerWebExchange exchange) {

    // debe buscar por tipo y numero de documento
    // si encontro mostrar
    return clientrepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento,
            numeroDeDocumento)
        .map(ClientUtil::entityToDto)
        .map(x -> ClientUtil.setStatusResponse(HttpStatus.OK, x));
  }


  @Override
  public Mono<ResponseEntity<Flux<ClientObject>>> getClientAll(ServerWebExchange exchange) {

    // debe traer todos los clientes con sus id si es posible
    Flux<ClientObject> clientObjectFlux = clientrepository.findAll().map(ClientUtil::entityToDto);
    // aca me trae un flux y debo devolver un Mono.
    return Mono.just(ResponseEntity.ok().body(clientObjectFlux))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

  }

  @Override
  public Mono<ResponseEntity<StatusResponse>> updateClient(Mono<ClientObject> clientObject,
                                                           ServerWebExchange exchange) {


    return clientObject.map(ClientUtil::dtoToEntity)
        .flatMap(actualizarCliente -> clientrepository
            .findByTipoDeDocumentoAndNumeroDeDocumento(actualizarCliente.getTipoDeDocumento(),
                actualizarCliente.getNumeroDeDocumento())
            .flatMap(existeCliente -> {
              if (existeCliente != null) {
                actualizarCliente.setId(existeCliente.getId());
                return clientrepository.save(actualizarCliente)
                    .map(ClientUtil::entityToDto)
                    .map(x -> ClientUtil.setStatusResponse(HttpStatus.CREATED, x));
              } else {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
              }
            })
        );
  }


}
