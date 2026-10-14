/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 9
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá en Java un algoritmo que encuentre el elemento de mayor valor dentro de una matriz de números enteros. Explicá y justificá en los comentarios por qué resulta matemáticamente necesario recorrer la totalidad de las celdas (filas y columnas) para tener la certeza de haber encontrado el máximo absoluto. Agregá también el análisis riguroso de su complejidad temporal (en función del tamaño n x m) y su complejidad espacial."
 * ============================================================================
 */

public class Ejercicio09_MayorElementoMatriz {

    /**
     * Se deben inspeccionar todas las celdas: cualquier celda no visitada
     * podría contener un valor superior al máximo provisional.
     * Para n filas y m columnas, el tiempo es O(n*m) y el espacio adicional
     * es O(1). La matriz debe ser rectangular y no estar vacía.
     */
    public static int encontrarMayor(int[][] matriz) {
        if (matriz == null || matriz.length == 0
                || matriz[0] == null || matriz[0].length == 0) {
            throw new IllegalArgumentException("La matriz debe contener elementos.");
        }

        int columnas = matriz[0].length;
        int mayor = matriz[0][0];
        for (int fila = 0; fila < matriz.length; fila++) {
            if (matriz[fila] == null || matriz[fila].length != columnas) {
                throw new IllegalArgumentException("La matriz debe ser rectangular.");
            }
            for (int columna = 0; columna < columnas; columna++) {
                if (matriz[fila][columna] > mayor) {
                    mayor = matriz[fila][columna];
                }
            }
        }
        return mayor;
    }

    public static void main(String[] args) {
        int[][] matriz = {
                {-8, 3, 12},
                {7, 25, -2},
                {11, 4, 9}
        };
        System.out.println("Mayor elemento de la matriz: "
                + encontrarMayor(matriz));
    }
}
