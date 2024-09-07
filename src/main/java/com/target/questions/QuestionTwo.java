package com.target.questions;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import static com.target.console.printers.PrinterConsole.println;
import static com.target.console.readers.NumberReaderConsole.readInt;

/**
 * Dado a sequência de Fibonacci, onde se inicia por 0 e 1 e o próximo valor sempre será a soma dos 2 valores anteriores
 * (exemplo: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...), escreva um programa na linguagem que desejar onde, informado um número,
 * ele calcule a sequência de Fibonacci e retorne uma mensagem avisando se o número informado pertence ou não a sequência.
 */
public class QuestionTwo {

    private QuestionTwo() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static void execute() {
        Integer n = readInt(entry -> entry >= 0, "Digite um número inteiro positivo para verificar se ele pertence à sequência de Fibonacci: ");
        println("O número %d%s pertence à sequência de Fibonacci", n, belongsToFibonacciSequence(n) ? "" : " não");
    }

    private static boolean belongsToFibonacciSequence(int n) {
        int a = 0;
        int b = 1;
        int c = 0;

        while(c < n) {
            c = a + b;
            a = b;
            b = c;
        }

        return c == n;
    }
}
