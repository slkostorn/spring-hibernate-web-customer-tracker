package com.kostornychenko.springdemo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kostornychenko.entity.Customer;
import com.kostornychenko.springdemo.controller.CustomerController;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		LOGGER.info("Entering getCustomers from database method");
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		LOGGER.info("Getting Hibernate sessionFactory from getCustomers method");
		// create a query  ... sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		LOGGER.info("Executing  query for getting list of customers from database");
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		LOGGER.info("Entering saveCustomer to database method");
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		LOGGER.info("Getting Hibernate sessionFactory from saveCustomer method");
		
		// save/update the customer 
		currentSession.saveOrUpdate(theCustomer);
		LOGGER.info("Saving a new customer or update current in a database");
		
	}

	@Override
	public Customer getCustomer(int theId) {
		LOGGER.info("Entering getCustomer from database method");
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		LOGGER.info("Getting Hibernate sessionFactory from getCustomer by id method");
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		LOGGER.info("Getting customer from database by id");
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		LOGGER.info("Entering deleteCustomer from database method");
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		LOGGER.info("Getting Hibernate sessionFactory from deleteCustomer by id method");
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();	
		LOGGER.info("Executing  query for deleting customer from database");
	}

}











