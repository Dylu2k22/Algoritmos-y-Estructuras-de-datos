/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 8
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Crea un programa en Java que invierta una cadena de texto o palabra utilizando una pila enlazada de caracteres (PilaCaracteres). Explicá en comentarios teóricos por qué la propiedad LIFO (Last In, First Out) invierte naturalmente la secuencia de símbolos, al empujar los caracteres en orden de izquierda a derecha y luego desapilarlos uno a uno para construir la palabra invertida. Demostrá el funcionamiento en el método main invirtiendo palabras como 'algoritmo' para obtener 'omtirogla'."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se valida la entrada null; la pila admite
 * cadenas vacías y preserva espacios y caracteres tal como fueron recibidos."
 * ============================================================================
 */
public class Ejercicio08_InvertirPalabraPila {
    /*
     * LIFO invierte el orden de manera natural: los caracteres se apilan desde
     * el primero al último y luego salen desde el último al primero. push/pop
     * cuestan O(1); el proceso completo cuesta O(n) y utiliza O(n) nodos.
     * Cada char corresponde a una unidad UTF-16, no necesariamente a un punto
     * de código Unicode completo; para esta práctica se invierten caracteres
     * char, como requiere la pila de caracteres indicada.
     */
    private static final class NodoCaracter {
        private final char dato;
        private NodoCaracter siguiente;

        private NodoCaracter(char dato, NodoCaracter siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private static final class PilaCaracteres {
        private NodoCaracter head;

        private void push(char dato) {
            head = new NodoCaracter(dato, head);
        }

        private char pop() {
            if (head == null) {
                throw new IllegalStateException("No se puede desapilar una pila vacía.");
            }
            char dato = head.dato;
            head = head.siguiente;
            return dato;
        }

        private boolean isEmpty() {
            return head == null;
        }
    }

    public static String invertir(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("El texto no puede ser null.");
        }
        PilaCaracteres pila = new PilaCaracteres();
        for (int i = 0; i < texto.length(); i++) {
            pila.push(texto.charAt(i));
        }
        StringBuilder invertido = new StringBuilder(texto.length());
        while (!pila.isEmpty()) {
            invertido.append(pila.pop());
        }
        return invertido.toString();
    }

    public static void main(String[] args) {
        String[] palabras = {"algoritmo", "pila", "Estructuras de Datos"};
        for (String palabra : palabras) {
            System.out.println("\"" + palabra + "\" -> \"" + invertir(palabra) + "\"");
        }
    }
}
