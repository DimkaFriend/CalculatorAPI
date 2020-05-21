package com.boltenkov.Calculator.controller;

import com.boltenkov.Calculator.model.CalculatorModel;
import com.boltenkov.Calculator.repository.CalculatorRepository;
import com.boltenkov.Calculator.service.CalculatorService;
import com.boltenkov.Calculator.view.Request.CalculatorRequest;
import com.boltenkov.Calculator.view.Response.ApiResponse;
import com.boltenkov.Calculator.view.Response.CalculatorResponse;
import com.boltenkov.Calculator.view.Response.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/expression")
public class CalculatorController {
    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private CalculatorService calculatorService;
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCalculatorExpression(@Valid @RequestBody CalculatorRequest calculatorRequest){
        System.out.println(calculatorRequest.getExpression());

        CalculatorModel calculatorModel = calculatorService.createCalculatorExpression(calculatorRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{Id}")
                .buildAndExpand(calculatorModel.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{id}")
    public CalculatorResponse getCalculatorExpressionById(@PathVariable long id) {
        return calculatorService.getCalculatorExpressionById(id);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public PagedResponse<CalculatorResponse> getCalculatorExpressionAll(){
        return calculatorService.getCalculatorExpressionAll();
    }
}
