package com.target.utils;

import com.google.gson.Gson;
import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class GenerateAnnualBillingJson {

    private static final Random random = new Random();
    private static final Gson gson = new Gson();

    private GenerateAnnualBillingJson() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static Map<String, Object> generate(int year, boolean overwrite) {
        EnumMap<Month, Map<String, BigDecimal>> annualBilling = generateAnnualBilling(year);
        String jsonContent = buildJson(annualBilling);
        return generateFile(year, jsonContent, overwrite);
    }

    public static boolean exists(int year) {
        return getFile(year).exists();
    }

    public static File getFile(int year) {
        String filename = buildFilename(year);
        return Path.of("src/main/resources/annual-billing", filename).toFile();
    }

    private static BigDecimal generateRandomBilling() {
        return BigDecimal.valueOf(10000 + (90000 * random.nextDouble())).setScale(2, RoundingMode.HALF_UP);
    }

    private static String buildFilename(int year) {
        return String.format("%d_annual_billing.json", year);
    }

    private static EnumMap<Month, Map<String, BigDecimal>> generateAnnualBilling(int year) {
        Month[] months = Month.values();

        LocalDate now = LocalDate.now();
        if (year == now.getYear()) {
            months = Arrays.copyOf(months, now.getMonthValue());
        }

        EnumMap<Month, Map<String, BigDecimal>> annualBilling = new EnumMap<>(Month.class);

        for (Month month : months) {
            int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
            Map<String, BigDecimal> monthlyBilling = new LinkedHashMap<>(daysInMonth);

            for (int day = 1; day <= daysInMonth; day++) {
                String dayString = String.format("%02d", day);
                BigDecimal billing = generateRandomBilling();

                monthlyBilling.put(dayString, billing);
            }

            annualBilling.put(month, monthlyBilling);
        }

        return annualBilling;
    }

    private static Map<String, Object> generateFile(int year, String content, boolean overwrite) {
        File file = getFile(year);
        boolean success = true;

        if (!file.exists() || overwrite) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(content);
            } catch (IOException e) {
                success = false;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("file", file);

        return response;
    }

    private static String buildJson(EnumMap<Month, Map<String, BigDecimal>> annualBilling) {
        return gson.toJson(annualBilling);
    }
}
