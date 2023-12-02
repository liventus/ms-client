package com.lizana.msclient.business.Impl;

import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.repository.Clientrepository;
import com.lizana.msclient.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ClientBusinessImpl implements ClientBusiness {

    @Autowired
    private Clientrepository clientrepository;
    @Override
    public Mono<ResponseEntity<ClientObject>> saveClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {

       return  clientObject.map(ClientUtil::dtoToEntity)
                .flatMap(clientrepository::insert)
                .map(ClientUtil::entityToDto)
               .map(savedClient -> ResponseEntity.status(HttpStatus.CREATED).body(savedClient));

    }
}
