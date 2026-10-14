/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 8
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Crea una función recursiva en Java que invierta el orden de los caracteres de una cadena de texto (String). Justificá en los comentarios que el caso base se alcanza cuando la longitud del String es menor o igual a 1 (cadena vacía "" o de un único carácter), retornando la cadena tal como está. Explicá cómo se reduce el problema en el caso recursivo separando el primer carácter con charAt(0) y reduciendo el texto restante con substring(1). Detallá que la reconstrucción del resultado al desapilar la memoria se logra mediante la concatenación invertir(cadena.substring(1)) + cadena.charAt(0), colocando el primer carácter al final de la subcadena invertida. Proba el código en el método main utilizando la cadena vacía "", un carácter "A", y las palabras "Hola" y "Recursion"."
 * ============================================================================
 */

public class Ejercicio08_InvertirPalabra {

    /**
     * Se procesa la subcadena sin el primer carácter. Al desapilar, ese
     * carácter se agrega al final del texto ya invertido.
     */
    public static String invertir(String cadena) {
        if (cadena == null) {
            throw new IllegalArgumentException("La cadena no puede ser null.");
        }

        // Caso base: una cadena vacía o de un carácter ya está invertida.
        if (cadena.length() <= 1) {
            return cadena;
        }

        // Caso recursivo: substring(1) reduce la cadena; charAt(0) se
        // concatena al final al reconstruir el resultado desde el Call Stack.
        return invertir(cadena.substring(1)) + cadena.charAt(0);
    }

    private static void probar(String texto, String esperado) {
        String resultado = invertir(texto);
        System.out.println("\"" + texto + "\" -> \"" + resultado + "\""
                + " (esperado: \"" + esperado + "\")");
        if (!resultado.equals(esperado)) {
            throw new AssertionError("Inversión incorrecta.");
        }
    }

    public static void main(String[] args) {
        probar("", "");
        probar("A", "A");
        probar("Hola", "aloH");
        probar("Recursion", "noisruceR");
    }
}
