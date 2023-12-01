package com.lizana.msclient.bootcamp;


import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class programacionFuncional {
    public static void main(String[] args) {

        /*La programación funcional es un estilo de escribir programas donde tratamos las operaciones como
         si fueran funciones matemáticas. En lugar de cambiar y manipular datos directamente, trabajamos con
         funciones que toman datos de entrada y producen resultados sin cambiar nada fuera de sí mismas. Además,
         evitamos efectos secundarios impredecibles y nos centramos en construir programas mediante la composición
         de estas funciones.*/
        //----------------------Supplier----------------------
        // Uso de Supplier para obtener un mensaje
        Supplier<String> proveedorMensaje = () -> "¡Hola, soy un mensaje suministrado por un Supplier!";
        // Obtener y mostrar el mensaje
        String mensaje = proveedorMensaje.get();
        System.out.println(mensaje);

        //----------------------Consumer----------------------
        /* La interfaz Consumer en Java es parte del paquete java.util.function y representa una operación que toma un solo
         argumento de entrada y no devuelve ningún resultado. Es decir, toma algo (puede ser un objeto, un valor, etc.)
        y realiza alguna operación sobre ese objeto sin devolver nada.

        */
        Consumer<Integer> imprimirDoble = numero -> {
            int resultado = numero * 2;
            System.out.println("El doble de " + numero + " es: " + resultado);
        };
        imprimirDoble.accept(5);

        //----------------------ByConsumer----------------------
        // BiConsumer que imprime dos números
        BiConsumer<Integer, Integer> imprimirSuma = (num1, num2) ->
        System.out.println("La suma de " + num1 + " y " + num2 + " es: " + (num1 + num2));
        imprimirSuma.accept(3,5);

        //----------------------Predicate----------------------
        // Predicate que verifica si un número es par
        Predicate<Integer> esPar = num -> num % 2 == 0;
        int numero = 4;
        if (esPar.test(numero)) {
            System.out.println(numero + " es un número par.");
        } else {
            System.out.println(numero + " no es un número par.");
        }

        // es una operacion que sera tratado como una funcion y estara iniciazado con interfaces funcioneles
        // Función que duplica un número
        Function<Integer, Integer> duplicar = x -> x * 2;
        int resultado = duplicar.apply(5);
        System.out.println("El resultado de duplicar 5 es: " + resultado);


        // UnaryOperator que duplica un número
        UnaryOperator<Integer> duplicar2 = x -> x * 2;
        int resultado2 = duplicar2.apply(5);
        System.out.println("El resultado de duplicar 5 es: " + resultado2);


    }

}
