/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 4: Pilas y Colas con Arreglos Estáticos
 * Ejercicio: 7
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Crea un programa en Java que simule un sistema de gestión de turnos para la atención al cliente en una oficina, representando cada turno con el nombre de la persona o un identificador numérico dentro de una Cola lineal basada en un arreglo. El programa debe permitir registrar la llegada de un cliente (encolar), atender al cliente que corresponda (desencolar) y consultar qué persona se encuentra primera en la fila para ser atendida. Añadí comentarios teóricos extensos que justifiquen por qué la atención en una oficina se rige de forma justa e innegociable bajo la regla FIFO (First In, First Out) y no LIFO, argumentando que el primer cliente en llegar debe ser strictly el primero en recibir atención para garantizar la equidad del servicio. Programá en el método main una simulación realista con la llegada consecutiva de varios clientes, la atención progresiva de los mismos y la consulta periódica del frente de la fila."
 * ============================================================================
 */
public class Ejercicio07_SistemaTurnos {
    /*
     * La cola atiende FIFO: el primer cliente que llega (first in) será el
     * primero atendido (first out). LIFO invertiría el orden y dejaría esperando
     * indefinidamente a quienes llegaron antes. front señala a quién toca;
     * rear marca la próxima celda de llegada en esta cola lineal.
     * rear == capacidad produce Overflow y front == rear es la fila vacía;
     * atender o consultar en ese estado produce Underflow.
     */
    private final String[] turnos;
    private int front = 0;
    private int rear = 0;

    public Ejercicio07_SistemaTurnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        turnos = new String[capacidad];
    }

    public void registrar(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente debe tener un nombre.");
        }
        if (rear == turnos.length) {
            throw new IllegalStateException("Overflow: no hay más espacio al final de la fila.");
        }
        turnos[rear++] = cliente;
    }

    public String atender() {
        if (front == rear) {
            throw new IllegalStateException("Underflow: no hay clientes en espera.");
        }
        String cliente = turnos[front];
        turnos[front++] = null;
        return cliente;
    }

    public String proximo() {
        if (front == rear) {
            throw new IllegalStateException("Underflow: la fila está vacía.");
        }
        return turnos[front];
    }

    public boolean estaVacia() {
        return front == rear;
    }

    public static void main(String[] args) {
        Ejercicio07_SistemaTurnos fila = new Ejercicio07_SistemaTurnos(6);
        for (String cliente : new String[]{"Lucía", "Marcos", "Sofía", "Tomás"}) {
            fila.registrar(cliente);
            System.out.println("Llega " + cliente + "; próximo: " + fila.proximo());
        }
        while (!fila.estaVacia()) {
            System.out.println("Se atiende a " + fila.atender()
                    + (fila.estaVacia() ? "; fila vacía." : "; sigue " + fila.proximo()));
        }
    }
}
