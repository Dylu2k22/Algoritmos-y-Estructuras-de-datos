/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 1
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una clase en Java llamada PilaEnlazadaEnteros que implemente una estructura de pila de números enteros utilizando una lista enlazada simple interna, sin utilizar estructuras de la API estándar de Java. La clase debe ofrecer los métodos: push(int dato) para apilar, pop() para desapilar y retornar el valor, peek() para consultar el tope, isEmpty() para verificar si está vacía, contiene(int dato) para buscar un valor en la pila e imprimir() para mostrar su contenido de la cima al fondo. Incluí comentarios teóricos extensos que expliquen por qué el puntero 'head' debe representar obligatoriamente el tope de la pila, fundamentando que la inserción y extracción al inicio de una lista enlazada simple son operaciones de complejidad O(1) constante, evitando la necesidad de recorrer toda la lista. Agregá un método main para probar todas las operaciones."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación; pop y peek informan explícitamente el acceso
 * a una pila vacía y se probaron inserción, búsqueda y recorrido."
 * ============================================================================
 */
public class Ejercicio01_PilaEnlazadaEnteros {
    /*
     * Una pila aplica LIFO: el último dato insertado es el primero que sale.
     * head representa obligatoriamente la cima para que tanto push como pop
     * trabajen en el inicio de la lista: enlazar o avanzar una referencia cuesta
     * O(1). Si la cima estuviera al final de una lista simplemente enlazada,
     * quitarla exigiría recorrer los nodos para encontrar su predecesor (O(n)).
     * Cada nodo es una referencia dinámica a un dato y al siguiente nodo.
     */
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;

        private Nodo(int dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo head;

    public void push(int dato) {
        head = new Nodo(dato, head);
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede desapilar: la pila está vacía.");
        }
        int dato = head.dato;
        head = head.siguiente;
        return dato;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar: la pila está vacía.");
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
        System.out.print("Cima -> ");
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            System.out.print(actual.dato + " -> ");
        }
        System.out.println("Fondo");
    }

    public static void main(String[] args) {
        PilaEnlazadaEnteros pila = new PilaEnlazadaEnteros();
        System.out.println("¿Vacía al inicio? " + pila.isEmpty());
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.imprimir();
        System.out.println("Cima: " + pila.peek());
        System.out.println("¿Contiene 20? " + pila.contiene(20));
        System.out.println("Sale (LIFO): " + pila.pop());
        pila.imprimir();
        System.out.println("Sale: " + pila.pop());
        System.out.println("Sale: " + pila.pop());
        System.out.println("¿Vacía al final? " + pila.isEmpty());
    }
}

final class PilaEnlazadaEnteros extends Ejercicio01_PilaEnlazadaEnteros {
}
