/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Transformá la implementación de lista enlazada simple de enteros en una versión genérica completa ListaEnlazada<T> y Nodo<T>. La lista debe operar con cualquier tipo de objeto, como ListaEnlazada<Integer>, ListaEnlazada<String> y objetos personalizados como ListaEnlazada<Alumno>. Incluí comentarios teóricos que expliquen qué partes del código cambian (definición de la clase con <T>, tipo de dato del nodo y uso obligatorio de .equals() para comparar igualdad de contenido en lugar de ==) y qué partes se mantienen exactamente iguales (los algoritmos de inserción, recorrido, inversión y eliminación de enlaces). Programá en el método main pruebas independientes para Integer, String y Alumno."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación y la comparación por contenido para Integer,
 * String y Alumno; los enlaces aceptan null sin causar errores al comparar."
 * ============================================================================
 */
public class Ejercicio10_ListaGenerica {
    /*
     * Los genéricos parametrizan la carga útil del nodo y la lista (<T>), con
     * comprobación de tipos en compilación. Las conexiones, inserciones,
     * recorrido, inversión y eliminación conservan el mismo algoritmo. Para
     * comparar contenido se usa equals, no == (que compararía identidad).
     */
    private static final class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;
        private Nodo(T dato) { this.dato = dato; }
    }

    private static final class ListaEnlazada<T> {
        private Nodo<T> head;
        private int size;

        public void insertarAlFinal(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (head == null) head = nuevo;
            else {
                Nodo<T> actual = head;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
            size++;
        }

        public boolean buscar(T dato) {
            Nodo<T> actual = head;
            while (actual != null) {
                if (dato == null ? actual.dato == null : dato.equals(actual.dato)) return true;
                actual = actual.siguiente;
            }
            return false;
        }

        public void invertir() {
            Nodo<T> anterior = null;
            Nodo<T> actual = head;
            while (actual != null) {
                Nodo<T> siguiente = actual.siguiente;
                actual.siguiente = anterior;
                anterior = actual;
                actual = siguiente;
            }
            head = anterior;
        }

        public boolean eliminar(T dato) {
            if (head == null) return false;
            if (sonIguales(head.dato, dato)) {
                head = head.siguiente;
                size--;
                return true;
            }
            Nodo<T> anterior = head;
            Nodo<T> actual = head.siguiente;
            while (actual != null) {
                if (sonIguales(actual.dato, dato)) {
                    anterior.siguiente = actual.siguiente;
                    size--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        private boolean sonIguales(T primero, T segundo) {
            return primero == null ? segundo == null : primero.equals(segundo);
        }

        public void imprimir() {
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null (size=" + size + ")");
        }
    }

    private static final class Alumno {
        private final String nombre;
        private final int legajo;

        private Alumno(String nombre, int legajo) {
            this.nombre = nombre;
            this.legajo = legajo;
        }

        @Override
        public boolean equals(Object objeto) {
            if (this == objeto) return true;
            if (!(objeto instanceof Alumno)) return false;
            Alumno otro = (Alumno) objeto;
            return legajo == otro.legajo && nombre.equals(otro.nombre);
        }

        @Override
        public int hashCode() {
            return 31 * nombre.hashCode() + legajo;
        }

        @Override
        public String toString() {
            return nombre + " (legajo " + legajo + ")";
        }
    }

    public static void main(String[] args) {
        ListaEnlazada<Integer> enteros = new ListaEnlazada<>();
        enteros.insertarAlFinal(10);
        enteros.insertarAlFinal(20);
        System.out.print("Integer: ");
        enteros.imprimir();
        System.out.println("Busca Integer.valueOf(20): " + enteros.buscar(Integer.valueOf(20)));

        ListaEnlazada<String> textos = new ListaEnlazada<>();
        textos.insertarAlFinal(new String("Estructuras"));
        textos.insertarAlFinal("Datos");
        System.out.print("String: ");
        textos.imprimir();
        System.out.println("Busca contenido Estructuras: " + textos.buscar(new String("Estructuras")));
        textos.invertir();
        System.out.print("String invertida: ");
        textos.imprimir();

        ListaEnlazada<Alumno> alumnos = new ListaEnlazada<>();
        alumnos.insertarAlFinal(new Alumno("Dylan Petrangeli", 1001));
        alumnos.insertarAlFinal(new Alumno("Ana García", 1002));
        System.out.print("Alumno: ");
        alumnos.imprimir();
        System.out.println("Busca Alumno equivalente: "
                + alumnos.buscar(new Alumno("Dylan Petrangeli", 1001)));
        System.out.println("Elimina Alumno equivalente: "
                + alumnos.eliminar(new Alumno("Dylan Petrangeli", 1001)));
        alumnos.imprimir();
    }
}
