/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá una implementación didáctica en Java del algoritmo de ordenamiento Bubble Sort para un vector de números enteros. El código debe incluir comentarios teóricos que expliquen detalladamente por qué la mecánica de Bubble Sort de comparar elementos adyacentes repetidas veces lo vuelve un algoritmo con complejidad de O(n^2), resultando adecuado únicamente para fines didácticos o conjuntos de datos sumamente pequeños. Además, programá contadores internos para que al finalizar el proceso se imprima en pantalla la cantidad exacta de comparaciones realizadas y la cantidad de intercambios de memoria ejecutados."
 * ============================================================================
 */

import java.util.Arrays;

public class Ejercicio10_BubbleSort {

    private static class Estadisticas {
        private final long comparaciones;
        private final long intercambios;

        private Estadisticas(long comparaciones, long intercambios) {
            this.comparaciones = comparaciones;
            this.intercambios = intercambios;
        }
    }

    /**
     * Cada pasada compara pares adyacentes y desplaza el mayor hacia el final.
     * Se requieren hasta n-1 pasadas y cada una hace O(n) comparaciones:
     * O(n^2) tiempo en general. Es in-place, con O(1) espacio adicional.
     * La cantidad de comparaciones de esta variante es exactamente n(n-1)/2;
     * los intercambios dependen del orden inicial. Por su costo cuadrático,
     * es útil sobre todo para enseñar el ordenamiento o para datos pequeños.
     */
    private static Estadisticas ordenar(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        long comparaciones = 0;
        long intercambios = 0;
        for (int pasada = 0; pasada < vector.length - 1; pasada++) {
            for (int indice = 0; indice < vector.length - 1 - pasada; indice++) {
                comparaciones++;
                if (vector[indice] > vector[indice + 1]) {
                    int temporal = vector[indice];
                    vector[indice] = vector[indice + 1];
                    vector[indice + 1] = temporal;
                    intercambios++;
                }
            }
        }
        return new Estadisticas(comparaciones, intercambios);
    }

    public static void main(String[] args) {
        int[] numeros = {5, 1, 4, 2, 8};
        System.out.println("Vector inicial: " + Arrays.toString(numeros));
        Estadisticas estadisticas = ordenar(numeros);
        System.out.println("Vector ordenado: " + Arrays.toString(numeros));
        System.out.println("Comparaciones realizadas: "
                + estadisticas.comparaciones);
        System.out.println("Intercambios realizados: "
                + estadisticas.intercambios);
    }
}
