package com.target.questions;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import static com.target.console.printers.PrinterConsole.println;
import static com.target.console.readers.StringReaderConsole.readText;

/**
 * Escreva um programa que inverta os caracteres de uma string.
 * <section>
 *  <strong>Importante</strong>
 *  <ul>
 *      <li>Essa string pode ser informada com qualquer entrada de sua preferência ou pode ser previamente definida no código;</li>
 *      <li>Evite usar funções prontas, como, por exemplo, reverse;</li>
 *  </ul>
 * </section>
 */

public class QuestionFive {
    private QuestionFive() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static void execute() {
        String text = readText("Digite um texto qualquer: ");
        StringBuilder reversedText = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText.append(text.charAt(i));
        }

        println("Texto invertido: %s", reversedText);
    }
}
