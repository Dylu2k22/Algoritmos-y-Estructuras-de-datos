/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 06
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá el algoritmo Quicksort en Java para ordenar un arreglo de enteros desordenados recibido como entrada, utilizando de forma estricta el primer elemento de cada subarreglo como pivote. El razonamiento del algoritmo se fundamenta en el paradigma Divide y Vencerás, donde se particiona la estructura en subarreglos de menores e iguales a la izquierda y mayores a la derecha. Por pantalla, el programa debe mostrar didácticamente en cada llamada recursiva: el pivote elegido, el subarreglo izquierdo generado, el subarreglo derecho generado y, finalmente, el arreglo completo ordenado. Incluí comentarios explícitos que relacionen la estructura del algoritmo con la recursividad, el uso de la pila de llamadas (Call Stack) y el concepto de condición de corte cuando el subarreglo tiene tamaño 0 o 1. La verificación del correcto resultado se validará comprobando que la unión recursiva de las particiones produzca la secuencia ordenada en consola."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio06_QuickSortPivoteInicial {
    public static void main(String[] args) {
        int[] numeros = {30, 15, 2, 21, 44, 8, 15};
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        quickSort(numeros, 0, numeros.length - 1, 0);
        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        if (!estaOrdenado(numeros)) throw new IllegalStateException("QuickSort no ordenó el arreglo.");
    }

    private static void quickSort(int[] numeros, int inicio, int fin, int nivel) {
        // Caso base: un rango vacío o unitario ya está ordenado y detiene la recursión.
        if (inicio >= fin) return;
        String sangria = repetir("  ", nivel);
        int pivote = numeros[inicio]; // Se elige estrictamente el primer elemento del rango.
        int frontera = inicio;
        for (int i = inicio + 1; i <= fin; i++) {
            if (numeros[i] <= pivote) {
                frontera++;
                intercambiar(numeros, frontera, i);
            }
        }
        intercambiar(numeros, inicio, frontera);
        System.out.println(sangria + "Pivote: " + pivote);
        System.out.println(sangria + "Subarreglo izquierdo: "
                + Arrays.toString(Arrays.copyOfRange(numeros, inicio, frontera)));
        System.out.println(sangria + "Subarreglo derecho: "
                + Arrays.toString(Arrays.copyOfRange(numeros, frontera + 1, fin + 1)));
        // Cada llamada queda en la Call Stack hasta resolver sus particiones; al
        // regresar, la concatenación izquierda + pivote + derecha está ordenada.
        quickSort(numeros, inicio, frontera - 1, nivel + 1);
        quickSort(numeros, frontera + 1, fin, nivel + 1);
    }

    private static void intercambiar(int[] numeros, int a, int b) {
        int auxiliar = numeros[a]; numeros[a] = numeros[b]; numeros[b] = auxiliar;
    }

    private static String repetir(String texto, int cantidad) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cantidad; i++) resultado.append(texto);
        return resultado.toString();
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) if (numeros[i - 1] > numeros[i]) return false;
        return true;
    }
}
