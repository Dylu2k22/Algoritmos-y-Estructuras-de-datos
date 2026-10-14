/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 5
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí en Java el método boolean eliminar(int dato) para una lista enlazada simple, el cual debe buscar y eliminar la primera aparición del valor especificado, retornando true si fue eliminado o false si no existía. Contemplá detalladamente los cinco casos límite: 1) lista vacía, 2) eliminar el primer nodo (actualizando head), 3) eliminar un nodo intermedio, 4) eliminar el último nodo y 5) dato inexistente. Incluí comentarios teóricos que expliquen que en Java no se destruye ni libera manualmente la memoria del nodo (como con free en C), sino que al modificar la referencia del nodo anterior para que apunte al siguiente, el nodo objetivo queda inaccesible y será recolectado automáticamente por el Garbage Collector. Probá cada caso en el método main."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se probaron lista vacía, eliminación en
 * cabeza/medio/final, valor ausente y consistencia del tamaño."
 * ============================================================================
 */
public class Ejercicio05_EliminarPorValor {
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
         * Casos cubiertos: lista vacía (false), cabeza (se mueve head),
         * intermedio/último (se puentea desde el anterior) y no encontrado
         * (false). Java no ofrece free: al perderse la última referencia al
         * nodo removido, el Garbage Collector podrá recuperarlo automáticamente.
         */
        public boolean eliminar(int dato) {
            if (head == null) return false;
            if (head.dato == dato) {
                head = head.siguiente;
                size--;
                return true;
            }
            Nodo anterior = head;
            Nodo actual = head.siguiente;
            while (actual != null) {
                if (actual.dato == dato) {
                    anterior.siguiente = actual.siguiente;
                    size--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
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
        ListaEnlazada vacia = new ListaEnlazada();
        System.out.println("Vacía, eliminar 1: " + vacia.eliminar(1));
        ListaEnlazada lista = new ListaEnlazada();
        for (int dato : new int[] {10, 20, 30, 40}) lista.insertarAlFinal(dato);
        System.out.println("Eliminar cabeza 10: " + lista.eliminar(10));
        lista.imprimir();
        System.out.println("Eliminar intermedio 30: " + lista.eliminar(30));
        lista.imprimir();
        System.out.println("Eliminar último 40: " + lista.eliminar(40));
        lista.imprimir();
        System.out.println("Inexistente 99: " + lista.eliminar(99));
    }
}
