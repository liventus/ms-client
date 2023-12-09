package com.lizana.msclient.util;


import com.lizana.msclient.model.StatusResponse;
import org.springframework.http.HttpStatus;



public class ValidationException extends RuntimeException {


    private  StatusResponse statusResponse;

    public ValidationException(int codigo, String descripcion) {
        super(descripcion);
        this.statusResponse = new StatusResponse();
        this.statusResponse.setCode(codigo);
        this.statusResponse.setDescription(descripcion);
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }
}
