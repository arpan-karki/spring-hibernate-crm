package com.arpankarki.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpankarki.spring.dao.CustomerDAO;
import com.arpankarki.spring.domain.Customer;


//Good practice to manage transactions here in service layer 
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
		
	@Transactional
	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customerDAO.getCustomers();
	}
	
	@Transactional
	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDAO.saveCustomer(customer);
	}
	
	
	@Override
	@Transactional()
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Customer customer = customerDAO.getCustomer(id);
		return customer;
	}
	
	@Transactional
	@Override
	public void deleteCustomer(int id) {
		customerDAO.delete(id);
		
	}

}
