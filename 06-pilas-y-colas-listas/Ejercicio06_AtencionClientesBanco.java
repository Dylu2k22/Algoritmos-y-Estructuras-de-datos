/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico: Pilas y Colas con Listas Enlazadas
 * Ejercicio: 6
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java un sistema de atención al cliente para una entidad bancaria utilizando una cola enlazada de objetos Cliente (atributos: nombre, numeroTurno, motivoConsulta). El sistema debe proveer las funciones agregarCliente(Cliente c), atenderProximoCliente(), consultarProximo() e imprimirFila(). Añadí comentarios teóricos que justifiquen técnicamente por qué la Cola (FIFO) es la única estructura éticamente justa para la atención al público, garantizando el respeto estricto del orden de llegada. En el método main simula la llegada de varios clientes y su posterior atención en secuencia."
 *
 * AJUSTES REALIZADOS LUEGO DE LA PRIMERA RESPUESTA DE OPENCODE:
 * "Se verificó la compilación, se rechazan clientes y campos inválidos, y
 * consultar/atender una fila vacía comunica el error mediante excepción."
 * ============================================================================
 */
public class Ejercicio06_AtencionClientesBanco {
    /*
     * La cola enlazada garantiza la política de turnos FIFO: el primer cliente
     * que llega es el primero atendido, sin adelantar a quienes esperan. Esta
     * política es apropiada para la equidad del sistema siempre que no exista
     * una regla de prioridad previamente establecida. head representa al
     * próximo cliente, tail al último; alta y atención son O(1).
     */
    private static final class Cliente {
        private final String nombre;
        private final int numeroTurno;
        private final String motivoConsulta;

        private Cliente(String nombre, int numeroTurno, String motivoConsulta) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
            }
            if (numeroTurno <= 0) {
                throw new IllegalArgumentException("El número de turno debe ser positivo.");
            }
            if (motivoConsulta == null || motivoConsulta.trim().isEmpty()) {
                throw new IllegalArgumentException("El motivo de consulta es obligatorio.");
            }
            this.nombre = nombre;
            this.numeroTurno = numeroTurno;
            this.motivoConsulta = motivoConsulta;
        }

        @Override
        public String toString() {
            return "Turno " + numeroTurno + ": " + nombre + " ("
                    + motivoConsulta + ")";
        }
    }

    private static final class Nodo {
        private final Cliente cliente;
        private Nodo siguiente;

        private Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
    }

    private Nodo head;
    private Nodo tail;

    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null.");
        }
        Nodo nuevo = new Nodo(cliente);
        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        System.out.println("Se agregó a la fila: " + cliente);
    }

    public Cliente atenderProximoCliente() {
        if (head == null) {
            throw new IllegalStateException("No hay clientes para atender.");
        }
        Cliente atendido = head.cliente;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }
        System.out.println("Atendiendo: " + atendido);
        return atendido;
    }

    public Cliente consultarProximo() {
        if (head == null) {
            throw new IllegalStateException("No hay clientes en espera.");
        }
        return head.cliente;
    }

    public void imprimirFila() {
        System.out.print("Fila: ");
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            System.out.print("[" + actual.cliente + "]");
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Ejercicio06_AtencionClientesBanco banco = new Ejercicio06_AtencionClientesBanco();
        banco.agregarCliente(new Cliente("Ana", 1, "Depósito"));
        banco.agregarCliente(new Cliente("Bruno", 2, "Consulta de cuenta"));
        banco.agregarCliente(new Cliente("Carla", 3, "Actualización de datos"));
        banco.imprimirFila();
        System.out.println("Próximo: " + banco.consultarProximo());
        banco.atenderProximoCliente();
        banco.atenderProximoCliente();
        banco.imprimirFila();
        banco.atenderProximoCliente();
    }
}
