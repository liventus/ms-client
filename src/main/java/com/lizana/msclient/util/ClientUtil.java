package com.lizana.msclient.util;


import com.lizana.msclient.entity.ClientEntity;
import com.lizana.msclient.model.ClientObject;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


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
}
