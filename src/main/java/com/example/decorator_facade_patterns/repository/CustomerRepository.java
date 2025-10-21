package com.example.decorator_facade_patterns.repository;

import com.example.decorator_facade_patterns.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
