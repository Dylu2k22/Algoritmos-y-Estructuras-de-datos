/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 4
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá en Java una clase genérica ColaGenerica<T> basada en una lista enlazada simple con referencias 'head' y 'tail'. Debe exponer los métodos enqueue(T dato), dequeue(), front(), isEmpty() e imprimir(). Incluí comentarios teóricos que expliquen cómo se preserva estrictamente el orden de llegada FIFO (First In, First Out) independientemente de la naturaleza del objeto almacenado. Programá un método main que valide el comportamiento operando de forma independiente con una cola de nombres (String) y una cola de objetos Cliente (con atributos id y nombre)."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación con String y Cliente, y el vaciado de la cola
 * restablece las referencias head y tail; dequeue y front controlan vacío."
 * ============================================================================
 */
public class Ejercicio04_ColaGenerica {
    /*
     * El parámetro <T> tipa únicamente el dato de cada nodo; el algoritmo de
     * enlaces es común a todos los tipos. FIFO se garantiza al agregar siempre
     * mediante tail y retirar siempre mediante head: nunca se altera el orden
     * de llegada, ya sea que T sea String o un objeto Cliente. Las operaciones
     * de inserción y extracción son O(1) gracias a las dos referencias.
     */
    private static final class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    public static final class ColaGenerica<T> {
        private Nodo<T> head;
        private Nodo<T> tail;

        public void enqueue(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (tail == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
        }

        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
            }
            T dato = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            return dato;
        }

        public T front() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede consultar: la cola está vacía.");
            }
            return head.dato;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void imprimir() {
            System.out.print("Frente -> ");
            for (Nodo<T> actual = head; actual != null; actual = actual.siguiente) {
                System.out.print(actual.dato + " -> ");
            }
            System.out.println("Final");
        }
    }

    private static final class Cliente {
        private final int id;
        private final String nombre;

        private Cliente(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "Cliente{id=" + id + ", nombre='" + nombre + "'}";
        }
    }

    public static void main(String[] args) {
        ColaGenerica<String> nombres = new ColaGenerica<>();
        nombres.enqueue("Ana");
        nombres.enqueue("Bruno");
        nombres.enqueue("Carla");
        System.out.print("Nombres: ");
        nombres.imprimir();
        System.out.println("Siguiente: " + nombres.front());
        System.out.println("Atendido: " + nombres.dequeue());

        ColaGenerica<Cliente> clientes = new ColaGenerica<>();
        clientes.enqueue(new Cliente(101, "Dylan"));
        clientes.enqueue(new Cliente(102, "Sofía"));
        System.out.print("Clientes: ");
        clientes.imprimir();
        System.out.println("Atendido: " + clientes.dequeue());
        System.out.println("Siguiente: " + clientes.front());
    }
}
