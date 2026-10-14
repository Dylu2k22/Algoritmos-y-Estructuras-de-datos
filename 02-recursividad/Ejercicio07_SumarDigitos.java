/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 7
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá un algoritmo recursivo en Java para calcular la suma de los dígitos de un número entero positivo. Comentá claramente en el código cómo el operador módulo n % 10 permite extraer el último dígito del número y cómo la división entera n / 10 reduce la magnitud del valor eliminando dicho dígito para la siguiente llamada. Definí como caso base cuando n < 10, donde la función simplemente retorna el valor de n por ser su propio único dígito. Detallá que la combinación de resultados se realiza mediante la instrucción (n % 10) + sumarDigitos(n / 10), sumando el dígito extraído actual al resultado acumulado recursivamente de las cifras restantes. Incluí un método main para validar la solución con los casos de prueba n = 5, n = 48, n = 305 y n = 1234."
 * ============================================================================
 */

public class Ejercicio07_SumarDigitos {

    /**
     * El módulo 10 extrae el último dígito; la división entera por 10
     * lo elimina antes de procesar recursivamente las cifras restantes.
     */
    public static int sumarDigitos(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo.");
        }

        // Caso base: si queda un único dígito, ese dígito es la suma.
        if (n < 10) {
            return n;
        }

        // Caso recursivo: cada marco agrega su dígito al resultado al desapilar.
        return (n % 10) + sumarDigitos(n / 10);
    }

    private static void probar(int n, int esperado) {
        int resultado = sumarDigitos(n);
        System.out.println("sumarDigitos(" + n + ") = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto para n = " + n);
        }
    }

    public static void main(String[] args) {
        probar(5, 5);
        probar(48, 12);
        probar(305, 8);
        probar(1234, 10);
    }
}
