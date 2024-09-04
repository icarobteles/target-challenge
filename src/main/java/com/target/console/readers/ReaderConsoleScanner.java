package com.target.console.readers;

import com.target.console.exceptions.UtilityClassCannotBeInstantiatedException;

import java.util.Scanner;

public class ReaderConsoleScanner {
    private static Scanner instance;

    private ReaderConsoleScanner() {
        throw new UtilityClassCannotBeInstantiatedException();
    }

    public static synchronized Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }

        return instance;
    }

    public static synchronized void close() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

    public static synchronized boolean isClosed() {
        return instance == null || instance.ioException() != null;
    }
}
