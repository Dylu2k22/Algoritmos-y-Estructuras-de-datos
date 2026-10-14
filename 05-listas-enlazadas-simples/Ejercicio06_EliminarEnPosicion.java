/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 6
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá en Java el método void eliminarEnPosicion(int posicion) dentro de una lista enlazada simple de enteros. Validá las posiciones fuera de rango (posicion < 0 o posicion >= size) y decrementá correctamente el atributo 'size'. Incluí comentarios explicativos detallados que describan cómo se localiza el nodo anterior al que se desea eliminar mediante un bucle de 'posicion - 1' pasos, y cómo se puentea la estructura haciendo 'actual.setSiguiente(actual.getSiguiente().getSiguiente())'. Programá pruebas en el método main eliminando la posición 0 (cabeza), una posición intermedia y la última posición."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación, los borrados en cabeza/medio/final y la
 * validación de índices antes de modificar la lista."
 * ============================================================================
 */
public class Ejercicio06_EliminarEnPosicion {
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

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) head = nuevo;
            else {
                Nodo actual = head;
                while (actual.getSiguiente() != null) actual = actual.getSiguiente();
                actual.setSiguiente(nuevo);
            }
            size++;
        }

        public void eliminarEnPosicion(int posicion) {
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
            }
            if (posicion == 0) {
                head = head.getSiguiente();
            } else {
                // Se avanza posicion - 1 veces para ubicar el nodo anterior.
                Nodo actual = head;
                for (int pasos = 0; pasos < posicion - 1; pasos++) {
                    actual = actual.getSiguiente();
                }
                // Puentea el objetivo; si se conserva la referencia antigua,
                // el nodo eliminado queda desconectado y será recolectable.
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
            }
            size--;
        }

        public void imprimir() {
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null (size=" + size + ")");
        }

        public int getSize() { return size; }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int dato : new int[] {10, 20, 30, 40, 50}) lista.insertarAlFinal(dato);
        lista.eliminarEnPosicion(0);
        System.out.print("Después de eliminar cabeza: ");
        lista.imprimir();
        lista.eliminarEnPosicion(1);
        System.out.print("Después de eliminar posición intermedia: ");
        lista.imprimir();
        lista.eliminarEnPosicion(lista.getSize() - 1);
        System.out.print("Después de eliminar último: ");
        lista.imprimir();
        try {
            lista.eliminarEnPosicion(lista.getSize());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
