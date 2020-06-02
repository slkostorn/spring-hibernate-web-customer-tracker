package com.kostornychenko.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.InitBinder;

import com.kostornychenko.entity.Customer;
import com.kostornychenko.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		LOGGER.info("Entering listCustomers method");
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		LOGGER.info("Executed listCustomers method");
        
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@Valid Model theModel,@ModelAttribute("customer") Customer customer) {
		
		LOGGER.info("Entering showFormForAdd method");
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		LOGGER.info("Executing showFormForAdd method");
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid@ModelAttribute("customer") Customer theCustomer,BindingResult bindingResult) {
		
		LOGGER.info("Entering saveCustomer method");
		if(bindingResult.hasErrors()) {
			LOGGER.info("BindingResult has errors");
			return "customer-form";
		}
			
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		LOGGER.info("Executing saveCustomer method");

		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		LOGGER.info("Entering showFormForUpdate method");
		
		// get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		LOGGER.info("Executing showFormForUpdate method");
		
		// send over to our form		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		LOGGER.info("Entering delete method");
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		LOGGER.info("Executing delete method");
		
		return "redirect:/customer/list";
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
	    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	    
	    LOGGER.info("Executing InitBinder method");
	}
}










