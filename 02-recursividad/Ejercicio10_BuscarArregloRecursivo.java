/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java un algoritmo de búsqueda lineal recursiva dentro de un arreglo de enteros sin emplear estructuras iterativas (for, while o do-while). Diseñá la función para que reciba el arreglo, el elemento buscado y un parámetro adicional indice que inicialice la búsqueda desde la posición 0. Explicá en comentarios que el avance a través de la estructura se logra incrementando la posición en la llamada recursiva mediante buscar(arreglo, elemento, indice + 1). Establecé dos casos base: 1) si indice == arreglo.length, se alcanza el final sin éxito y la función devuelve -1 (o false); 2) si arreglo[indice] == elemento, se encuentra la coincidencia y la función devuelve el indice (o true). Programá pruebas en el método main evaluando la búsqueda de un elemento presente al inicio, uno en el medio, uno al final y uno inexistente."
 * ============================================================================
 */

public class Ejercicio10_BuscarArregloRecursivo {

    /**
     * Búsqueda lineal recursiva sin bucles: cada marco examina una posición.
     * Tiempo O(n) en el peor caso y espacio O(n) por la pila de llamadas.
     */
    public static int buscar(int[] arreglo, int elemento, int indice) {
        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }
        if (indice < 0 || indice > arreglo.length) {
            throw new IllegalArgumentException("El índice está fuera de rango.");
        }

        // Caso base de fracaso: se llegó al final sin encontrar el elemento.
        if (indice == arreglo.length) {
            return -1;
        }

        // Caso base de éxito: se retorna el índice de la primera coincidencia.
        if (arreglo[indice] == elemento) {
            return indice;
        }

        // Caso recursivo: el siguiente marco comienza en la posición siguiente.
        return buscar(arreglo, elemento, indice + 1);
    }

    private static void probar(int[] arreglo, int elemento, int esperado) {
        int resultado = buscar(arreglo, elemento, 0);
        System.out.println("buscar(" + elemento + ") = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto para: " + elemento);
        }
    }

    public static void main(String[] args) {
        int[] valores = {12, 7, 25, 4, 18};
        probar(valores, 12, 0);
        probar(valores, 25, 2);
        probar(valores, 18, 4);
        probar(valores, 99, -1);
    }
}
