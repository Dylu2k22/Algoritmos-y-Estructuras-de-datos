/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 5
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá en Java una clase genérica Pila<T> implementada sobre un arreglo interno de tipo Object[] castando elementos al tipo T. La clase debe ofrecer los métodos de una pila estándar (push, pop, peek, isEmpty, isFull y size). Incluí comentarios teóricos detallados que expliquen qué problema resuelven los tipos genéricos <T> en la ingeniería de software, fundamentando cómo la encapsulación genérica abstrae la regla LIFO de la carga útil y la diferencia de una pila rígida de enteros primitivos (PilaEnteros), permitiendo reutilizar exactamente el mismo código para almacenar instancias de Integer, String u objetos personalizados como Alumno con total seguridad de tipos en tiempo de compilación. Programá un método main que instancie y pruebe independientemente una Pila<Integer>, una Pila<String> y una Pila<Alumno>, ejecutando operaciones de apilado y desapilado con salidas explicativas por consola."
 * ============================================================================
 */
public class Ejercicio05_PilaGenerica {
    /*
     * <T> parametriza el tipo de elemento: el compilador impide mezclar tipos
     * incompatibles en cada instancia, sin duplicar la lógica LIFO. Un int[],
     * como el de PilaEnteros, solo almacena enteros primitivos; esta estructura
     * guarda referencias en Object[] y recupera T mediante el cast controlado.
     * top=-1 representa vacío; push comprueba Overflow y pop/peek Underflow antes
     * de acceder al arreglo, protegiendo sus límites.
     */
    public static final class Pila<T> {
        private final Object[] elementos;
        private int top = -1;

        public Pila(int capacidad) {
            if (capacidad <= 0) {
                throw new IllegalArgumentException("La capacidad debe ser positiva.");
            }
            elementos = new Object[capacidad];
        }

        public void push(T elemento) {
            if (isFull()) {
                throw new IllegalStateException("Overflow: la pila está llena.");
            }
            elementos[++top] = elemento;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Underflow: la pila está vacía.");
            }
            T elemento = (T) elementos[top];
            elementos[top--] = null;
            return elemento;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Underflow: la pila está vacía.");
            }
            return (T) elementos[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == elementos.length - 1;
        }

        public int size() {
            return top + 1;
        }
    }

    public static final class Alumno {
        private final String nombre;
        private final int legajo;

        public Alumno(String nombre, int legajo) {
            this.nombre = nombre;
            this.legajo = legajo;
        }

        @Override
        public String toString() {
            return nombre + " (legajo " + legajo + ")";
        }
    }

    public static void main(String[] args) {
        Pila<Integer> enteros = new Pila<>(3);
        enteros.push(10);
        enteros.push(20);
        System.out.println("Pila<Integer>, cima: " + enteros.peek() + ", pop: " + enteros.pop());

        Pila<String> textos = new Pila<>(3);
        textos.push("primero");
        textos.push("segundo");
        System.out.println("Pila<String>, cima: " + textos.peek() + ", pop: " + textos.pop());

        Pila<Alumno> alumnos = new Pila<>(3);
        alumnos.push(new Alumno("Dylan Petrangeli", 1001));
        alumnos.push(new Alumno("Ana García", 1002));
        System.out.println("Pila<Alumno>, cima: " + alumnos.peek());
        System.out.println("Alumno desapilado: " + alumnos.pop());
        System.out.println("Tamaños restantes: Integer=" + enteros.size()
                + ", String=" + textos.size() + ", Alumno=" + alumnos.size());
    }
}
