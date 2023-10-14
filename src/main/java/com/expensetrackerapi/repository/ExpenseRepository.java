package com.expensetrackerapi.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetrackerapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{

	Page<Expense> findByCategory(String category, Pageable page);
	
	//select * from expenses where keyword LIKE '%keyword%'
	Page<Expense> findByNameContaining(String keyword, Pageable Page);
	
	//select * from expenses where date BETWEEN 'startDate' AND 'endDate' 
	Page<Expense> findByDateBetween(Date startEnd, Date endDate,Pageable page);
}
