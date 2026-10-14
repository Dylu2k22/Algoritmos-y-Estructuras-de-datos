/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 3
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá en Java una implementación del algoritmo de Búsqueda Binaria para encontrar un número dentro de un vector previamente ordenado. Explicá en los comentarios de forma analítica por qué esta estrategia de 'divide y vencerás' resulta matemáticamente mucho más eficiente que una búsqueda lineal tradicional, logrando una complejidad temporal esperada de O(log n). Además, el código debe contener impresiones en consola que muestren paso a paso cómo evolucionan y se ajustan las variables de búsqueda (límite inferior, límite superior y punto medio) en cada iteración del algoritmo."
 * ============================================================================
 */

public class Ejercicio03_BusquedaBinaria {

    /**
     * Precondición: el vector está ordenado de menor a mayor.
     *
     * En cada iteración se descarta aproximadamente la mitad del intervalo.
     * Tras k iteraciones quedan como máximo n / 2^k posiciones; el proceso
     * termina cuando esa cantidad llega a 1, por lo que k es O(log n).
     * La búsqueda lineal, en cambio, puede requerir inspeccionar n elementos.
     * Espacio adicional: O(1), ya que el algoritmo es iterativo.
     */
    public static int buscar(int[] vector, int objetivo) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int inferior = 0;
        int superior = vector.length - 1;
        int iteracion = 1;

        while (inferior <= superior) {
            int medio = inferior + (superior - inferior) / 2;
            System.out.println("Iteración " + iteracion + ": inferior=" + inferior
                    + ", superior=" + superior + ", medio=" + medio
                    + ", valor=" + vector[medio]);

            if (vector[medio] == objetivo) {
                return medio;
            } else if (vector[medio] < objetivo) {
                inferior = medio + 1;
            } else {
                superior = medio - 1;
            }
            iteracion++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ordenados = {2, 5, 8, 12, 16, 23, 38, 56, 72};
        int objetivo = 23;
        int indice = buscar(ordenados, objetivo);
        System.out.println(indice >= 0
                ? "Elemento encontrado en el índice " + indice + "."
                : "Elemento no encontrado.");
    }
}
