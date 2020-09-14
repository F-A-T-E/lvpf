package com.java.service;

import java.util.List;

import com.java.controller.CustomerController;
import com.java.domain.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public void save(Customer customer);

	public Customer findById(Integer id);

	public void delete(Integer[] id);
}
