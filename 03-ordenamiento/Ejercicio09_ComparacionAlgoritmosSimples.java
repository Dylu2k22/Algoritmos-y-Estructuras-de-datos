/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 09
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un programa en Java que implemente los tres algoritmos de ordenamiento simple (Bubble Sort, Selection Sort e Insertion Sort) para procesar tres copias idénticas de un mismo arreglo desordenado de enteros recibido como entrada. El programa debe ejecutar las tres estrategias e imprimir por pantalla: el arreglo final ordenado generado por cada método, la cantidad exacta de comparaciones realizadas y la cantidad de intercambios o desplazamientos ejecutados por cada uno. Incluí comentarios analíticos en el código que comparen las tres estrategias, razonando cómo varían los tipos de operaciones a pesar de compartir la misma complejidad promedio O(n^2). La verificación del resultado se efectuará comprobando que los tres métodos produzcan exactamente el mismo arreglo ordenado y constatando mediante las métricas por consola las diferencias de esfuerzo físico en memoria de cada algoritmo."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio09_ComparacionAlgoritmosSimples {
    private static class Metricas {
        int comparaciones;
        int movimientos;
    }

    public static void main(String[] args) {
        int[] entrada = {30, 15, 2, 21, 44, 8};
        int[] burbuja = Arrays.copyOf(entrada, entrada.length);
        int[] seleccion = Arrays.copyOf(entrada, entrada.length);
        int[] insercion = Arrays.copyOf(entrada, entrada.length);
        Metricas mb = bubbleSort(burbuja);
        Metricas ms = selectionSort(seleccion);
        Metricas mi = insertionSort(insercion);
        imprimir("Bubble Sort", burbuja, mb);
        imprimir("Selection Sort", seleccion, ms);
        imprimir("Insertion Sort", insercion, mi);
        if (!Arrays.equals(burbuja, seleccion) || !Arrays.equals(burbuja, insercion)
                || !estaOrdenado(burbuja)) {
            throw new IllegalStateException("Los tres algoritmos deben ordenar igual.");
        }
        System.out.println("Verificación: los tres resultados son idénticos y están ordenados.");
    }

    // Bubble usa intercambios vecinos. Selection compara O(n^2), pero hace
    // pocos intercambios. Insertion desplaza elementos; en promedio los tres
    // son O(n^2), aunque sus escrituras y su comportamiento en casi ordenados difieren.
    private static Metricas bubbleSort(int[] a) {
        Metricas m = new Metricas();
        for (int fin = a.length - 1; fin > 0; fin--) {
            boolean cambio = false;
            for (int i = 0; i < fin; i++) {
                m.comparaciones++;
                if (a[i] > a[i + 1]) {
                    int aux = a[i]; a[i] = a[i + 1]; a[i + 1] = aux;
                    m.movimientos++;
                    cambio = true;
                }
            }
            if (!cambio) break;
        }
        return m;
    }

    private static Metricas selectionSort(int[] a) {
        Metricas m = new Metricas();
        for (int i = 0; i < a.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < a.length; j++) {
                m.comparaciones++;
                if (a[j] < a[menor]) menor = j;
            }
            if (menor != i) {
                int aux = a[i]; a[i] = a[menor]; a[menor] = aux;
                m.movimientos++;
            }
        }
        return m;
    }

    private static Metricas insertionSort(int[] a) {
        Metricas m = new Metricas();
        for (int i = 1; i < a.length; i++) {
            int valor = a[i], j = i - 1;
            while (j >= 0) {
                m.comparaciones++;
                if (a[j] <= valor) break;
                a[j + 1] = a[j];
                m.movimientos++;
                j--;
            }
            a[j + 1] = valor;
        }
        return m;
    }

    private static void imprimir(String nombre, int[] arreglo, Metricas m) {
        System.out.println(nombre + ": " + Arrays.toString(arreglo));
        System.out.println("  Comparaciones: " + m.comparaciones);
        System.out.println("  Intercambios/desplazamientos: " + m.movimientos);
    }

    private static boolean estaOrdenado(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }
}
