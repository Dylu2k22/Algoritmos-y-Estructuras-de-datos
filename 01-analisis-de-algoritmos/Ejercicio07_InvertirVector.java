/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 7
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá dos versiones diferentes en Java para invertir el orden de los elementos de un vector de enteros. La primera versión deberá resolverlo utilizando un vector auxiliar adicional. La segunda versión deberá realizar la inversión in-place sobre el mismo vector original utilizando un sistema de punteros (inicio y fin), sin utilizar memoria adicional significativa. Incluí una sección de comentarios que compare ambas soluciones, indicando claramente sus ventajas, desventajas, y su complejidad temporal y espacial final."
 * ============================================================================
 */

import java.util.Arrays;

public class Ejercicio07_InvertirVector {

    /**
     * Copia los elementos en orden inverso a un nuevo vector.
     * Tiempo O(n), espacio adicional O(n). No modifica el vector de entrada.
     */
    public static int[] invertirConAuxiliar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int[] invertido = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            invertido[i] = vector[vector.length - 1 - i];
        }
        return invertido;
    }

    /**
     * Intercambia los extremos y avanza los punteros hacia el centro.
     * Tiempo O(n), espacio adicional O(1). Modifica el vector original.
     * La versión auxiliar simplifica conservar la entrada, a costa de memoria;
     * la versión in-place ahorra espacio pero altera el arreglo recibido.
     */
    public static void invertirInPlace(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int inicio = 0;
        int fin = vector.length - 1;
        while (inicio < fin) {
            int temporal = vector[inicio];
            vector[inicio] = vector[fin];
            vector[fin] = temporal;
            inicio++;
            fin--;
        }
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Con auxiliar: "
                + Arrays.toString(invertirConAuxiliar(original)));
        invertirInPlace(original);
        System.out.println("In-place: " + Arrays.toString(original));
    }
}
