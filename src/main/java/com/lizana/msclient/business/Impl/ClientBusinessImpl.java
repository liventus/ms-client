package com.lizana.msclient.business.Impl;

import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.Status;
import com.lizana.msclient.repository.Clientrepository;
import com.lizana.msclient.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClientBusinessImpl implements ClientBusiness {

    @Autowired
    private Clientrepository clientrepository;

    @Override
    public Mono<ResponseEntity<ClientObject>> saveClient(Mono<ClientObject> clientObject) {


        return clientObject.map(ClientUtil::dtoToEntity)
                .flatMap(clientrepository::insert)
                .map(ClientUtil::entityToDto)
                .map(savedClient -> ResponseEntity.status(HttpStatus.CREATED).body(savedClient))
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));

    }

    @Override
    public Mono<ResponseEntity<Status>> deleteClient(ServerWebExchange exchange) {

        // debe buscar por tipo y numero de documento
        // si no existe , debe devolver que existe elemento
        // si encontro, debe eliminar,

        return null;
    }

    @Override
    public Mono<ResponseEntity<ClientObject>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {
        // debe buscar por tipo y numero de documento
        // si encontro mostrar
        // si no encontro debe pintar que cliente no existe (notFound)
/*
        return clientrepository.findBytipoDeDocumentoAndnumeroDeDocumento(tipoDeDocumento, numeroDeDocumento)
                .map(client -> ResponseEntity.ok().body(client))
                .defaultIfEmpty(ResponseEntity.notFound().build());*/
return null;

    }

    @Override
    public Mono<ResponseEntity<ClientObject>> updateClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {
        //primero buscar con tipo y numero de cliente, si no encuentra al cliente debe pintar que no existe

        // traer el objeto con su id
        // luego setear todos los objetos nuevos
        // debe guardar

        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<ClientObject>>> getClientAll(ServerWebExchange exchange) {

        // debe traer todos los clientes con sus id si es posible
        Flux<ClientObject> clientObjectFlux = clientrepository.findAll().map(ClientUtil::entityToDto);
        // aca me trae un flux y debo devolver un Mono.
        return Mono.just(ResponseEntity.ok().body(clientObjectFlux))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
}
