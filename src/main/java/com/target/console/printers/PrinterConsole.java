package com.target.console.printers;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

/**
 * Classe utilitária para imprimir mensagens no console.
 */
public class PrinterConsole {

    private PrinterConsole() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    /**
     * Imprime uma mensagem formatada no console.
     *
     * @param obj  O objeto a ser formatado e impresso.
     * @param args Os argumentos a serem usados na formatação.
     */
    public static void print(Object obj, Object... args) {
        System.out.printf(obj.toString(), args);
    }

    /**
     * Imprime uma mensagem formatada seguida por uma nova linha no console.
     *
     * @param obj  O objeto a ser formatado e impresso.
     * @param args Os argumentos a serem usados na formatação.
     */
    public static void println(Object obj, Object... args) {
        String message = obj.toString() + "%n";
        System.out.printf(message, args);
    }

    /**
     * Imprime uma linha em branco no console.
     */
    public static void printbl() {
        System.out.println();
    }

    /**
     * Limpa o console.
     */
    public static void clear() {
        printbl();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
