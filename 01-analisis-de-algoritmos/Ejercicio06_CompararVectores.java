/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 6
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá una implementación en Java que determine si dos vectores de enteros son exactamente iguales. El algoritmo debe estar optimizado con un 'corte temprano' para finalizar y retornar false en el instante exacto en que detecte una diferencia de tamaños o el primer elemento distinto. Incluí en los comentarios el análisis asintótico de complejidad temporal detallando el mejor caso (tamaños distintos o diferencia en el primer índice), el peor caso (vectores idénticos) y el caso promedio."
 * ============================================================================
 */

public class Ejercicio06_CompararVectores {

    /**
     * Mejor caso: longitudes distintas o diferencia en el primer elemento,
     * O(1). Peor caso: vectores idénticos, O(n). Caso promedio: O(n) si las
     * diferencias pueden aparecer a lo largo de las posiciones; el costo
     * esperado depende de dónde aparece la primera diferencia. Espacio O(1).
     */
    public static boolean sonIguales(int[] primero, int[] segundo) {
        if (primero == null || segundo == null) {
            return primero == segundo;
        }
        if (primero.length != segundo.length) {
            return false;
        }

        for (int i = 0; i < primero.length; i++) {
            if (primero[i] != segundo[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8};
        int[] b = {2, 4, 6, 8};
        int[] c = {2, 4, 5, 8};

        System.out.println("a y b son iguales: " + sonIguales(a, b));
        System.out.println("a y c son iguales: " + sonIguales(a, c));
        System.out.println("a y {2, 4} son iguales: "
                + sonIguales(a, new int[]{2, 4}));
    }
}
