/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 4
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java el método void insertarEnPosicion(int dato, int posicion) en una lista enlazada simple de enteros. El método debe soportar inserciones al inicio (posicion == 0), en el medio (0 < posicion < size) y al final (posicion == size), validando posiciones inválidas fuera de rango. Explicá con extrema claridad en comentarios el orden estricto de reconexión de referencias: primero 'nuevo.setSiguiente(actual.getSiguiente())' y luego 'actual.setSiguiente(nuevo)'. Justificá teóricamente el peligro de invertir dicho orden, advirtiendo que si se hace 'actual.setSiguiente(nuevo)' primero, la referencia al resto de la cadena se destruye y la sublista posterior queda permanentemente huérfana. Probá todos los escenarios de inserción en el método main."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación, las inserciones en inicio/medio/final y el
 * rechazo de índices negativos y mayores que size."
 * ============================================================================
 */
public class Ejercicio04_InsertarEnPosicion {
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
        private Nodo getSiguiente() { return siguiente; }
        private void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    }

    private static final class ListaEnlazada {
        private Nodo head;
        private int size;

        public void insertarEnPosicion(int dato, int posicion) {
            if (posicion < 0 || posicion > size) {
                throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
            }
            if (posicion == 0) {
                Nodo nuevo = new Nodo(dato);
                nuevo.setSiguiente(head);
                head = nuevo;
            } else {
                Nodo actual = head;
                for (int pasos = 0; pasos < posicion - 1; pasos++) {
                    actual = actual.getSiguiente();
                }
                Nodo nuevo = new Nodo(dato);
                /*
                 * Primero preservar la cadena que venía después de actual;
                 * recién entonces se enlaza actual con el nodo nuevo. Si se
                 * invirtiera el orden, actual apuntaría a nuevo y se perdería
                 * la única referencia al resto: esa sublista quedaría huérfana.
                 */
                nuevo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevo);
            }
            size++;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        public int getSize() { return size; }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarEnPosicion(20, 0);
        lista.insertarEnPosicion(40, 1);
        lista.insertarEnPosicion(10, 0);
        lista.insertarEnPosicion(30, 2);
        lista.insertarEnPosicion(50, lista.getSize());
        lista.imprimir();
        try {
            lista.insertarEnPosicion(99, lista.getSize() + 1);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
