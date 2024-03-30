package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.CustomerDao;
import com.hexaware.entity.Customer;

public class CustomerController implements CustomerControllerInterface {

	CustomerDao cd = new CustomerDao();
	static Scanner sc = new Scanner(System.in);
	@Override
	public void addCustomer() {
		
		Customer c = new Customer();
		
		System.out.print("Enter your name : ");
		c.setName(sc.next());

		System.out.print("Enter your email : ");
		c.setEmailAddress(null);

		System.out.print("Enter your Address : ");
		c.setAddress(sc.next());
		
		System.out.print("Enter your Phone Number : ");
		c.setPhoneNumber(sc.next());
		
		
		
		
	}

	@Override
	public Customer getCustomer(int cid) {
		
			return cd.getCustomer(cid);
		
	}

	
 
}
