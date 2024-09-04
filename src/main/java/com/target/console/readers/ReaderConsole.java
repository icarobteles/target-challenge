package com.target.console.readers;

import com.target.console.exceptions.InvalidEntryException;
import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.target.console.printers.PrinterConsole.*;

/**
 * Classe utilitária para ler valores do usuário no console.
 */
public abstract class ReaderConsole {

    protected static final Scanner sc = ReaderConsoleScanner.getInstance();

    protected ReaderConsole() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    /**
     * Valida uma entrada em relação a um conjunto de entradas válidas.
     *
     * @param entry        Entrada a ser validada.
     * @param validEntries Conjunto de entradas válidas.
     * @return {@code true} se a entrada é válida, {@code false} caso contrário.
     */
    protected static <T> boolean validateEntry(T entry, Set<T> validEntries) {
        return entry != null && validEntries.contains(entry);
    }

    /**
     * Exibe uma mensagem de entrada inválida.
     */
    protected static void onInvalidEntry() {
        clear();
        println("Entrada inválida. Tente novamente.");
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja do tipo esperado.
     *
     * @param onPrintPrompt Função que exibe a mensagem de entrada.
     *                      <p>Exemplo: {@code () -> println("Digite um número inteiro: ")}</p>
     * @param onReadEntry   Função que lê a entrada.
     *                      <p>Exemplo: {@code sc::nextInt}</p>
     * @return A entrada lida e validada.
     */
    protected static <T> T readEntry(Runnable onPrintPrompt, Supplier<T> onReadEntry) {
        T entry = null;

        while(true) {

            onPrintPrompt.run();

            try {
                entry = onReadEntry.get();
                break;
            } catch (Exception e) {
                onInvalidEntry();
            }

            sc.nextLine(); // Consumir a quebra de linha

        }

        return entry;
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja válida conforme a função de validação.
     *
     * @param onPrintPrompt    Função que exibe a mensagem de entrada.
     *                         <p>Exemplo: {@code () -> println("Digite um número inteiro: ")}</p>
     * @param onReadEntry      Função que lê a entrada.
     *                         <p>Exemplo: {@code sc::nextInt}</p>
     * @param onValidateEntry  Função que valida a entrada.
     *                         <p>Exemplo: {@code (Integer entry) -> entry > 0}</p>
     * @return A entrada lida e validada.
     */
    protected static <T> T readEntry(Runnable onPrintPrompt, Supplier<T> onReadEntry, Predicate<T> onValidateEntry) {
        T entry = null;

        while(true) {

            onPrintPrompt.run();

            try {
                entry = onReadEntry.get();
                if (!onValidateEntry.test(entry)) throw new InvalidEntryException();
                break;
            } catch (Exception e) {
                onInvalidEntry();
            }

            sc.nextLine(); // Consumir a quebra de linha

        }

        return entry;
    }

    /**
     * Lê uma entrada do usuário e continua lendo até que a entrada seja válida conforme o conjunto de entradas válidas.
     *
     * @param onPrintPrompt   Função que exibe a mensagem de entrada.
     *                        <p>Exemplo: {@code () -> println("Digite um número inteiro: ")}</p>
     * @param onReadEntry     Função que lê a entrada.
     *                        <p>Exemplo: {@code sc::nextInt}</p>
     * @param validEntries    Conjunto de entradas válidas.
     *                        <p>Exemplo: {@code Set.of(1, 2, 3)}</p>
     * @return A entrada lida e validada.
     */
    protected static <T> T readEntry(Runnable onPrintPrompt, Supplier<T> onReadEntry, Set<T> validEntries) {
        return readEntry(onPrintPrompt, onReadEntry, (T entry) -> validateEntry(entry, validEntries));
    }

}
