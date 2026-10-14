/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 10
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Implementá en Java un sistema de ranking que reciba como datos de entrada un arreglo de objetos Jugador (cada uno con atributos nombre y puntaje), utilizando como prueba los datos: Jugador("Ana", 1200), Jugador("Pedro", 900) y Jugador("Lucia", 1500). El programa debe ordenar y mostrar por consola el ranking de jugadores de mayor a menor puntaje. Seleccioná el algoritmo de ordenamiento más conveniente entre los vistos y agregá comentarios teóricos que justifiquen explícitamente dicha elección, analizando factores como el tamaño reducido del conjunto de datos, la estabilidad del algoritmo para preservar órdenes previos en puntajes iguales y la eficiencia en la manipulación de objetos. Para verificar que el resultado sea correcto, el programa debe imprimir en pantalla la tabla de posiciones validando que 'Lucia' (1500) ocupe la primera posición y 'Pedro' (900) la última."
 * ============================================================================
 */
public class Ejercicio10_RankingPuntajes {
    private static class Jugador {
        private final String nombre;
        private final int puntaje;

        Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }
    }

    public static void main(String[] args) {
        Jugador[] jugadores = {
            new Jugador("Ana", 1200),
            new Jugador("Pedro", 900),
            new Jugador("Lucia", 1500)
        };
        // Para un conjunto tan pequeño, Insertion Sort es simple y eficiente.
        // Es estable porque solo desplaza puntajes estrictamente menores: ante
        // empates conserva el orden de entrada, y mueve referencias sin copiar objetos.
        insertionSortDescendente(jugadores);

        System.out.println("Posición | Jugador | Puntaje");
        System.out.println("----------------------------");
        for (int i = 0; i < jugadores.length; i++) {
            System.out.printf("%8d | %-7s | %7d%n", i + 1, jugadores[i].nombre, jugadores[i].puntaje);
        }
        if (!"Lucia".equals(jugadores[0].nombre) || jugadores[0].puntaje != 1500
                || !"Pedro".equals(jugadores[jugadores.length - 1].nombre)
                || jugadores[jugadores.length - 1].puntaje != 900) {
            throw new IllegalStateException("El ranking no coincide con los puntajes esperados.");
        }
    }

    private static void insertionSortDescendente(Jugador[] jugadores) {
        for (int i = 1; i < jugadores.length; i++) {
            Jugador actual = jugadores[i];
            int j = i - 1;
            while (j >= 0 && jugadores[j].puntaje < actual.puntaje) {
                jugadores[j + 1] = jugadores[j];
                j--;
            }
            jugadores[j + 1] = actual;
        }
    }
}
