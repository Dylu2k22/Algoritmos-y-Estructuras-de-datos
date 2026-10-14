/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 2
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java una lista enlazada simple de enteros que incluya el método boolean buscar(int dato). El método debe recorrer secuencialmente la lista desde la referencia 'head' evaluando cada nodo hasta encontrar la primera coincidencia (retornando true) o alcanzar 'null' (retornando false). Incluí comentarios teóricos extensos que expliquen por qué una lista enlazada no permite el acceso directo por índice instantáneo O(1) como un arreglo rígido, fundamentando por qué cualquier búsqueda exige obligatoriamente un recorrido secuencial O(n) iterando con un puntero caminante auxiliar 'actual = actual.getSiguiente()'. Programá un método main con casos de prueba buscando elementos presentes en la cabeza, en el medio, al final y un elemento inexistente."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se probaron coincidencias en los extremos,
 * en el medio y la búsqueda fallida; head vacío se trata sin desreferenciarlo."
 * ============================================================================
 */
public class Ejercicio02_BuscarElemento {
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
        private int getDato() { return dato; }
        private Nodo getSiguiente() { return siguiente; }
        private void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    }

    private static final class ListaEnlazada {
        private Nodo head;

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
                return;
            }
            Nodo actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        /*
         * Los nodos no ocupan necesariamente direcciones contiguas en RAM,
         * por lo que un índice no se traduce en un salto directo O(1) como en
         * un arreglo. Para buscar hay que seguir enlace por enlace: actual es
         * un puntero caminante que avanza con getSiguiente(). En el peor caso
         * se visita cada nodo, así que el tiempo es O(n).
         */
        public boolean buscar(int dato) {
            Nodo actual = head;
            while (actual != null) {
                if (actual.getDato() == dato) {
                    return true;
                }
                actual = actual.getSiguiente();
            }
            return false;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.getDato() + " -> ");
                actual = actual.getSiguiente();
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.imprimir();
        System.out.println("Cabeza (10): " + lista.buscar(10));
        System.out.println("Medio (20): " + lista.buscar(20));
        System.out.println("Final (40): " + lista.buscar(40));
        System.out.println("Inexistente (99): " + lista.buscar(99));
    }
}
