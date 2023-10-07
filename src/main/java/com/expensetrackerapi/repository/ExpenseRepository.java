package com.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetrackerapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{

}
