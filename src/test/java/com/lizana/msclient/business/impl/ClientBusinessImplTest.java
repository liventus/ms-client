package com.lizana.msclient.business.impl;

import com.lizana.msclient.entity.ClientEntity;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.StatusResponse;
import com.lizana.msclient.repository.ClientRepository;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientBusinessImplTest {
  @Mock
  ClientRepository clientRepository;

  @InjectMocks
  ClientBusinessImpl clientBusiness;

  @Test
  void saveClientCaseExito() {
    ClientObject clientObject = new ClientObject();
    clientObject.setTipo("personal");
    when(clientRepository.insert((ClientEntity) any())).thenReturn(Mono.just(new ClientEntity()));
    Mono<ResponseEntity<StatusResponse>> resultMono = clientBusiness.saveClient(Mono.just(clientObject), null);
    StepVerifier.create(resultMono)
        .expectNextMatches(responseEntity -> {
          assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
          assertNotNull(responseEntity.getBody().getDetail());
          return true;
        })
        .verifyComplete();
  }

  @Test
  void saveClientCaseFail() {
    ClientObject clientObject = new ClientObject();
    clientObject.setTipo("otro tipo");
    when(clientRepository.insert((ClientEntity) any())).thenReturn(Mono.just(new ClientEntity()));
    Mono<ResponseEntity<StatusResponse>> resultMono = clientBusiness.saveClient(Mono.just(clientObject), null);
    StepVerifier.create(resultMono)
        .expectNextMatches(responseEntity -> {
          assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
          assertNotNull(responseEntity.getBody().getDetail());
          return true;
        })
        .verifyComplete();
  }


}