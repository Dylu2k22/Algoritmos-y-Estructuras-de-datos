/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 1
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una función recursiva en Java para calcular el factorial de un número entero no negativo. Explicá mediante comentarios en el código que el factorial se presta a una solución recursiva porque la definición matemática de n! equivale a n * (n - 1)!, descomponiendo la operación en la misma tarea sobre un valor menor. Definí como caso base cuando n es igual a 0 o 1, retornando explícitamente 1, ya que matemáticamente 0! = 1! = 1 y esto detiene la acumulación de llamadas en la pila de ejecución (Call Stack). Detallá cómo el caso recursivo reduce progresivamente el problema invocando n * factorial(n - 1), decrementando n en cada paso para acercarse al caso base. Incluí un método main con casos de prueba para los valores n = 0, n = 1, n = 5 y n = 10, e imprimí en consola tanto el resultado como comentarios didácticos sobre el apilado y desapilado de marcos de ejecución."
 * ============================================================================
 */

public class Ejercicio01_Factorial {

    /**
     * La definición n! = n * (n - 1)! expresa el problema en términos de una
     * instancia menor de la misma tarea.
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }

        // Caso base: 0! y 1! valen 1 y detienen nuevas llamadas en el Call Stack.
        if (n == 0 || n == 1) {
            return 1;
        }

        // Caso recursivo: n disminuye en cada llamada hasta alcanzar 1.
        // Al desapilar, cada marco multiplica n por el resultado de factorial(n - 1).
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int[] casos = {0, 1, 5, 10};
        for (int n : casos) {
            System.out.println("factorial(" + n + ") = " + factorial(n));
        }
        System.out.println("Call Stack: las llamadas se apilan al reducir n; "
                + "los resultados se multiplican al desapilar.");
    }
}
