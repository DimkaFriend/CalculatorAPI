package com.boltenkov.Calculator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name="expressionMetrics")
public class ExpressionMetricsModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private long countOperands;

    private long countOperators;

    private long countParentheses;

    private ArrayList<String> listExpressionsParentheses;

    @OneToOne(mappedBy = "expressionMetrics")
    private CalculatorModel calculatorModel;

    public ExpressionMetricsModel() {
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

    public void setCountOperands(int countOperands) {
        this.countOperands = countOperands;
    }

    public long getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(int countOperators) {
        this.countOperators = countOperators;
    }

    public CalculatorModel getCalculatorModel() {
        return calculatorModel;
    }

    public void setCalculatorModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    public long getCountParentheses() {
        return countParentheses;
    }

    public void setCountParentheses(int countParentheses) {
        this.countParentheses = countParentheses;
    }

    public void createExpressionMetrics(CalculatorModel calculatorModel) {
        this.listExpressionsParentheses = this.listExpressionsParentheses(calculatorModel.getReadableExpression());
        this.countParentheses = this.computeExpressionParentheses();
        this.countOperands = this.computeOperands(calculatorModel);
        this.countOperators = this.computeOperators(calculatorModel);
    }

    public void setCountOperands(long countOperands) {
        this.countOperands = countOperands;
    }

    public void setCountOperators(long countOperators) {
        this.countOperators = countOperators;
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

    private ArrayList<String> listExpressionsParentheses(String expressions) {
        int i = 0;
        ArrayList<String> result = new ArrayList<String>();

        while (expressions.contains("(") & expressions.contains(")")) {

            String buff = expressions.substring(expressions.lastIndexOf('('), expressions.lastIndexOf(')') + 1);
            buff = buff.substring(buff.indexOf('('), buff.indexOf(')') + 1);
            expressions = expressions.replace(buff, "[" + (i++) + "]");

            result.add(buff);
            i++;

            if (expressions.contains("(") & expressions.contains(")")) {
                listExpressionsParentheses(expressions);
            }
        }

        return result;
    }

    private int computeExpressionParentheses() {
        return this.listExpressionsParentheses.size();
    }

    private long computeOperands(CalculatorModel calculatorModel) {
        return new StringBuilder(calculatorModel.getReadableExpression()).chars()
                .filter(x -> x != '(')
                .filter(x -> x != ')')
                .filter(x -> '0' < x)
                .filter(x -> x < '9')
                .count();
    }

    private long computeOperators(CalculatorModel calculatorModel) {
        return new StringBuilder(calculatorModel.getReadableExpression()).chars()
                .filter(x -> x != '(')
                .filter(x -> x != ')')
                .filter(x -> '0' > x)
                .filter(x -> x < '9')
                .count();
    }

    @Override
    public String toString() {
        return "ExpressionMetricsModel{" +
                "id=" + id +
                ", countOperands=" + countOperands +
                ", countOperators=" + countOperators +
                ", countParentheses=" + countParentheses +
                ", listExpressionsParentheses=" + listExpressionsParentheses +
                ", calculatorModel=" + calculatorModel.getId() +
                '}';
    }
}
