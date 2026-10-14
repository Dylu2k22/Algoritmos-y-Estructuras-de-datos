/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 07
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá un programa en Java que aplique la implementación de Quicksort (seleccionando siempre el primer elemento como pivote) sobre un arreglo de entrada que ya se encuentra totalmente ordenado, como int[] numeros = {1, 2, 3, 4, 5, 6, 7};. El programa debe contar e imprimir por pantalla la cantidad total de llamadas recursivas ejecutadas durante el proceso, además de mostrar el arreglo final. Incluí comentarios detallados que expliquen analíticamente por qué elegir sistemáticamente el primer elemento como pivote frente a datos preordenados desencadena el peor caso de complejidad O(n^2), fundamentando cómo las particiones se vuelven completamente desbalanceadas al dejar siempre un subarreglo vacío y otro con n-1 elementos. Para verificar la precisión de la prueba, el programa debe evidenciar un número de llamadas recursivas proporcional al tamaño n del arreglo, confirmando la degradación cuadrática del rendimiento."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio07_QuickSortPeorCaso {
    private static int llamadas;
    private static long comparaciones;

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Arreglo inicial: " + Arrays.toString(numeros));
        quickSort(numeros, 0, numeros.length - 1);
        System.out.println("Llamadas recursivas (incluye casos base): " + llamadas);
        System.out.println("Comparaciones de partición: " + comparaciones);
        System.out.println("Arreglo final: " + Arrays.toString(numeros));
        if (!estaOrdenado(numeros) || llamadas != 2 * numeros.length - 1) {
            throw new IllegalStateException("La medición del peor caso no coincide con lo esperado.");
        }
    }

    private static void quickSort(int[] numeros, int inicio, int fin) {
        llamadas++;
        if (inicio >= fin) return;

        // Con el mínimo al principio, el pivote queda en inicio. Se generan
        // una partición vacía y otra de n-1; las particiones sucesivas repiten
        // el patrón, sumando (n-1)+(n-2)+...+1 = O(n^2) comparaciones.
        int pivote = numeros[inicio];
        int frontera = inicio;
        for (int i = inicio + 1; i <= fin; i++) {
            comparaciones++;
            if (numeros[i] <= pivote) {
                frontera++;
                int auxiliar = numeros[frontera];
                numeros[frontera] = numeros[i];
                numeros[i] = auxiliar;
            }
        }
        int auxiliar = numeros[inicio];
        numeros[inicio] = numeros[frontera];
        numeros[frontera] = auxiliar;
        quickSort(numeros, inicio, frontera - 1);
        quickSort(numeros, frontera + 1, fin);
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) if (numeros[i - 1] > numeros[i]) return false;
        return true;
    }
}
