/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 2
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java una función recursiva que calcule la sumatoria acumulada de los números enteros desde n hasta 1. Documentá en los comentarios que el problema se modela recursivamente mediante la expresión suma(n) = n + suma(n - 1), donde la sumatoria total se descompone en sumar el entero actual n al resultado de la suma de los anteriores. Establecé como caso más simple o caso base la condición n = 0, en la cual la función debe retornar 0, evitando iteraciones infinitas e indicando el límite de la llamada. Explicá qué ocurre cuando n es 0 e incluí validaciones para parámetros negativos. Agregá una clase de prueba con un método main que ejecute la función para n = 0, n = 1 y n = 10, verificando la salida por consola frente al cálculo analítico esperado para validar su correcto funcionamiento."
 * ============================================================================
 */

public class Ejercicio02_SumaN {

    /**
     * suma(n) = n + suma(n - 1). Cada marco conserva n mientras espera
     * el resultado de sumar los valores anteriores.
     */
    public static int suma(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n debe ser no negativo.");
        }

        // Caso base: suma(0) = 0, por lo que no se crean más marcos.
        if (n == 0) {
            return 0;
        }

        // Caso recursivo: n decrece hasta 0; al retornar se acumulan las sumas.
        return n + suma(n - 1);
    }

    private static void probar(int n, int esperado) {
        int resultado = suma(n);
        System.out.println("suma(" + n + ") = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto para n = " + n);
        }
    }

    public static void main(String[] args) {
        probar(0, 0);
        probar(1, 1);
        probar(10, 55);
    }
}
