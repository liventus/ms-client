package com.lizana.msclient.util;


import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.StatusResponse;
import org.springframework.http.HttpStatus;



public class ValidationException extends RuntimeException {
    private  StatusResponse statusResponse;

    public ValidationException(int codigo, String descripcion, ClientObject clientObject) {
        super.getMessage();
        this.statusResponse = new StatusResponse();
        this.statusResponse.setCode(codigo);
        this.statusResponse.setDescription(descripcion);
        this.statusResponse.setDetail(clientObject);
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }
}
