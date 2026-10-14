/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 3
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un programa en Java con una función recursiva que calcule el producto de dos números enteros positivos A y B sin utilizar el operador aritmético de multiplicación *. Explicá en comentarios que la multiplicación de A * B se representa analíticamente como la suma repetida del factor A un total de B veces. Indicá que el parámetro a reducir en cada llamada recursiva es B, ejecutando la instrucción A + multiplicar(A, B - 1). Justificá por qué el caso base ocurre cuando el multiplicador B vale 0, en cuyo caso la función debe retornar 0 debido a que cualquier número multiplicado por cero es cero, deteniendo la recursión. Diseñá un método main para validar la implementación ejecutando pruebas con las combinaciones (5,0), (7,1), (4,3) y (6,5), mostrando los resultados obtenidos en consola."
 * ============================================================================
 */

public class Ejercicio03_MultiplicacionSumas {

    /**
     * El producto A por B es la suma repetida de A, B veces. Se reduce B
     * sin utilizar el operador aritmético de multiplicación.
     */
    public static int multiplicar(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("A y B deben ser no negativos.");
        }

        // Caso base: cualquier número por cero es cero; detiene la recursión.
        if (b == 0) {
            return 0;
        }

        // Caso recursivo: cada marco conserva una suma de A y reduce B en uno.
        return a + multiplicar(a, b - 1);
    }

    private static void probar(int a, int b, int esperado) {
        int resultado = multiplicar(a, b);
        System.out.println(a + " por " + b + " = " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto.");
        }
    }

    public static void main(String[] args) {
        probar(5, 0, 0);
        probar(7, 1, 7);
        probar(4, 3, 12);
        probar(6, 5, 30);
    }
}
