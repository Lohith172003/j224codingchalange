package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.CustomerDao;
import com.hexaware.dao.LoanDao;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Loan;

public class LoanController {
    private LoanDao loanDao = new LoanDao();
    private CustomerDao customerDao = new CustomerDao();
    private static Scanner scanner = new Scanner(System.in);

 

    
    public Customer getCustomer(int cid)
    {
		return customerDao.getCustomer(cid);
    	
    }
    
}
