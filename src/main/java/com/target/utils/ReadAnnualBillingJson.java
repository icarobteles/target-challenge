package com.target.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

import static com.target.console.printers.PrinterConsole.println;

public class ReadAnnualBillingJson {

    private static final Gson gson = new Gson();

    private ReadAnnualBillingJson() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static Map<Month, Map<String, BigDecimal>> read(File file) {
        EnumMap<Month, Map<String, BigDecimal>> json = null;

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<EnumMap<Month, Map<String, BigDecimal>>>() {}.getType();
            json = gson.fromJson(reader, type);
        } catch (Exception e) {
            println("Erro ao ler o arquivo '%s': %s", file.getName(), e.getMessage());
        }

        return json;
    }

}
