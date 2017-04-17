package com.mslc.poc.mongo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mslc.poc.mongo.model.Customer;
import com.mslc.poc.mongo.service.CustomerService;

@RestController
public class HomeController {

	private static Log _log = LogFactory.getLog(HomeController.class);
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/customers", method= RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomer(){
	
		_log.info("in get all customers...!");
		List<Customer> customerList = customerService.getAllCustomers();
		
		if(customerList == null){
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers/{id}", method= RequestMethod.GET)
	public String getCustomerById(@PathVariable("id") String id){
	
		_log.info("in get one customer...!");
		return null;
	}
	
	@RequestMapping(value="/customers", method= RequestMethod.POST)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		
		_log.info("in add new customer...!");
		Customer newCustomer = customerService.addCustomer(customer);
		
		if(newCustomer == null){
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers", method= RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		
		_log.info("in update customer...!");
		String id = customer.getId();
		Customer updateCustomer = customerService.getCustomer(id);
		
		if(updateCustomer == null){
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		updateCustomer.setFirstName(customer.getFirstName());
		updateCustomer.setLastName(customer.getLastName());
		customerService.updateCustomer(updateCustomer);
		
		return new ResponseEntity<Customer>(updateCustomer, HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String id){
		
		_log.info("in delete customer...!");
		customerService.deleteCustomer(id);
		
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
}
