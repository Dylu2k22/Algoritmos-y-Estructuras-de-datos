/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 5: Listas Enlazadas Simples
 * Ejercicio: 3
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá un programa en Java que incorpore el método int obtener(int posicion) dentro de una estructura de lista enlazada simple. El método debe devolver el valor entero almacenado en el nodo de la posición especificada (basada en índice 0). Validá rigurosamente los casos de posiciones inválidas (posicion < 0 o posicion >= size) lanzando una excepción IndexOutOfBoundsException. Incluí comentarios teóricos detallados que expliquen que, aunque la firma del método utilice una posición numérica como si fuera un vector, la lista enlazada carece de direcciones contiguas en RAM y por lo tanto debe recorrer paso a paso exactamente 'posicion' veces utilizando un bucle. Demostrá el método desde main consultando posiciones válidas e inválidas."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se comprobó la compilación y se validaron índices negativos, el límite
 * superior y los accesos válidos por índice basado en cero."
 * ============================================================================
 */
public class Ejercicio03_ObtenerPorPosicion {
    private static final class Nodo {
        private final int dato;
        private Nodo siguiente;
        private Nodo(int dato) { this.dato = dato; }
    }

    private static final class ListaEnlazada {
        private Nodo head;
        private int size;

        public void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo actual = head;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
            size++;
        }

        /*
         * Aunque posicion parezca un índice de vector, los nodos no están en
         * direcciones contiguas. No se calcula una dirección por aritmética:
         * desde head se avanza exactamente posicion enlaces, secuencialmente.
         */
        public int obtener(int posicion) {
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
            }
            Nodo actual = head;
            for (int pasos = 0; pasos < posicion; pasos++) {
                actual = actual.siguiente;
            }
            return actual.dato;
        }

        public int getSize() { return size; }
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(11);
        lista.insertarAlFinal(22);
        lista.insertarAlFinal(33);
        System.out.println("Posición 0: " + lista.obtener(0));
        System.out.println("Posición 2: " + lista.obtener(2));
        probarIndice(lista, -1);
        probarIndice(lista, lista.getSize());
    }

    private static void probarIndice(ListaEnlazada lista, int posicion) {
        try {
            System.out.println("Posición " + posicion + ": " + lista.obtener(posicion));
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Índice inválido " + posicion + ": " + exception.getMessage());
        }
    }
}
