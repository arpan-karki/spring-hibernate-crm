package com.arpankarki.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arpankarki.spring.dao.CustomerDAO;
import com.arpankarki.spring.domain.Customer;
import com.arpankarki.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/list")
	public String listCustomers(Model model) {		
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		return"list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		/**customerService.saveCustomer(customer);
		return "customer-saved";
		**/
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,Model model) {
		Customer customer= customerService.getCustomer(id);
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId")int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}


