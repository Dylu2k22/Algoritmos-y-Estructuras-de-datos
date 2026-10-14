/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 9
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una clase en Java que implemente el método void invertir() para invertir in-place el sentido de todos los enlaces de una lista enlazada simple de enteros (por ejemplo, transformar 10 -> 20 -> 30 -> 40 -> null en 40 -> 30 -> 20 -> 10 -> null). Incluí comentarios teóricos extensos que expliquen las tres referencias auxiliares necesarias para lograr la inversión sin perder nodos: 'anterior' (inicializada en null), 'actual' (inicializada en head) y 'siguiente' (para preservar el resto de la lista antes de reconectar). Detallá paso a paso el ciclo de reasignación de punteros y por qué el orden de actualización es crítico. Mostrá el resultado en el método main imprimiendo la lista antes y después de invertir."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y la inversión in-place para lista no vacía;
 * también se verificó que las listas vacía y de un nodo no fallen."
 * ============================================================================
 */
public class Ejercicio09_InvertirLista {
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
    }

    private static final class ListaEnlazada {
        private Nodo head;
        private int size;

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) head = nuevo;
            else {
                Nodo actual = head;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
            size++;
        }

        /*
         * anterior inicia en null porque el primer nodo invertido será el
         * último y debe terminar en null. actual recorre desde head. Antes de
         * cambiar un enlace, siguiente guarda el resto de la cadena; luego se
         * invierte actual.siguiente y ambas referencias avanzan. Guardar
         * siguiente primero es esencial: de lo contrario se pierde el camino
         * al resto de nodos. Al final, head pasa al anterior, ahora nuevo inicio.
         */
        public void invertir() {
            Nodo anterior = null;
            Nodo actual = head;
            while (actual != null) {
                Nodo siguiente = actual.siguiente;
                actual.siguiente = anterior;
                anterior = actual;
                actual = siguiente;
            }
            head = anterior;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null (size=" + size + ")");
        }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int dato : new int[] {10, 20, 30, 40}) lista.insertarAlFinal(dato);
        System.out.print("Antes: ");
        lista.imprimir();
        lista.invertir();
        System.out.print("Después: ");
        lista.imprimir();
        ListaEnlazada vacia = new ListaEnlazada();
        vacia.invertir();
        vacia.imprimir();
    }
}
