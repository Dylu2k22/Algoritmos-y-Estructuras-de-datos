/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 8
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Crea un programa en Java que implemente el método int contarOcurrencias(int dato) dentro de una lista enlazada simple de enteros. El método debe realizar un recorrido exhaustivo completo desde 'head' hasta 'null', contando cuántas veces aparece el valor buscado dentro de la lista (por ejemplo, para la lista 10 -> 20 -> 10 -> 30 -> 10 -> null y el valor 10, debe retornar 3). Explicá mediante comentarios por qué no se debe cortar el recorrido al encontrar la primera coincidencia, fundamentando que a diferencia de una búsqueda booleana, la determinación de frecuencias exige inspeccionar la totalidad de la estructura O(n). Probá el método en main con un ejemplo claro."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se comprobó la frecuencia esperada, incluyendo
 * búsquedas sin coincidencias."
 * ============================================================================
 */
public class Ejercicio08_ContarOcurrencias {
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
    }

    private static final class ListaEnlazada {
        private Nodo head;

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) head = nuevo;
            else {
                Nodo actual = head;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
        }

        /*
         * Una búsqueda booleana puede parar en su primera coincidencia, pero
         * una frecuencia requiere inspeccionar cada nodo hasta null. El
         * puntero caminante avanza secuencialmente y el costo total es O(n).
         */
        public int contarOcurrencias(int dato) {
            int cantidad = 0;
            Nodo actual = head;
            while (actual != null) {
                if (actual.dato == dato) cantidad++;
                actual = actual.siguiente;
            }
            return cantidad;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int dato : new int[] {10, 20, 10, 30, 10}) lista.insertarAlFinal(dato);
        lista.imprimir();
        System.out.println("Ocurrencias de 10: " + lista.contarOcurrencias(10));
        System.out.println("Ocurrencias de 99: " + lista.contarOcurrencias(99));
    }
}
