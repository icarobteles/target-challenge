package com.target.console.readers;

import java.util.function.Predicate;

import static com.target.console.printers.PrinterConsole.print;

public class BooleanReaderConsole extends ReaderConsole {

    /**
     * Lê uma entrada do usuário que deve ser "S" ou "N", não diferencia maiúsculas de minúsculas.
     * Continua lendo até que a entrada seja válida.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return {@code true} se a entrada for "S" e {@code false} se a entrada for "N".
     */
    public static Boolean readStrictStringBoolean(Object prompt, Object... args) {
        Predicate<String> isYesOrNo = (String entry) -> entry.equalsIgnoreCase("S") || entry.equalsIgnoreCase("N");
        String validatedEntry = readEntry(() -> print(prompt, args), sc::next, isYesOrNo);
        return validatedEntry.equalsIgnoreCase("S");
    }

    /**
     * Lê uma entrada do usuário que deve ser "S" para {@code true} ou qualquer outra coisa para {@code false}.
     * Não diferencia maiúsculas de minúsculas.
     * Desconsidera espaços em branco no início e no final da entrada.
     * Continua lendo até que a entrada seja válida.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return {@code true} se a entrada for "S" e {@code false} se a entrada for diferente de "S".
     */
    public static Boolean readStringBoolean(Object prompt, Object... args) {
        String validatedEntry = readEntry(() -> print(prompt, args), sc::next);
        return validatedEntry.trim().equalsIgnoreCase("S");
    }

    /**
     * Lê uma entrada do usuário que deve ser 1 ou 0.
     * Continua lendo até que a entrada seja válida.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return {@code true} se a entrada for 1 e {@code false} se a entrada for 0.
     */
    public static Boolean readStrictIntBoolean(Object prompt, Object... args) {
        Predicate<Integer> isYesOrNo = (Integer entry) -> entry == 1 || entry == 0;
        Integer validatedEntry = readEntry(() -> print(prompt, args), sc::nextInt, isYesOrNo);
        return validatedEntry == 1;
    }

    /**
     * Lê uma entrada do usuário que deve ser 1 para {@code true} ou qualquer outro valor para {@code false}.
     * Desconsidera espaços em branco no início e no final da entrada.
     * Continua lendo até que a entrada seja válida.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return {@code true} se a entrada for 1 e {@code false} se a entrada for diferente de 1.
     */
    public static Boolean readIntBoolean(Object prompt, Object... args) {
        String validatedEntry = readEntry(() -> print(prompt, args), sc::next);
        return validatedEntry.trim().equals("1");
    }

}
