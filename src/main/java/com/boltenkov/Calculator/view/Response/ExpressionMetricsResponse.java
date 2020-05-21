package com.boltenkov.Calculator.view.Response;

import com.boltenkov.Calculator.model.ExpressionMetricsModel;

import java.util.ArrayList;

public class ExpressionMetricsResponse {
    private long id;

    private long countOperands;

    private long countOperators;

    private long countParentheses;

    private ArrayList<String> listExpressionsParentheses;

    public ExpressionMetricsResponse(ExpressionMetricsModel expressionMetricsModel) {
        this.id = expressionMetricsModel.getId();
        this.countOperands = expressionMetricsModel.getCountOperands();
        this.countOperators = expressionMetricsModel.getCountOperators();
        this.countParentheses = expressionMetricsModel.getCountParentheses();
        this.listExpressionsParentheses = expressionMetricsModel.getListExpressionsParentheses();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCountOperands() {
        return countOperands;
    }

    public void setCountOperands(long countOperands) {
        this.countOperands = countOperands;
    }

    public long getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(long countOperators) {
        this.countOperators = countOperators;
    }

    public long getCountParentheses() {
        return countParentheses;
    }

    public void setCountParentheses(long countParentheses) {
        this.countParentheses = countParentheses;
    }

    public ArrayList<String> getListExpressionsParentheses() {
        return listExpressionsParentheses;
    }

    public void setListExpressionsParentheses(ArrayList<String> listExpressionsParentheses) {
        this.listExpressionsParentheses = listExpressionsParentheses;
    }

    @Override
    public String toString() {
        return "ExpressionMetricsResponse{" +
                "id=" + id +
                ", countOperands=" + countOperands +
                ", countOperators=" + countOperators +
                ", countParentheses=" + countParentheses +
                ", listExpressionsParentheses=" + listExpressionsParentheses +
                '}';
    }
}
