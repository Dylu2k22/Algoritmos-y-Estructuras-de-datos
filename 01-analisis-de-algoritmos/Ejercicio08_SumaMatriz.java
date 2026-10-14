/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 8
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá una implementación en Java que calcule la suma de la totalidad de los elementos contenidos en una matriz bidimensional de enteros. Indicá explícitamente en el código cómo se recorrerá la matriz utilizando ciclos for anidados (priorizando el recorrido secuencial de filas y luego columnas). Además de calcular el total, el programa debe llevar un acumulador independiente que contabilice e imprima la cantidad exacta de operaciones de suma realizadas, e incluir el análisis de su complejidad temporal."
 * ============================================================================
 */

public class Ejercicio08_SumaMatriz {

    private static class ResultadoSuma {
        private final long total;
        private final long sumasRealizadas;

        private ResultadoSuma(long total, long sumasRealizadas) {
            this.total = total;
            this.sumasRealizadas = sumasRealizadas;
        }
    }

    /**
     * Recorre cada fila y, dentro de ella, cada columna.
     * Para una matriz rectangular n x m, el tiempo es O(n*m); espacio
     * adicional O(1). Se cuenta una operación de suma por celda acumulada,
     * para un total exacto de n*m sumas (cero si no hay celdas).
     */
    private static ResultadoSuma sumar(int[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("La matriz no puede ser null.");
        }

        int columnas = matriz.length == 0 ? 0 : matriz[0].length;
        for (int[] fila : matriz) {
            if (fila == null || fila.length != columnas) {
                throw new IllegalArgumentException("La matriz debe ser rectangular.");
            }
        }

        long total = 0;
        long sumasRealizadas = 0;
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                total += matriz[fila][columna];
                sumasRealizadas++;
            }
        }
        return new ResultadoSuma(total, sumasRealizadas);
    }

    public static void main(String[] args) {
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6}
        };
        ResultadoSuma resultado = sumar(matriz);
        System.out.println("Suma total: " + resultado.total);
        System.out.println("Operaciones de suma realizadas: "
                + resultado.sumasRealizadas);
    }
}
