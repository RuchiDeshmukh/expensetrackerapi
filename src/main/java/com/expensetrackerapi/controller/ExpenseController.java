package com.expensetrackerapi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expensetrackerapi.entity.Expense;
import com.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		
		return expenseService.getAllExpenses(page).toList();
	}
	
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		
		return expenseService.getExpenseById(id);
	}
	@DeleteMapping("/expenses")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteExpenseById(@RequestParam Long id) {
		
		expenseService.deleteExpenseById(id);
	}
	
	@PostMapping("/expenses")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpenseDetails(expense);
	}
	
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
		return expenseService.updateExpenseDetails(id,expense);
	}
	
	@GetMapping("/expenses/category")
	public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
		return expenseService.readByCategory(category, page);
	}
	
	@GetMapping("/expenses/name")
	public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page){
		return expenseService.readByName(keyword, page);
	}
	
	@GetMapping("/expenses/date")
	public List<Expense> getExpensesByDates(@RequestParam(required = false) java.sql.Date startDate,@RequestParam(required = false) java.sql.Date endDate ,Pageable page){
		return expenseService.readByDate(startDate, endDate, page);
	}
	
	@GetMapping("/")
	public List<Expense> createExpenses() {
		
		List<Expense> expenseList = new ArrayList<>();
		Expense expense1 = new Expense();
		expense1.setName("water bill");
		expense1.setCategory("bill");
		expense1.setAmount(500.00);
		expense1.setDate(new Date(System.currentTimeMillis()));
		expenseList.add(expense1);
		
		Expense expense2 = new Expense();
		expense2.setName("electricity bill");
		expense2.setCategory("bill");
		expense2.setAmount(900.00);
		expense2.setDate(new Date(System.currentTimeMillis()));
		expenseList.add(expense2);
		
		Expense expense3 = new Expense();
		expense3.setName("onions");
		expense3.setCategory("vegetables");
		expense3.setAmount(200.00);
		expense3.setDate(new Date(System.currentTimeMillis()));
		expenseList.add(expense3);
		
		Expense expense4 = new Expense();
		expense4.setName("mobile");
		expense4.setCategory("recharge");
		expense4.setAmount(1000.00);
		expense4.setDate(new Date(System.currentTimeMillis()));
		expenseList.add(expense4);
		
		Expense expense5 = new Expense();
		expense5.setName("tomato");
		expense5.setCategory("vegetables");
		expense5.setAmount(100.00);
		expense5.setDate(new Date(System.currentTimeMillis()));
		expenseList.add(expense5);
		
		
		return expenseService.save(expenseList);
	}
}
