/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 4
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java una función recursiva que calcule la potencia de una base entera elevada a un exponente entero no negativo sin hacer uso de Math.pow. Explicá en la documentación del código que la potenciación a^b equivale a realizar multiplicaciones sucesivas de la base a por sí misma un total de b veces, reduciendo el exponente en 1 en cada paso recursivo mediante la llamada base * potencia(base, exponente - 1). Justificá por qué el caso base se alcanza cuando el exponente es 0, retornando estrictamente 1 dado que cualquier número elevado a la potencia cero es igual a 1. Detallá qué valor retorna la función tanto en el caso base como en cada retorno del caso recursivo. Agregá un conjunto de pruebas en el método main evaluando 2^0, 3^1, 2^5 y 5^3, e imprimí los resultados por consola."
 * ============================================================================
 */

public class Ejercicio04_Potencia {

    /**
     * La potencia base^exponente equivale a multiplicar la base por sí misma
     * exponente veces. Cada marco pendiente la multiplica por la potencia menor.
     */
    public static long potencia(int base, int exponente) {
        if (exponente < 0) {
            throw new IllegalArgumentException("El exponente debe ser no negativo.");
        }

        // Caso base: cualquier base elevada a cero retorna estrictamente 1.
        if (exponente == 0) {
            return 1;
        }

        // Caso recursivo: el exponente decrece; al retornar se multiplica por base.
        return base * potencia(base, exponente - 1);
    }

    private static void probar(int base, int exponente, long esperado) {
        long resultado = potencia(base, exponente);
        System.out.println(base + "^" + exponente + " = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto.");
        }
    }

    public static void main(String[] args) {
        probar(2, 0, 1);
        probar(3, 1, 3);
        probar(2, 5, 32);
        probar(5, 3, 125);
    }
}
