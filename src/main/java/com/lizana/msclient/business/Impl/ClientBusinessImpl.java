package com.lizana.msclient.business.Impl;

import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.Status;
import com.lizana.msclient.repository.Clientrepository;
import com.lizana.msclient.util.ClientUtil;
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
    private Clientrepository clientrepository;

    @Override
    public Mono<ResponseEntity<ClientObject>> saveClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {


        return clientObject.map(ClientUtil::dtoToEntity)//Se utiliza para transformar cada elemento del flujo (por ejemplo, Flux o Mono)
                .flatMap(clientrepository::insert) //Se utiliza cuando la función de transformación devuelve un Flux o un Mono
                .map(ClientUtil::entityToDto)
                .map(savedClient -> ResponseEntity.status(HttpStatus.CREATED).body(savedClient))
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));

    }

    @Override
    public Mono<ResponseEntity<Status>> deleteClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {

        // debe buscar por tipo y numero de documento
        // si encontro, debe eliminar,
        // si no existe , debe devolver que existe elemento

        return clientrepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento, numeroDeDocumento)
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
    public Mono<ResponseEntity<ClientObject>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {

        // debe buscar por tipo y numero de documento
        // si encontro mostrar
        return clientrepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento, numeroDeDocumento)
                .map(ClientUtil::entityToDto)
                .map(client -> ResponseEntity.ok().body(client));
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
    public Mono<ResponseEntity<ClientObject>> updateClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {


        return clientObject.map(ClientUtil::dtoToEntity)
                .flatMap(actualizarCliente -> clientrepository
                        .findByTipoDeDocumentoAndNumeroDeDocumento(actualizarCliente.getTipoDeDocumento(), actualizarCliente.getNumeroDeDocumento())
                        .flatMap(existeCliente -> {
                            if (existeCliente != null) {
                                actualizarCliente.setId(existeCliente.getId());
                                return clientrepository.save(actualizarCliente)
                                        .then(Mono.just(ResponseEntity.ok().body(ClientUtil.entityToDto(actualizarCliente))));
                            } else {
                                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                            }
                        })
                );
    }


}
