package com.kostornychenko.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kostornychenko.entity.Customer;
import com.kostornychenko.springdemo.dao.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		LOGGER.info("Redirect method getCustomers from CustomerController to CustomerDAO");
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		LOGGER.info("Redirect method saveCustomer from CustomerController to CustomerDAO");
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		LOGGER.info("Redirect method getCustomer by id from CustomerController to CustomerDAO");
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		LOGGER.info("Redirect method deleteCustomer by id from CustomerController to CustomerDAO");;
		customerDAO.deleteCustomer(theId);
	}
}





