/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una clase en Java llamada ColaCircular que resuelva el problema del desperdicio de memoria en arreglos mediante la reutilización de espacios liberados. La clase debe utilizar un arreglo de tamaño fijo e implementar los métodos enqueue, dequeue, front, isEmpty, isFull y size. Incluí comentarios teóricos exhaustivos en el código que expliquen la matemática del anillo infinito utilizando el operador módulo % mediante la fórmula (posicion + 1) % capacidad, justificando cómo esta expresión permite que los punteros front y rear vuelvan al índice 0 al alcanzar el límite físico del arreglo. Explicá también cómo se detectan de forma unívoca los estados de cola vacía (count == 0 o coincidencia neutral) y cola llena (count == capacidad), evitando ambigüedades entre ambos estados. Diseñá un método main que llene la cola, desencole algunos elementos y encole nuevos valores, demostrando en consola la reutilización efectiva de las posiciones iniciales del arreglo."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio10_ColaCircular {
    private final int[] elementos;
    // Como en toda cola FIFO, el primero en entrar es el primero en salir.
    // front apunta al próximo valor FIFO que sale; rear apunta al próximo hueco
    // de inserción. count distingue inequívocamente vacío y lleno aunque ambos
    // puedan coincidir en índices tras una vuelta completa.
    private int front = 0;
    private int rear = 0;
    private int count = 0;

    public Ejercicio10_ColaCircular(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        elementos = new int[capacidad];
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: la cola circular está llena.");
        }
        elementos[rear] = valor;
        // Módulo convierte los índices lineales en posiciones de un anillo:
        // al llegar a capacidad, (posición + 1) % capacidad vuelve al índice 0.
        rear = (rear + 1) % elementos.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: no hay elementos para desencolar.");
        }
        int valor = elementos[front];
        front = (front + 1) % elementos.length;
        count--;
        return valor;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: la cola está vacía.");
        }
        return elementos[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == elementos.length;
    }

    public int size() {
        return count;
    }

    private void mostrarEstado(String accion) {
        System.out.println(accion + " | arreglo=" + Arrays.toString(elementos)
                + ", front=" + front + ", rear=" + rear + ", count=" + count);
    }

    public static void main(String[] args) {
        Ejercicio10_ColaCircular cola = new Ejercicio10_ColaCircular(5);
        for (int valor : new int[]{10, 20, 30, 40, 50}) {
            cola.enqueue(valor);
        }
        cola.mostrarEstado("Cola llena");
        System.out.println("Se desencola: " + cola.dequeue());
        System.out.println("Se desencola: " + cola.dequeue());
        cola.mostrarEstado("Luego de liberar dos posiciones");
        cola.enqueue(60);
        cola.mostrarEstado("enqueue(60), reutiliza índice inicial");
        cola.enqueue(70);
        cola.mostrarEstado("enqueue(70), rear vuelve a cero");
        while (!cola.isEmpty()) {
            System.out.println("Sale FIFO: " + cola.dequeue());
        }
        cola.mostrarEstado("Cola vacía");
    }
}
