package com.boltenkov.Calculator.view.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CalculatorRequest {
    @NotBlank
    @Size(max = 300)
    String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
