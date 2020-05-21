package com.boltenkov.Calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boltenkov.Calculator.model.CalculatorModel;

import java.util.Optional;

@Repository
public interface CalculatorRepository extends JpaRepository<CalculatorModel, Long> {
    Optional<CalculatorModel> findById(long id);

}
