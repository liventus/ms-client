package com.lizana.msclient.util;

import com.lizana.msclient.model.ClientObject;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Clase.
 */
@NoArgsConstructor
public class ClientValidator {

  /**
   * autor daniel.
   *
   * @param clientObject de tipo cliente.
   */
  public static void validateClientObject(ClientObject clientObject) {


    if (!(clientObject.getTipo().equals("personal") || clientObject.getTipo()
        .equals("empresarial"))) {
      throw new ValidationException(HttpStatus.BAD_REQUEST.value(),
          "debe ser de tipo personal o empresarial",
          clientObject);
    }


  }
}
