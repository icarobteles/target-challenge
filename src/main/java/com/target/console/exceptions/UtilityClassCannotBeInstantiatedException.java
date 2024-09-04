package com.target.console.exceptions;

public class UtilityClassCannotBeInstantiatedException extends RuntimeException {
    public UtilityClassCannotBeInstantiatedException() {
            super("Não é possível instanciar uma classe utilitária");
        }
}
