package com.boltenkov.Calculator.service;

import com.boltenkov.Calculator.exception.ResourceNotFoundException;
import com.boltenkov.Calculator.model.CalculatorModel;
import com.boltenkov.Calculator.model.ExpressionMetricsModel;
import com.boltenkov.Calculator.repository.CalculatorRepository;
import com.boltenkov.Calculator.util.ModelMapper;
import com.boltenkov.Calculator.view.Request.CalculatorRequest;
import com.boltenkov.Calculator.view.Response.CalculatorResponse;
import com.boltenkov.Calculator.view.Response.PagedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired
    private CalculatorRepository calculatorRepository;

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public CalculatorModel createCalculatorExpression(CalculatorRequest calculatorRequest){
        CalculatorModel calculatorModel = new CalculatorModel();
        calculatorModel.setReadableExpression(calculatorRequest.getExpression());
        calculatorModel.setExpressionMetrics(new ExpressionMetricsModel());
        calculatorModel.getExpressionMetrics().createExpressionMetrics(calculatorModel);

        return calculatorRepository.save(calculatorModel);
    }

    public CalculatorResponse getCalculatorExpressionById(long Id) {
        CalculatorModel calculatorModel = calculatorRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("Calculator expression", "id", Id));

        return ModelMapper.map(calculatorModel);
    }

    public PagedResponse<CalculatorResponse> getCalculatorExpressionAll(){


        return new PagedResponse<CalculatorResponse>(ModelMapper.mapToListCalculatorResponses(calculatorRepository.findAll()));
    }
}
