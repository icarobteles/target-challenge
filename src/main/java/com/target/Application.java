package com.target;

import com.target.console.readers.ReaderConsoleScanner;
import com.target.questions.*;

import java.util.Locale;
import java.util.Set;

import static com.target.console.printers.PrinterConsole.*;
import static com.target.console.readers.NumberReaderConsole.readInt;

public class Application {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        Integer question;

        while (true) {
            question = readInt(Set.of(1, 2, 3, 4, 5, 0), "Digite o número da questão que deseja executar (0 para sair): ");

            switch (question) {
                case 1 -> questionOne();
                case 2 -> questionTwo();
                case 3 -> questionThree();
                case 4 -> questionFour();
                case 5 -> questionFive();
                default -> end();
            }

            printbl();
        }
    }

    private static void questionOne() {
        clear();
        QuestionOne.execute();
    }

    private static void questionTwo() {
        clear();
        QuestionTwo.execute();
    }

    private static void questionThree() {
        clear();
        QuestionThree.execute();
    }

    private static void questionFour() {
        clear();
        QuestionFour.execute();
    }

    private static void questionFive() {
        clear();
        QuestionFive.execute();
    }

    private static void end() {
        clear();
        println("Fim da execução");
        ReaderConsoleScanner.close();
        System.exit(0);
    }
}
