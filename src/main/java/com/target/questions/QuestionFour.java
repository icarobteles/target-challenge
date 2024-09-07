package com.target.questions;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;

import static com.target.console.printers.PrinterConsole.print;
import static com.target.console.printers.PrinterConsole.println;

/**
 * Dado o valor de faturamento mensal de uma distribuidora, detalhado por estado:
 * <ul>
 *  <li>SP – R$67.836,43</li>
 *  <li>RJ – R$36.678,66</li>
 *  <li>MG – R$29.229,88</li>
 *  <li>ES – R$27.165,48</li>
 *  <li>Outros – R$19.849,53</li>
 * </ul>
 * Escreva um programa na linguagem que desejar onde calcule o percentual de representação que cada estado teve dentro
 * do valor total mensal da distribuidora.
 */

public class QuestionFour {
    private enum State {
        SP, RJ, MG, ES, OTHERS("Outros");

        private final String value;

        State() {
            this.value = name();
        }

        State(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    private static final EnumMap<State, BigDecimal> monthlyBilling = new EnumMap<>(State.class);

    private QuestionFour() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static void execute() {
        populateMonthlyBilling();

        BigDecimal totalBilling = monthlyBilling.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

        println("Faturamento Mensal Total: R$%.2f", totalBilling);

        monthlyBilling.forEach((state, billing) -> {
            BigDecimal percentage = billing.divide(totalBilling, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            print("\t- %s: %.2f%%\n", state, percentage);
        });
    }

    private static void populateMonthlyBilling() {
        monthlyBilling.put(State.SP, new BigDecimal("67836.43"));
        monthlyBilling.put(State.RJ, new BigDecimal("36678.66"));
        monthlyBilling.put(State.MG, new BigDecimal("29229.88"));
        monthlyBilling.put(State.ES, new BigDecimal("27165.48"));
        monthlyBilling.put(State.OTHERS, new BigDecimal("19849.53"));
    }
}
