package com.lizana.msclient.service;


import com.lizana.msclient.api.ClientApiDelegate;
import com.lizana.msclient.business.ClientBusiness;
import com.lizana.msclient.model.ClientObject;

import com.lizana.msclient.model.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements ClientApiDelegate {

    @Autowired
    ClientBusiness clientBusiness;

    @Override
    public Mono<ResponseEntity<StatusResponse>> addClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {
        return clientBusiness.saveClient(clientObject,exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponse>> deleteClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {
        return clientBusiness.deleteClient(tipoDeDocumento,numeroDeDocumento,exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponse>> getClient(String tipoDeDocumento, String numeroDeDocumento, ServerWebExchange exchange) {
        return clientBusiness.getClient(tipoDeDocumento,numeroDeDocumento,exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponse>> updateClient(Mono<ClientObject> clientObject, ServerWebExchange exchange) {
        return clientBusiness.updateClient(clientObject,exchange);
    }
}
