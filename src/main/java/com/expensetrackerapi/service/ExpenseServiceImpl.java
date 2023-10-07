package com.expensetrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expensetrackerapi.entity.Expense;
import com.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepository.findAll(page);
	}

	@Override
	public List<Expense> save(List<Expense> expenseList) {
		return expenseRepository.saveAll(expenseList);
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		
		if(expense.isPresent()) {
			return expense.get();
		}
		
		throw new RuntimeException("Expense is not found for the id "+id);
		
	}

	@Override
	public void deleteExpenseById(Long id) {
		expenseRepository.deleteById(id);
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setAmount(expense.getAmount()!= null ? expense.getAmount() : existingExpense.getAmount());
		
		return expenseRepository.save(existingExpense);
		
	}

}