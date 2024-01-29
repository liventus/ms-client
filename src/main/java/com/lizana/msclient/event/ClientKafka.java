package com.lizana.msclient.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientKafka {

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
