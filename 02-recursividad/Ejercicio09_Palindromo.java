/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 9
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una función recursiva en Java que retorne un valor booleano (boolean) indicando si una palabra dada es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda). Documentá que el proceso requiere evaluar simétricamente la cadena comparando el primer carácter charAt(0) con el último charAt(length() - 1). Explicá que si dichos caracteres son distintos, se activa el corte por no coincidencia retornando inmediatamente false. Si coinciden, el problema se reduce eliminando ambos extremos mediante substring(1, length() - 1) en la llamada recursiva. Definí que el caso base positivo ocurre cuando la longitud de la cadena es 0 o 1, retornando true. Incluí pruebas en el método main con las palabras "neuquen", "reconocer", "sometemos" y "java"."
 * ============================================================================
 */

public class Ejercicio09_Palindromo {

    /**
     * Compara simétricamente los extremos y reduce la cadena quitándolos.
     * La igualdad de los extremos solo permite continuar; el resultado de la
     * subcadena se propaga por los marcos pendientes del Call Stack.
     */
    public static boolean esPalindromo(String palabra) {
        if (palabra == null) {
            throw new IllegalArgumentException("La palabra no puede ser null.");
        }

        // Caso base positivo: no quedan pares que comparar.
        if (palabra.length() <= 1) {
            return true;
        }

        // Una diferencia en los extremos basta para retornar false.
        if (palabra.charAt(0) != palabra.charAt(palabra.length() - 1)) {
            return false;
        }

        // Caso recursivo: elimina ambos extremos y comprueba el interior.
        return esPalindromo(palabra.substring(1, palabra.length() - 1));
    }

    private static void probar(String palabra, boolean esperado) {
        boolean resultado = esPalindromo(palabra);
        System.out.println("\"" + palabra + "\" es palíndromo: " + resultado
                + " (esperado: " + esperado + ")");
        if (resultado != esperado) {
            throw new AssertionError("Resultado incorrecto para: " + palabra);
        }
    }

    public static void main(String[] args) {
        probar("neuquen", true);
        probar("reconocer", true);
        probar("sometemos", true);
        probar("java", false);
    }
}
