package com.boltenkov.Calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boltenkov.Calculator.model.ExpressionMetricsModel;

import java.util.Optional;

@Repository
public interface ExpressionMetricsRepository extends JpaRepository<ExpressionMetricsModel, Long> {
    Optional<ExpressionMetricsModel> findById(long id);
}
