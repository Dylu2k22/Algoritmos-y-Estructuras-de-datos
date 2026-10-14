/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 4
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá dos implementaciones distintas en Java para detectar si existen elementos duplicados dentro de un vector. La primera versión debe resolver el problema utilizando un enfoque de fuerza bruta con dos ciclos anidados. La segunda versión debe solucionar el mismo problema pero utilizando una estructura de datos HashSet. Por último, incluí como comentarios una comparación exhaustiva entre ambas implementaciones analizando el trade-off desde el punto de vista de la complejidad temporal y espacial de cada enfoque."
 * ============================================================================
 */

import java.util.HashSet;
import java.util.Set;

public class Ejercicio04_DetectarDuplicados {

    /**
     * Fuerza bruta compara cada pareja de posiciones.
     * Tiempo: O(n^2) en el peor caso; espacio adicional: O(1).
     * Puede detenerse al encontrar el primer duplicado.
     */
    public static boolean tieneDuplicadosFuerzaBruta(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        for (int i = 0; i < vector.length; i++) {
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[i] == vector[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * HashSet ofrece inserción y consulta O(1) promedio, por lo que el tiempo
     * esperado es O(n). En el peor caso teórico puede degradarse según las
     * colisiones. A cambio, almacena hasta n valores: espacio O(n).
     * Fuerza bruta ahorra memoria, HashSet suele ser mucho más rápido para
     * vectores grandes; ambos responden al detectar el primer repetido.
     */
    public static boolean tieneDuplicadosConHashSet(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        Set<Integer> vistos = new HashSet<>();
        for (int valor : vector) {
            if (!vistos.add(valor)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] conDuplicados = {4, 7, 2, 9, 7};
        int[] sinDuplicados = {4, 7, 2, 9};

        System.out.println("Fuerza bruta, {4, 7, 2, 9, 7}: "
                + tieneDuplicadosFuerzaBruta(conDuplicados));
        System.out.println("HashSet, {4, 7, 2, 9, 7}: "
                + tieneDuplicadosConHashSet(conDuplicados));
        System.out.println("HashSet, {4, 7, 2, 9}: "
                + tieneDuplicadosConHashSet(sinDuplicados));
    }
}
