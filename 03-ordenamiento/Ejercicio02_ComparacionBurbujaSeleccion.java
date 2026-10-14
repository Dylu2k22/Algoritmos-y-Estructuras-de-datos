/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 02
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un programa en Java que contenga dos métodos estáticos independientes, bubbleSort(int[] array) y selectionSort(int[] array), los cuales recibirán como datos de entrada dos copias idénticas de un arreglo de enteros desordenado. El programa debe ejecutar ambos métodos e imprimir por pantalla el arreglo ordenado resultante junto con las métricas detalladas de rendimiento de cada uno: la cantidad total de comparaciones realizadas y la cantidad total de intercambios ejecutados en memoria. Incluí comentarios en el código que expliquen el razonamiento de cada algoritmo y razonen la diferencia estructural entre la estrategia de Bubble Sort (que compara e intercambia constantemente vecinos contiguos) y la de Selection Sort (que recorre el subarreglo desordenado buscando el menor elemento para realizar un único intercambio por pasada). Para verificar el resultado correcto, el programa debe comprobar que ambos métodos devuelvan arreglos idénticos correctamente ordenados y contrastar que Selection Sort realice drásticamente menos intercambios que Bubble Sort."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio02_ComparacionBurbujaSeleccion {
    private static class Metricas {
        int comparaciones;
        int intercambios;
    }

    public static void main(String[] args) {
        int[] entrada = {30, 15, 2, 21, 44, 8};
        int[] burbuja = Arrays.copyOf(entrada, entrada.length);
        int[] seleccion = Arrays.copyOf(entrada, entrada.length);
        Metricas metricasBurbuja = bubbleSort(burbuja);
        Metricas metricasSeleccion = selectionSort(seleccion);

        System.out.println("Bubble Sort:    " + Arrays.toString(burbuja));
        System.out.println("  Comparaciones: " + metricasBurbuja.comparaciones);
        System.out.println("  Intercambios:  " + metricasBurbuja.intercambios);
        System.out.println("Selection Sort: " + Arrays.toString(seleccion));
        System.out.println("  Comparaciones: " + metricasSeleccion.comparaciones);
        System.out.println("  Intercambios:  " + metricasSeleccion.intercambios);
        if (!Arrays.equals(burbuja, seleccion) || !estaOrdenado(burbuja)) {
            throw new IllegalStateException("Los algoritmos no produjeron el mismo orden correcto.");
        }
        System.out.println("Verificación: ambos arreglos están ordenados e idénticos.");
        System.out.println("Selection Sort realiza menos intercambios: "
                + (metricasSeleccion.intercambios < metricasBurbuja.intercambios));
    }

    // Bubble compara vecinos y puede intercambiar muchas veces en una pasada.
    public static Metricas bubbleSort(int[] array) {
        Metricas m = new Metricas();
        for (int fin = array.length - 1; fin > 0; fin--) {
            boolean cambio = false;
            for (int i = 0; i < fin; i++) {
                m.comparaciones++;
                if (array[i] > array[i + 1]) {
                    intercambiar(array, i, i + 1);
                    m.intercambios++;
                    cambio = true;
                }
            }
            if (!cambio) break;
        }
        return m;
    }

    // Selection busca el mínimo de toda la zona restante y hace como máximo
    // un intercambio por pasada, aunque sigue realizando O(n^2) comparaciones.
    public static Metricas selectionSort(int[] array) {
        Metricas m = new Metricas();
        for (int inicio = 0; inicio < array.length - 1; inicio++) {
            int indiceMenor = inicio;
            for (int i = inicio + 1; i < array.length; i++) {
                m.comparaciones++;
                if (array[i] < array[indiceMenor]) indiceMenor = i;
            }
            if (indiceMenor != inicio) {
                intercambiar(array, inicio, indiceMenor);
                m.intercambios++;
            }
        }
        return m;
    }

    private static void intercambiar(int[] array, int a, int b) {
        int auxiliar = array[a];
        array[a] = array[b];
        array[b] = auxiliar;
    }

    private static boolean estaOrdenado(int[] array) {
        for (int i = 1; i < array.length; i++) if (array[i - 1] > array[i]) return false;
        return true;
    }
}
