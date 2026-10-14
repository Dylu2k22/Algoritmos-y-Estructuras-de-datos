/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java una lista doblemente enlazada genérica ListaDoble<T> donde cada NodoDoble<T> posea referencias a 'siguiente' y 'anterior'. La lista debe implementar: insertarAlInicio(T dato), insertarAlFinal(T dato), eliminar(T dato), imprimirAdelante() e imprimirAtras(). Agregá comentarios teóricos extensos que expliquen la ventaja de disponer de punteros en ambas direcciones, detallando los casos especiales al eliminar nodos (eliminar único nodo, eliminar cabeza, eliminar cola o eliminar nodo intermedio) y el cuidado necesario para no dejar referencias colgadas. Validá en main realizando inserciones y recorridos en ambos sentidos."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación, eliminación por equals con null seguro y
 * actualización de ambos extremos en los cuatro casos de eliminación."
 * ============================================================================
 */
public class Ejercicio10_ListaDoblementeEnlazada {
    /*
     * Cada nodo enlaza con anterior y siguiente, permitiendo recorrer en ambos
     * sentidos sin construir otra estructura. head y tail marcan los extremos.
     * Al eliminar hay que reconectar ambas direcciones: en lista unitaria se
     * vacían ambos extremos; al quitar cabeza se avanza head y su anterior se
     * vuelve null; al quitar cola se retrocede tail y su siguiente se vuelve
     * null; en un nodo intermedio se enlazan directamente sus vecinos. Luego se
     * limpian los enlaces del nodo retirado para no conservar referencias
     * colgantes. La búsqueda/eliminación es O(n); insertar en un extremo O(1).
     */
    private static final class NodoDoble<T> {
        private final T dato;
        private NodoDoble<T> siguiente;
        private NodoDoble<T> anterior;

        private NodoDoble(T dato) {
            this.dato = dato;
        }
    }

    public static final class ListaDoble<T> {
        private NodoDoble<T> head;
        private NodoDoble<T> tail;

        public void insertarAlInicio(T dato) {
            NodoDoble<T> nuevo = new NodoDoble<>(dato);
            nuevo.siguiente = head;
            if (head == null) {
                tail = nuevo;
            } else {
                head.anterior = nuevo;
            }
            head = nuevo;
        }

        public void insertarAlFinal(T dato) {
            NodoDoble<T> nuevo = new NodoDoble<>(dato);
            nuevo.anterior = tail;
            if (tail == null) {
                head = nuevo;
            } else {
                tail.siguiente = nuevo;
            }
            tail = nuevo;
        }

        public boolean eliminar(T dato) {
            NodoDoble<T> actual = head;
            while (actual != null && !sonIguales(actual.dato, dato)) {
                actual = actual.siguiente;
            }
            if (actual == null) {
                return false;
            }

            if (actual.anterior == null) {
                head = actual.siguiente;
            } else {
                actual.anterior.siguiente = actual.siguiente;
            }
            if (actual.siguiente == null) {
                tail = actual.anterior;
            } else {
                actual.siguiente.anterior = actual.anterior;
            }
            actual.anterior = null;
            actual.siguiente = null;
            return true;
        }

        private boolean sonIguales(T primero, T segundo) {
            return primero == null ? segundo == null : primero.equals(segundo);
        }

        public void imprimirAdelante() {
            System.out.print("head -> ");
            for (NodoDoble<T> actual = head; actual != null; actual = actual.siguiente) {
                System.out.print(actual.dato + " <-> ");
            }
            System.out.println("null");
        }

        public void imprimirAtras() {
            System.out.print("tail -> ");
            for (NodoDoble<T> actual = tail; actual != null; actual = actual.anterior) {
                System.out.print(actual.dato + " <-> ");
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ListaDoble<String> lista = new ListaDoble<>();
        lista.insertarAlInicio("segundo");
        lista.insertarAlInicio("primero");
        lista.insertarAlFinal("tercero");
        lista.insertarAlFinal("cuarto");
        lista.imprimirAdelante();
        lista.imprimirAtras();
        System.out.println("Elimina cabeza: " + lista.eliminar("primero"));
        System.out.println("Elimina nodo intermedio: " + lista.eliminar("tercero"));
        lista.imprimirAdelante();
        lista.imprimirAtras();
        System.out.println("Elimina cola: " + lista.eliminar("cuarto"));
        System.out.println("Elimina último nodo: " + lista.eliminar("segundo"));
        lista.imprimirAdelante();
        lista.imprimirAtras();
    }
}
