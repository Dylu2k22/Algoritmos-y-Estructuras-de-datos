/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 1
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una implementación en Java de una lista enlazada simple de números enteros desde cero, sin utilizar estructuras de la API de Java como ArrayList o LinkedList. Definí una clase Nodo con los atributos privados 'dato' (int) y 'siguiente' (Nodo), junto con sus constructores y getters/setters. Creá la clase ListaEnlazada con los atributos privados 'head' (Nodo) y 'size' (int). Implementá los métodos insertarAlInicio(int dato), insertarAlFinal(int dato), imprimir(), estaVacia() y getSize(). Agregá comentarios teóricos detallados explicando que un nodo es la unidad básica que almacena el dato útil y la referencia al próximo elemento, que 'head' es la referencia ancla al primer nodo cuya pérdida destruye toda la estructura, y cómo el atributo 'size' se actualiza incrementándose en cada inserción. Probá la lista en el método main realizando inserciones y mostrando el estado por consola."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se comprobaron las inserciones en lista vacía
 * y no vacía, la actualización de size y la impresión de una lista vacía."
 * ============================================================================
 */
public class Ejercicio01_CrearListaEnlazada {
    /*
     * Un nodo es la unidad básica: contiene el dato útil y una referencia al
     * nodo siguiente. La referencia siguiente del último nodo es null.
     */
    private static final class Nodo {
        private int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this(dato, null);
        }

        private Nodo(int dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }

        private int getDato() { return dato; }
        private void setDato(int dato) { this.dato = dato; }
        private Nodo getSiguiente() { return siguiente; }
        private void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    }

    private static final class ListaEnlazada {
        /*
         * head es el ancla al primer nodo. Perder esa referencia sin conservar
         * otra ruta deja inaccesibles todos los nodos de la cadena. size cuenta
         * los nodos y se incrementa exactamente una vez por inserción.
         */
        private Nodo head;
        private int size;

        public void insertarAlInicio(int dato) {
            head = new Nodo(dato, head);
            size++;
        }

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo actual = head;
                while (actual.getSiguiente() != null) {
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(nuevo);
            }
            size++;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.getDato() + (actual.getSiguiente() == null ? "" : " -> "));
                actual = actual.getSiguiente();
            }
            System.out.println(actual == null && size == 0 ? "Lista vacía" : " -> null");
        }

        public boolean estaVacia() { return head == null; }
        public int getSize() { return size; }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        System.out.println("Vacía al inicio: " + lista.estaVacia());
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        System.out.print("Lista: ");
        lista.imprimir();
        System.out.println("Tamaño: " + lista.getSize());
    }
}
