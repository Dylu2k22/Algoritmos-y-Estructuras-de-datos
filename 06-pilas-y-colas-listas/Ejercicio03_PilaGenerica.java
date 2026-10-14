/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 3
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Transformá la implementación de pila enlazada en una clase genérica PilaGenerica<T> basada en un NodoGenerico<T>. La clase debe implementar push(T dato), pop(), peek(), isEmpty() e imprimir(). Incluí comentarios teóricos detallados explicando qué significan los tipos genéricos <T> en Java y fundamentando por qué la lógica de enlace de punteros y la regla LIFO es completamente independiente del tipo de dato almacenado. Programá un método main que pruebe independientemente la pila con tipos Integer, String y objetos de una clase personalizada Producto (con atributos id y nombre)."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación para los tres tipos y se agregaron validaciones
 * de pila vacía; los datos null se manejan sin fallar al imprimir."
 * ============================================================================
 */
public class Ejercicio03_PilaGenerica {
    /*
     * <T> es un parámetro de tipo: el compilador lo reemplaza por el tipo
     * elegido en cada instancia y evita mezclar, por ejemplo, String e Integer.
     * Los enlaces solo conectan nodos; no dependen del tipo de la carga útil.
     * Por eso el mismo algoritmo conserva LIFO para cualquier T: push enlaza
     * al inicio y pop quita ese mismo inicio, ambos en O(1).
     */
    private static final class NodoGenerico<T> {
        private final T dato;
        private NodoGenerico<T> siguiente;

        private NodoGenerico(T dato, NodoGenerico<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    public static final class PilaGenerica<T> {
        private NodoGenerico<T> head;

        public void push(T dato) {
            head = new NodoGenerico<>(dato, head);
        }

        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede desapilar: la pila está vacía.");
            }
            T dato = head.dato;
            head = head.siguiente;
            return dato;
        }

        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede consultar: la pila está vacía.");
            }
            return head.dato;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void imprimir() {
            System.out.print("Cima -> ");
            for (NodoGenerico<T> actual = head; actual != null; actual = actual.siguiente) {
                System.out.print(actual.dato + " -> ");
            }
            System.out.println("Fondo");
        }
    }

    private static final class Producto {
        private final int id;
        private final String nombre;

        private Producto(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "Producto{id=" + id + ", nombre='" + nombre + "'}";
        }
    }

    public static void main(String[] args) {
        PilaGenerica<Integer> enteros = new PilaGenerica<>();
        enteros.push(10);
        enteros.push(20);
        System.out.print("Integer: ");
        enteros.imprimir();
        System.out.println("Sale: " + enteros.pop());

        PilaGenerica<String> textos = new PilaGenerica<>();
        textos.push("primero");
        textos.push("segundo");
        System.out.print("String: ");
        textos.imprimir();
        System.out.println("Cima: " + textos.peek());

        PilaGenerica<Producto> productos = new PilaGenerica<>();
        productos.push(new Producto(1, "Teclado"));
        productos.push(new Producto(2, "Mouse"));
        System.out.print("Producto: ");
        productos.imprimir();
        System.out.println("Sale: " + productos.pop());
    }
}
