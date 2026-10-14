/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 01
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá un programa en Java que implemente el algoritmo de ordenamiento Bubble Sort para ordenar de menor a mayor un arreglo desordenado de enteros recibido como entrada, como por ejemplo {30, 15, 2, 21, 44, 8}. El razonamiento del algoritmo se basa en comparar elementos vecinos adyacentes e intercambiarlos si están fuera de orden, repitiendo este proceso en múltiples pasadas. El programa debe imprimir por consola el arreglo original antes de comenzar y el estado completo del arreglo al finalizar cada pasada del ciclo externo. Incluí comentarios en el código que expliquen conceptualmente por qué el elemento de mayor valor se va desplazando o 'flotando' progresivamente hacia la última posición disponible del arreglo no ordenado en cada iteración. Para verificar que el resultado sea correcto, la ejecución debe mostrar la evolución paso a paso hasta obtener el arreglo totalmente ordenado en pantalla y validar que no se realicen más pasadas de las necesarias."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio01_BubbleSortPasoAPaso {
    public static void main(String[] args) {
        int[] numeros = {30, 15, 2, 21, 44, 8};
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        bubbleSortConTraza(numeros);
        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        if (!estaOrdenado(numeros)) {
            throw new IllegalStateException("Bubble Sort no ordenó el arreglo.");
        }
    }

    private static void bubbleSortConTraza(int[] numeros) {
        for (int fin = numeros.length - 1, pasada = 1; fin > 0; fin--, pasada++) {
            boolean huboIntercambio = false;
            for (int i = 0; i < fin; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    int auxiliar = numeros[i];
                    numeros[i] = numeros[i + 1];
                    numeros[i + 1] = auxiliar;
                    huboIntercambio = true;
                }
            }
            // Cada comparación puede empujar un valor grande hacia la derecha,
            // como una burbuja; por eso el mayor de la zona pendiente queda fijado al final.
            System.out.println("Pasada " + pasada + ": " + Arrays.toString(numeros));
            if (!huboIntercambio) {
                System.out.println("Sin intercambios: el arreglo ya está ordenado; se detienen las pasadas.");
                break;
            }
        }
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i - 1] > numeros[i]) return false;
        }
        return true;
    }
}
