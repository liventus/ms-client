package com.lizana.msclient.util;

import com.lizana.msclient.model.ClientObject;
import com.lizana.msclient.model.StatusResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientValidatorTest {

  @Test
  @DisplayName("return validate client")
  public void testValidationException() {
    //preparar data
    ClientObject clientObject = new ClientObject();
    clientObject.setTipo("otroTipo");
    ValidationException validationException = new ValidationException(
        HttpStatus.BAD_REQUEST.value(),
        "debe ser de tipo personal o empresarial",
        clientObject
    );




    // Verificar que getStatusResponse devuelve una instancia no nula de StatusResponse
    StatusResponse statusResponse = validationException.getStatusResponse();
    assertNotNull(statusResponse);

    // Verificar que los valores en la instancia de StatusResponse son los esperados
    assertEquals(HttpStatus.BAD_REQUEST.value(), statusResponse.getCode());
    assertEquals("debe ser de tipo personal o empresarial", statusResponse.getDescription());
    assertEquals(clientObject, statusResponse.getDetail());
  }

  @Test
  @DisplayName("return validate client")
  public void testValidateClientObject() {
    // Crear un objeto ClientObject de prueba
    ClientObject clientObject = mock(ClientObject.class);
    when(clientObject.getTipo()).thenReturn("otroTipo");


    try {
      // metodo a probar
      ClientValidator.validateClientObject(clientObject);
    } catch (ValidationException ex) {
      // validacion de respuesta esperada
      assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getStatusResponse().getCode());
      assertEquals("debe ser de tipo personal o empresarial", ex.getStatusResponse().getDescription());
      assertEquals(clientObject, ex.getStatusResponse().getDetail());
      return;
    }

    // en caso no capture exception
    assertEquals("Se esperaba una ValidationException pero no se lanzó.", false);
  }
}