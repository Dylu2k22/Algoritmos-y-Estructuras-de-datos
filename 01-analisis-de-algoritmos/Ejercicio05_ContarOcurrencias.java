/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 1: Análisis de Algoritmos
 * Ejercicio: 5
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Generá un programa en Java que implemente un algoritmo para contar cuántas veces aparece un determinado valor dentro de un vector. Justificá técnica y teóricamente mediante comentarios por qué este problema en particular requiere obligatoriamente recorrer el vector de principio a fin de manera exhaustiva, imposibilitando un corte anticipado. Finalmente, solicitá el análisis y documentación explícita de su complejidad temporal y espacial esperada."
 * ============================================================================
 */

public class Ejercicio05_ContarOcurrencias {

    /**
     * No se puede cortar al hallar una coincidencia: cualquier posición aún
     * no recorrida podría contener otra ocurrencia, y se pide el total exacto.
     * Por ello se inspeccionan siempre n elementos: tiempo O(n), incluso si
     * el valor no aparece. Solo se necesita el contador: espacio O(1).
     */
    public static int contar(int[] vector, int objetivo) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int cantidad = 0;
        for (int valor : vector) {
            if (valor == objetivo) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static void main(String[] args) {
        int[] numeros = {3, 5, 3, 8, 3, 1, 5};
        int objetivo = 3;
        System.out.println("El valor " + objetivo + " aparece "
                + contar(numeros, objetivo) + " veces.");
    }
}
