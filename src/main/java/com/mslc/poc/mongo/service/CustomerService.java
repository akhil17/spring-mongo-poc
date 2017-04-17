package com.mslc.poc.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mslc.poc.mongo.model.Customer;
import com.mslc.poc.mongo.repository.CustomerRepository;

@Service
public class CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer){
		
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(String id){
		
		customerRepository.delete(id);
	}
	
	public List<Customer> getAllCustomers(){
		
		return customerRepository.findAll();
	}
	
	public Customer getCustomer(String id){
		
		return customerRepository.findOne(id);
	}
	
	public Customer updateCustomer(Customer customer){
		
		return customerRepository.save(customer);
	}
}
