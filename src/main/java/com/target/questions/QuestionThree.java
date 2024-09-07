package com.target.questions;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;
import com.target.utils.GenerateAnnualBillingJson;
import com.target.utils.ReadAnnualBillingJson;

import java.io.File;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import static com.target.console.printers.PrinterConsole.printbl;
import static com.target.console.printers.PrinterConsole.println;
import static com.target.console.readers.BooleanReaderConsole.readStrictStringBoolean;
import static com.target.console.readers.NumberReaderConsole.readInt;

/**
 * Dado um vetor que guarda o valor de faturamento diário de uma distribuidora, faça um programa, na linguagem que desejar, que calcule e retorne:
 * <ul>
 *     <li>O menor valor de faturamento ocorrido em um dia do mês;</li>
 *     <li>O maior valor de faturamento ocorrido em um dia do mês;</li>
 *     <li>Número de dias no mês em que o valor de faturamento diário foi superior à média mensal.</li>
 * </ul>
 */
public class QuestionThree {

    private QuestionThree() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static void execute() {

        int currentYear = Year.now().getValue();
        int year = readInt(entry -> entry >= 1980 && entry <= currentYear, "Digite o ano (1980 - %d) de consulta do faturamento: ", currentYear);

        boolean annualBillingExists = GenerateAnnualBillingJson.exists(year);
        boolean overwrite = false;

        if (annualBillingExists) {
            println("O ano de %d já foi consultado anteriormente.", year);
            overwrite = readStrictStringBoolean("Deseja atualizar os dados? (S/N): ");
        }

        Map<String, Object> response = GenerateAnnualBillingJson.generate(year, overwrite);

        boolean success = (boolean) response.get("success");

        if (!success) {
            println("Erro ao processar o arquivo de faturamento anual para o ano de %d.", year);
            return;
        }

        printbl();

        int month = readInt(entry -> entry >= 1 && entry <= 12, "Digite o mês (1 - 12) de consulta do faturamento: ");

        File file = (File) response.get("file");

        Map<Month, Map<String, BigDecimal>> json = ReadAnnualBillingJson.read(file);

        try {
            Map<String, BigDecimal> monthlyBilling = json.get(Month.of(month));
            println("Exibindo dados referentes ao faturamento de %s/%d.", Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()), year);
            println("\t- Menor valor de faturamento: R$ %.2f",  getLowestBillingValue(monthlyBilling));
            println("\t- Maior valor de faturamento: R$ %.2f", getHighestBillingValue(monthlyBilling));
            println("\t- Número de dias no mês em que o valor de faturamento diário foi superior à média mensal: %d", getDaysAboveMonthlyAverage(monthlyBilling));
        } catch (Exception e) {
            println("Não foram encontrados dados de faturamento para o mês de %s de %d.", Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault()), year);
        }

        printbl();
        println("Você pode verificar os dados completos no arquivo '%s'.", file.getAbsolutePath());
    }

    private static BigDecimal getLowestBillingValue(Map<String, BigDecimal> monthlyBilling) {
        return monthlyBilling.values().stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    private static BigDecimal getHighestBillingValue(Map<String, BigDecimal> monthlyBilling) {
        return monthlyBilling.values().stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    private static int getDaysAboveMonthlyAverage(Map<String, BigDecimal> monthlyBilling) {
        BigDecimal average = monthlyBilling.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(monthlyBilling.size()), RoundingMode.HALF_UP);
        return (int) monthlyBilling.values().stream().filter(value -> value.compareTo(average) > 0).count();
    }
}
