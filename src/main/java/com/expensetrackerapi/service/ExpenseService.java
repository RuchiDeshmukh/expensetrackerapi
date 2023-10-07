package com.expensetrackerapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	
	Page<Expense> getAllExpenses(Pageable page);

	List<Expense> save(List<Expense> expenseList);
	
	Expense getExpenseById(Long id);
	
	void deleteExpenseById(Long id);

	Expense saveExpenseDetails(Expense expense);
	
	Expense updateExpenseDetails(Long id, Expense expense);
	

}
