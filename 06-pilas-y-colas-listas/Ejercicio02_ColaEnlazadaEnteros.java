/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 2
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java una clase llamada ColaEnlazadaEnteros que represente una cola de enteros mediante una lista enlazada simple con dos punteros de control: 'head' (frente) y 'tail' (final). La clase debe ofrecer los métodos enqueue(int dato), dequeue(), front(), isEmpty(), contiene(int dato) e imprimir(). Agregá comentarios teóricos detallados que expliquen por qué la cola necesita obligatoriamente mantener referencias tanto a 'head' como a 'tail', justificando qué problema grave de rendimiento O(n) aparecería en la operación de encolar (enqueue) si solo dispusiéramos del puntero 'head'. Demostrá el correcto funcionamiento en un método main ejecutando encolados, desencolados y consultas."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y el restablecimiento de head y tail al quitar
 * el último nodo; dequeue y front validan explícitamente la cola vacía."
 * ============================================================================
 */
public class Ejercicio02_ColaEnlazadaEnteros {
    /*
     * FIFO conserva el orden de llegada: dequeue extrae por head y enqueue
     * agrega por tail. Ambas referencias son necesarias para mantener O(1) en
     * las dos operaciones. Con solo head, insertar al final requeriría caminar
     * hasta el último nodo en cada enqueue, dando O(n) por inserción (y O(n²)
     * al agregar n datos). En la cola vacía ambos punteros son null; al retirar
     * el último elemento también deben volver a null para conservar el invariante.
     */
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;
    private Nodo tail;

    public void enqueue(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
        }
        int dato = head.dato;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }
        return dato;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar: la cola está vacía.");
        }
        return head.dato;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contiene(int dato) {
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            if (actual.dato == dato) {
                return true;
            }
        }
        return false;
    }

    public void imprimir() {
        System.out.print("Frente -> ");
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            System.out.print(actual.dato + " -> ");
        }
        System.out.println("Final");
    }

    public static void main(String[] args) {
        ColaEnlazadaEnteros cola = new ColaEnlazadaEnteros();
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.imprimir();
        System.out.println("Próximo: " + cola.front());
        System.out.println("¿Contiene 20? " + cola.contiene(20));
        System.out.println("Atendido (FIFO): " + cola.dequeue());
        cola.imprimir();
        System.out.println("Atendido: " + cola.dequeue());
        System.out.println("Atendido: " + cola.dequeue());
        System.out.println("¿Vacía al final? " + cola.isEmpty());
    }
}

final class ColaEnlazadaEnteros extends Ejercicio02_ColaEnlazadaEnteros {
}
