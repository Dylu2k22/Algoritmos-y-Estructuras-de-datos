/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 03
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java el algoritmo Insertion Sort para ordenar un arreglo de enteros que recibe como datos de entrada la estructura casi ordenada int[] numeros = {1, 2, 3, 5, 4, 6, 7};. El razonamiento del algoritmo se basa en mantener una zona ordenada a la izquierda e ir insertando cada elemento nuevo en su posición correcta. El programa debe imprimir en consola el arreglo inicial, el arreglo final ordenado y la cantidad exacta de desplazamientos de elementos realizados durante la inserción. Incluí comentarios detallados que expliquen por qué Insertion Sort presenta una alta eficiencia cercana a O(n) cuando los datos están casi ordenados, justificando cómo la condición del ciclo interno detiene anticipadamente las comparaciones al no encontrar elementos fuera de lugar. La verificación del resultado se realizará comprobando que el arreglo final esté ordenado y constatando que el número total de desplazamientos sea mínimo debido al estado casi ordenado del arreglo de entrada."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio03_InsertionSortCasiOrdenado {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 5, 4, 6, 7};
        System.out.println("Arreglo inicial: " + Arrays.toString(numeros));
        int desplazamientos = insertionSort(numeros);
        System.out.println("Arreglo final:   " + Arrays.toString(numeros));
        System.out.println("Desplazamientos: " + desplazamientos);
        if (!estaOrdenado(numeros) || desplazamientos != 1) {
            throw new IllegalStateException("La verificación de Insertion Sort falló.");
        }
    }

    public static int insertionSort(int[] numeros) {
        int desplazamientos = 0;
        // La zona a la izquierda de i ya está ordenada; se inserta allí el nuevo valor.
        for (int i = 1; i < numeros.length; i++) {
            int valor = numeros[i];
            int j = i - 1;
            // Si el predecesor ya es menor o igual, la condición falla enseguida:
            // con datos casi ordenados hay pocos retrocesos y el costo se acerca a O(n).
            while (j >= 0 && numeros[j] > valor) {
                numeros[j + 1] = numeros[j];
                desplazamientos++;
                j--;
            }
            numeros[j + 1] = valor;
        }
        return desplazamientos;
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) if (numeros[i - 1] > numeros[i]) return false;
        return true;
    }
}
