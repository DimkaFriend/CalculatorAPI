package com.boltenkov.Calculator.model;

import com.boltenkov.Calculator.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name ="calculator")
public class CalculatorModel extends UserDateAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Size(max = 300)
    private String readableExpression;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "expression_metrics_id", referencedColumnName = "id")
    private ExpressionMetricsModel expressionMetrics;

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

    public ExpressionMetricsModel getExpressionMetrics() {
        return expressionMetrics;
    }

    public void setExpressionMetrics(ExpressionMetricsModel expressionMetrics) {
        this.expressionMetrics = expressionMetrics;
    }

    @Override
    public String toString() {
        return "CalculatorModel{" +
                "id=" + id +
                ", readableExpression='" + readableExpression + '\'' +
                ", expressionMetrics=" + expressionMetrics +
                '}';
    }
}
