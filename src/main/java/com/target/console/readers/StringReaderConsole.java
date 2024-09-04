package com.target.console.readers;

import java.util.Set;
import java.util.function.Predicate;

import static com.target.console.printers.PrinterConsole.print;

/**
 * Classe utilitária para leitura de strings inseridas pelo usuário no console.
 */
public class StringReaderConsole extends ReaderConsole {

    /**
     * Lê uma entrada do usuário do tipo {@code String} e continua lendo até que a entrada seja válida conforme a função de validação.
     * <p>Só considera a primeira palavra da entrada.</p>
     *
     * @param onValidateEntry Função que valida a entrada.
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readWord(Predicate<String> onValidateEntry, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next, onValidateEntry);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code String} e continua lendo até que a entrada pertença ao conjunto de entradas válidas.
     * <p>Só considera a primeira palavra da entrada.</p>
     *
     * @param validEntries Conjunto de entradas válidas.
     * @param prompt       Mensagem de entrada.
     * @param args         Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readWord(Set<String> validEntries, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next, validEntries);
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja do tipo {@code String}.
     * <p>Uma entrada é considerada válida se não for nula.</p>
     * <p>Só considera a primeira palavra da entrada.</p>
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readWord(Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code String} e continua lendo até que a entrada seja válida conforme a função de validação.
     *
     * @param onValidateEntry Função que valida a entrada.
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readText(Predicate<String> onValidateEntry, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next, onValidateEntry);
    }

    /**
     * Lê uma entrada do usuário do tipo {@code String} e continua lendo até que a entrada pertença ao conjunto de entradas válidas.
     *
     * @param validEntries Conjunto de entradas válidas.
     * @param prompt       Mensagem de entrada.
     * @param args         Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readText(Set<String> validEntries, Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next, validEntries);
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja do tipo {@code String}.
     * <p>Uma entrada é considerada válida se não for nula.</p>
     *
     * @param prompt          Mensagem de entrada.
     * @param args            Argumentos para formatação da mensagem.
     * @return A entrada lida e validada.
     */
    public static String readText(Object prompt, Object... args) {
        return readEntry(() -> print(prompt, args), sc::next);
    }

}
