package com.lizana.msclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data //para generar set y get
@AllArgsConstructor //generar automáticamente un constructor que incluye todos los campos de la clase como parámetros
@NoArgsConstructor  //  Lombok genera automáticamente un constructor sin argumentos para esa clase.
@Document(collection = "client") //marca una clase como un documento que puede ser  almacenado en  base de datos MongoDB
public class ClientEntity {

    @Id
    private String id;
    private String tipoDeDocumento;
    private String numeroDeDocumento;
    private String direccion;
    private String tipo;
    private String nombre;
    private String apellido;
    private Boolean clienteVip;
    private String ruc;
    private String razonSocial;
    private Boolean clientePyme;

}
