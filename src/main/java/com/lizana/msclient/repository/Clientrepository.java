package com.lizana.msclient.repository;


import com.lizana.msclient.entity.ClientEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository //, estás indicando a Spring que gestione esta clase como un bean de repositorio. debe ser interface.
public interface Clientrepository extends ReactiveMongoRepository<ClientEntity,String> {
}
