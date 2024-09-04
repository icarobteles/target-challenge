package com.target.console.readers;

import java.util.Set;
import java.util.function.Predicate;

import static com.target.console.printers.PrinterConsole.print;

/**
 * Classe utilitária para leitura de valores numéricos inseridos pelo usuário no console.
 */
public class NumberReaderConsole extends ReaderConsole {

    /**
     * Lê uma entrada do usuário do tipo {@code Double} e continua lendo até que a entrada seja válida conforme a função de validação.
     *
     * @param onValidateEntry Função que valida a entrada.
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Double readDouble(Predicate<Double> onValidateEntry, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextDouble, onValidateEntry);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code Double} e continua lendo até que a entrada pertença ao conjunto de entradas válidas.
     *
     * @param validEntries Conjunto de entradas válidas.
     * @param prompt       Mensagem de entrada.
     * @param args         Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Double readDouble(Set<Double> validEntries, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextDouble, validEntries);
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja do tipo {@code Double}.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Double readDouble(Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextDouble);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code Integer} e continua lendo até que a entrada seja válida conforme a função de validação.
     *
     * @param onValidateEntry Função que valida a entrada.
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Integer readInt(Predicate<Integer> onValidateEntry, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextInt, onValidateEntry);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code Integer} e continua lendo até que a entrada pertença ao conjunto de entradas válidas.
     *
     * @param validEntries Conjunto de entradas válidas.
     * @param prompt       Mensagem de entrada.
     * @param args         Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Integer readInt(Set<Integer> validEntries, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextInt, validEntries);
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja do tipo {@code Integer}.
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static Integer readInt(Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::nextInt);
    }

}
