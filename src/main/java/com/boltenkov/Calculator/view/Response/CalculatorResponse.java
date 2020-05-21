package com.boltenkov.Calculator.view.Response;

public class CalculatorResponse {
    private long id;

    private String readableExpression;

    private ExpressionMetricsResponse expressionMetrics;

    public CalculatorResponse() {
    }

    public CalculatorResponse(long id, String readableExpression, ExpressionMetricsResponse expressionMetricsResponse) {
        this.id = id;
        this.readableExpression = readableExpression;
        this.expressionMetrics = expressionMetricsResponse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReadableExpression() {
        return readableExpression;
    }

    public void setReadableExpression(String readableExpression) {
        this.readableExpression = readableExpression;
    }

    public ExpressionMetricsResponse getExpressionMetrics() {
        return expressionMetrics;
    }

    public void setExpressionMetrics(ExpressionMetricsResponse expressionMetrics) {
        this.expressionMetrics = expressionMetrics;
    }

    @Override
    public String toString() {
        return "CalculatorResponse{" +
                "id=" + id +
                ", readableExpression='" + readableExpression + '\'' +
                ", expressionMetrics=" + expressionMetrics +
                '}';
    }
}
