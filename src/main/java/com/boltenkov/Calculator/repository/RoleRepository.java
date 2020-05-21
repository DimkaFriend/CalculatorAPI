package com.boltenkov.Calculator.repository;

import com.boltenkov.Calculator.model.Role;
import com.boltenkov.Calculator.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}