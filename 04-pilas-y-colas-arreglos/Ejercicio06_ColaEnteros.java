/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 6
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá una clase en Java llamada ColaEnteros que represente una cola lineal de enteros utilizando un arreglo de tamaño fijo. La clase debe incluir los métodos enqueue(int x) para insertar al final, dequeue() para retirar y devolver el primer elemento al frente, front() o peek() para consultar el elemento en espera sin removerlo, isEmpty(), isFull() y size(). Incluí comentarios teóricos explicativos que detallen el rol de los dos índices de control: front (que señala la posición del primer elemento que saldrá) y rear (que indica la posición donde se insertará el próximo dato). Explica detenidamente cómo incrementa rear al encolar datos y cómo incrementa front al desencolarlos, desplazándose ambos hacia la derecha. Diseñá un método main que ejecute una secuencia de inserciones y extracciones, imprimiendo en pantalla el estado interno del arreglo y los valores de front y rear tras cada operación."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio06_ColaEnteros {
    private final int[] elementos;
    // front identifica el próximo dato FIFO que saldrá; rear identifica dónde
    // se escribirá el próximo dato. Ambos avanzan hacia la derecha en esta cola lineal.
    // Si rear alcanza la capacidad ocurre Overflow aunque front haya liberado
    // índices iniciales; front == rear indica vacío y dequeue/front lanzan Underflow.
    private int front = 0;
    private int rear = 0;

    public Ejercicio06_ColaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        elementos = new int[capacidad];
    }

    public void enqueue(int x) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: la cola lineal no tiene posiciones finales libres.");
        }
        elementos[rear++] = x;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: no hay elementos para desencolar.");
        }
        return elementos[front++];
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: la cola está vacía.");
        }
        return elementos[front];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == elementos.length;
    }

    public int size() {
        return rear - front;
    }

    private void mostrarEstado(String operacion) {
        System.out.println(operacion + " | arreglo=" + Arrays.toString(elementos)
                + ", front=" + front + ", rear=" + rear + ", size=" + size());
    }

    public static void main(String[] args) {
        Ejercicio06_ColaEnteros cola = new Ejercicio06_ColaEnteros(4);
        cola.mostrarEstado("Inicial");
        for (int valor : new int[]{11, 22, 33}) {
            cola.enqueue(valor);
            cola.mostrarEstado("enqueue(" + valor + ")");
        }
        System.out.println("Primer elemento FIFO: " + cola.front());
        System.out.println("dequeue() -> " + cola.dequeue());
        cola.mostrarEstado("Luego de extraer");
        cola.enqueue(44);
        cola.mostrarEstado("enqueue(44)");
        System.out.println("dequeue() -> " + cola.dequeue());
        cola.mostrarEstado("Estado final");
    }
}
