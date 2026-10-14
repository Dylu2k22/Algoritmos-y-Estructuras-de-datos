/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 2: Recursividad
 * Ejercicio: 5
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Construí en Java una función recursiva con firma void que imprima en consola un conteo regresivo desde un número entero n hasta 0. Explicá mediante comentarios el significado de que la función sea void, señalar que su objetivo es ejecutar un efecto secundario (imprimir por pantalla) y no retornar un valor computado. Definí el caso base para cuando n < 0, momento en el que se ejecuta un return; explícito para finalizar la función. Detallá que la aproximación al caso base se logra decrementando el parámetro mediante conteoRegresivo(n - 1). Justificá teóricamente qué sucedería si se utilizara n + 1, advirtiendo que el valor se alejaría de la condición de parada generando una recursión infinita y provocando un error de desbordamiento de pila StackOverflowError. Proba el programa invocando la función con n = 5 y n = 0 desde el método main."
 * ============================================================================
 */

public class Ejercicio05_ConteoRegresivo {

    /**
     * void indica que no se retorna un valor: el propósito es imprimir,
     * un efecto secundario. El parámetro se acerca a la condición de parada.
     */
    public static void conteoRegresivo(int n) {
        // Caso base: al ser negativo, termina explícitamente sin otra llamada.
        if (n < 0) {
            return;
        }

        System.out.println(n);
        // Caso recursivo: n - 1 eventualmente será menor que cero.
        // Usar n + 1 lo alejaría de la parada y causaría StackOverflowError.
        conteoRegresivo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("Conteo desde 5:");
        conteoRegresivo(5);
        System.out.println("Conteo desde 0:");
        conteoRegresivo(0);
    }
}
