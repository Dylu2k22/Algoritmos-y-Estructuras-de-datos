/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 1
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una clase en Java llamada PilaEnteros que implemente una estructura de datos de pila con capacidad fija mediante un arreglo interno de enteros int[]. La clase debe exponer públicamente los métodos push(int x) para apilar, pop() para desapilar y retornar el elemento de la cima, peek() para consultar el elemento superior sin removerlo, isEmpty() para verificar si la pila está vacía, isFull() para comprobar si alcanzó su capacidad máxima y size() para devolver la cantidad actual de elementos almacenados. Incluí comentarios teóricos extensos en el código explicando que el índice top actúa como un puntero de control que rastrea la cima de la pila, fundamentando por qué debe inicializarse obligatoriamente en -1 (dado que el índice 0 representa una celda válida cargada). Explicá también qué ocurre en los casos límite de desbordamiento por arriba (Overflow al intentar hacer push cuando isFull() es verdadero) y por abajo (Underflow al ejecutar pop o peek cuando isEmpty() es verdadero), manejando estas situaciones mediante excepciones o mensajes de error apropiados. Agregá una clase principal con un método main que pruebe todas las operaciones y muestre el comportamiento por consola."
 * ============================================================================
 */
public class Ejercicio01_PilaEnteros {
    private final int[] elementos;
    // top apunta a la cima. -1 significa que aún no hay ninguna celda ocupada;
    // iniciar en 0 confundiría una pila vacía con una que contiene un elemento.
    private int top = -1;

    public Ejercicio01_PilaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        elementos = new int[capacidad];
    }

    public void push(int x) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: no hay espacio en la pila.");
        }
        elementos[++top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: no se puede desapilar una pila vacía.");
        }
        return elementos[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: la pila no tiene cima.");
        }
        return elementos[top];
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

    public static void main(String[] args) {
        Ejercicio01_PilaEnteros pila = new Ejercicio01_PilaEnteros(3);
        System.out.println("Vacía al inicio: " + pila.isEmpty());
        pila.push(10);
        pila.push(20);
        pila.push(30);
        System.out.println("Cima: " + pila.peek() + ", tamaño: " + pila.size());
        System.out.println("Llena: " + pila.isFull());
        try {
            pila.push(40);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
        while (!pila.isEmpty()) {
            System.out.println("Pop: " + pila.pop());
        }
        try {
            pila.pop();
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
