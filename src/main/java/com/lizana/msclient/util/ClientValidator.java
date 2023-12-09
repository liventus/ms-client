package com.lizana.msclient.util;

import com.lizana.msclient.model.ClientObject;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
public class ClientValidator {

    public static void validateClientObject(ClientObject clientObject) {


        if (!(clientObject.getTipo().equals("personal") || clientObject.getTipo().equals("empresarial"))) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "debe ser de tipo personal o empresarial",clientObject);
        }


    }
}
