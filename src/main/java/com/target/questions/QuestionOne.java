package com.target.questions;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import static com.target.console.printers.PrinterConsole.println;

/**
 * Observe o trecho de código abaixo:
 * <code>
 * <pre>
 * int INDICE = 13, SOMA = 0, K = 0;
 * Enquanto K < INDICE faça { K = K + 1; SOMA = SOMA + K; }
 * Imprimir(SOMA);
 * </pre>
 * </code>
 * Ao final do processamento, qual será o valor da variável SOMA?
 */
public class QuestionOne {

    private QuestionOne() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static void execute() {
        int index = 13;
        int sum = sumOfTheFirstNIntegers(index);
        println("SOMA = %d", sum);
    }

    private static int sumOfTheFirstNIntegers(int n) {
        int sum = 0;
        int k = 0;

        while(k < n) {
            k += 1;
            sum += k;
        }

        return sum;
    }
}
