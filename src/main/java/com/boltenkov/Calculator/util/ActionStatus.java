package com.boltenkov.Calculator.util;

public class ActionStatus {
    public final boolean isValid;
    public final String messageOfValidations;

    public ActionStatus(boolean isValid, String messageOfValidations) {
        this.isValid=isValid;
        this.messageOfValidations=messageOfValidations;
    }
}
