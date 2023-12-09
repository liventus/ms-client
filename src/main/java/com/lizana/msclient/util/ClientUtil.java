package com.lizana.msclient.util;


import com.lizana.msclient.entity.ClientEntity;
import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.StatusResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@NoArgsConstructor
public class ClientUtil {

    public static ClientObject entityToDto(ClientEntity clientEntity) {
        ClientObject clientObject = new ClientObject();
        BeanUtils.copyProperties(clientEntity, clientObject);
        return clientObject;
    }

    public static ClientEntity dtoToEntity(ClientObject clientObject) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientObject, clientEntity);
        return clientEntity;
    }

    public  static ResponseEntity<StatusResponse> setStatusResponse(HttpStatus http, ClientObject clientObject){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode(http.value());
        statusResponse.setDescription(http.name());
        statusResponse.setDetail(clientObject);


        return ResponseEntity.status(HttpStatus.CREATED).body( statusResponse);
    }
}
