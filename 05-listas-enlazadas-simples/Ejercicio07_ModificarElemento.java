/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 7
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java el método void modificar(int posicion, int nuevoDato) para una lista enlazada simple. El método debe validar la posición solicitada antes de recorrer la lista y actualizar la carga útil del nodo hallado. Agregá comentarios teóricos detallados que expliquen la diferencia fundamental entre modificar el dato interno de un nodo ('actual.setDato(nuevoDato)'), lo cual mantiene la topología y enlaces de la lista intactos, y modificar la referencia al siguiente nodo ('actual.setSiguiente(...)'), la cual altera la estructura y el orden de la cadena. Validá el comportamiento en main imprimiendo la lista antes y después de modificar."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y se validó el índice antes del recorrido y
 * antes de realizar cualquier cambio en la carga útil."
 * ============================================================================
 */
public class Ejercicio07_ModificarElemento {
    private static final class Nodo {
        private int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
        private int getDato() { return dato; }
        private void setDato(int dato) { this.dato = dato; }
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

        public void modificar(int posicion, int nuevoDato) {
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
            }
            Nodo actual = head;
            for (int pasos = 0; pasos < posicion; pasos++) {
                actual = actual.getSiguiente();
            }
            /*
             * setDato cambia solamente la carga útil: la topología y los
             * enlaces quedan intactos. setSiguiente, en cambio, modifica la
             * estructura, pudiendo alterar el orden o desconectar nodos.
             */
            actual.setDato(nuevoDato);
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
        for (int dato : new int[] {10, 20, 30}) lista.insertarAlFinal(dato);
        System.out.print("Antes: ");
        lista.imprimir();
        lista.modificar(1, 99);
        System.out.print("Después: ");
        lista.imprimir();
        try {
            lista.modificar(3, 100);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
