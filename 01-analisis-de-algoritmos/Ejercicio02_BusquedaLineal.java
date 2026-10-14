/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 2
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá una implementación en Java que busque un elemento específico dentro de un vector desordenado. Utilizá la estrategia de Búsqueda Lineal, ya que al no tener la certeza de un ordenamiento, la única estrategia segura es revisar secuencialmente posición por posición. Incluí en los comentarios un análisis detallado del mejor caso (el elemento está en el índice 0), peor caso (el elemento está en la última posición o no existe) y el caso promedio. Además, el programa debe llevar un contador e informar explícitamente en consola cuántas posiciones fueron recorridas hasta hallar el elemento o hasta determinar su inexistencia."
 * ============================================================================
 */

public class Ejercicio02_BusquedaLineal {

    private static class ResultadoBusqueda {
        private final int indice;
        private final int posicionesRecorridas;

        private ResultadoBusqueda(int indice, int posicionesRecorridas) {
            this.indice = indice;
            this.posicionesRecorridas = posicionesRecorridas;
        }
    }

    /**
     * Mejor caso: el objetivo está en el índice 0, O(1), una inspección.
     * Peor caso: está al final o no existe, O(n), n inspecciones.
     * Caso promedio: para objetivos distribuidos uniformemente entre las
     * posiciones, se inspeccionan aproximadamente n/2 elementos, O(n).
     */
    private static ResultadoBusqueda buscar(int[] vector, int objetivo) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int recorridas = 0;
        for (int i = 0; i < vector.length; i++) {
            recorridas++;
            if (vector[i] == objetivo) {
                return new ResultadoBusqueda(i, recorridas);
            }
        }
        return new ResultadoBusqueda(-1, recorridas);
    }

    public static void main(String[] args) {
        int[] numeros = {18, 4, 27, 9, 13};
        int objetivo = 9;
        ResultadoBusqueda resultado = buscar(numeros, objetivo);
        if (resultado.indice >= 0) {
            System.out.println("Elemento " + objetivo + " encontrado en el índice "
                    + resultado.indice + ".");
        } else {
            System.out.println("Elemento " + objetivo + " inexistente.");
        }
        System.out.println("Posiciones recorridas: " + resultado.posicionesRecorridas);

        int ausente = 100;
        ResultadoBusqueda resultadoAusente = buscar(numeros, ausente);
        System.out.println("Elemento " + ausente
                + (resultadoAusente.indice >= 0 ? " encontrado." : " inexistente."));
        System.out.println("Posiciones recorridas: "
                + resultadoAusente.posicionesRecorridas);
    }
}
