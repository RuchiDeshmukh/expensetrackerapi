package com.expensetrackerapi.entity;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(example = "1")
	private Long id;
	
	@Column(name="expense_name")
	@NotBlank(message = "Expense name must not be null.")
	@Size(min = 3, message = "Expense name must be at least 3 characters.")
	@Schema(example = "rent")
	private String name;
	
	@Schema(name = "Expense Description", example = "Rent for current month", required = false)
	private String description;
	
	@Column(name="expense_amount")
	@NotNull(message = "Expense amount should not be null.")
	@Schema(name = "Expense Amount", example = "22000", required = true)
	private Double amount;
	
	@NotBlank(message = "Category should not be null.")
	@Schema(example = "Bills")
	private String category;
	
	@NotNull(message = "Date must not be null.")
	@Schema(example = "new Date()")
	private Date date; 
	
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	@Schema(example = "new LocalDate()")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	@Schema(example = "new LocalDate()")
	private Timestamp updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	

}
