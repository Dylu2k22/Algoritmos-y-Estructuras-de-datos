/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 1
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá una implementación en Java de un algoritmo que encuentre el valor mínimo de un vector de números enteros. La estrategia más adecuada es realizar un recorrido lineal secuencial guardando en una variable el valor mínimo encontrado hasta el momento, comparándolo con cada elemento del vector. Esta estrategia es correcta porque garantiza encontrar el mínimo absoluto recorriendo todos los elementos exactamente una vez, lo que nos da una complejidad temporal de O(n) y espacial de O(1). Asegurate de que el código esté debidamente comentado y explicá detalladamente por qué no es necesario aplicar un costoso algoritmo de ordenamiento previo (como O(n log n) u O(n^2)) para encontrar este valor único."
 * ============================================================================
 */

public class Ejercicio01_MinimoVector {

    /**
     * Busca el menor valor recorriendo el vector una sola vez.
     *
     * Tiempo: O(n), porque se inspeccionan todos los elementos.
     * Espacio adicional: O(1), porque solo se conserva el mínimo actual.
     *
     * No es necesario ordenar: ordenar resolvería un problema más general y
     * costoso (O(n log n) o incluso O(n^2)) cuando solo interesa un valor.
     */
    public static int encontrarMinimo(int[] vector) {
        if (vector == null || vector.length == 0) {
            throw new IllegalArgumentException("El vector debe contener elementos.");
        }

        int minimo = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] < minimo) {
                minimo = vector[i];
            }
        }
        return minimo;
    }

    public static void main(String[] args) {
        int[] numeros = {8, -3, 12, 0, -7, 4};
        System.out.println("Vector: {8, -3, 12, 0, -7, 4}");
        System.out.println("Mínimo: " + encontrarMinimo(numeros));
    }
}
