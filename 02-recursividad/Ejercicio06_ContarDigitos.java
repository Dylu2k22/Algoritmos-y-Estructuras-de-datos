/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 6
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una función recursiva en Java que devuelva la cantidad total de dígitos que componen un número entero positivo. Explicá en los comentarios que la reducción del problema se realiza aplicando la división entera n / 10, la cual elimina progresivamente el último dígito del número en cada invocación. Establecé como caso base la condición n < 10, justificando que cualquier número menor que 10 consta matemáticamente de un único dígito, por lo que la función debe retornar 1. Explicá que cada llamada recursiva debe retornar 1 + contarDigitos(n / 10), sumando 1 al conteo parcial acumulado por el resto de las divisiones. Agregá pruebas de ejecución en el método main utilizando los valores 7, 42, 505 y 98765, e imprimí los resultados en consola."
 * ============================================================================
 */

public class Ejercicio06_ContarDigitos {

    /**
     * La división entera por 10 elimina el último dígito en cada llamada.
     * Los marcos apilados agregan uno al conteo parcial al retornar.
     */
    public static int contarDigitos(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }

        // Caso base: un número menor que 10, incluido 0, tiene un dígito.
        if (n < 10) {
            return 1;
        }

        // Caso recursivo: n / 10 reduce el problema hasta dejar un dígito.
        return 1 + contarDigitos(n / 10);
    }

    private static void probar(int n, int esperado) {
        int resultado = contarDigitos(n);
        System.out.println("contarDigitos(" + n + ") = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto para n = " + n);
        }
    }

    public static void main(String[] args) {
        probar(7, 1);
        probar(42, 2);
        probar(505, 3);
        probar(98765, 5);
    }
}
