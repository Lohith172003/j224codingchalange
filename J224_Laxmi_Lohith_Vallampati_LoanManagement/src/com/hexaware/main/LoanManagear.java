package com.hexaware.main;

import java.sql.SQLException;
import java.util.Scanner;
import com.hexaware.controller.ILoanRepository;
import com.hexaware.exception.InvalidLoanException;

public class LoanManagear {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException, InvalidLoanException {

		ILoanRepository ilr = new ILoanRepository();
		boolean exit = false;

		while (true) {

			System.out.println("1. Apply Loan");
			System.out.println("2. Intrest and Status");
			System.out.println("3. Financial");
			System.out.println("4. Loans");
			System.out.println("5.Exit");
			System.out.print("Enter your choice:");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("1.Car Loan 2. Home Loan");
				System.out.print("Enter your choice : ");
				int loan = sc.nextInt();

				switch (loan) {
				case 1:
					ilr.applyLoa(1);
					break;
				case 2:
					ilr.applyLoa(2);
					break;
				default:
					System.out.println("give correct choice..");
				}

				break;
			case 2:

				System.out.println("1.calculating interest 2. checking status");
				System.out.print("Enter your choice : ");
				int manage = sc.nextInt();

				switch (manage) {

				case 1:
					System.out.println("enter your loan id : ");
					System.out.println(ilr.calculateInterest(sc.nextInt()));
					break;
				case 2:
					System.out.println("enter your loan id : ");
					ilr.loanStatus(sc.nextInt());
					break;
				default:
					System.out.println("give correct choice..");
				}

				break;
			case 3:

				System.out.println("1.calculating EMI 2. loan repayment");
				System.out.print("Enter your choice : ");
				int finance = sc.nextInt();

				switch (finance) {
				case 1:
					System.out.println("enter your loan id : ");
					System.out.println(ilr.calculateEMI(sc.nextInt()));
					break;
				case 2:
					System.out.println("enter your loan id : ");
					int lid = sc.nextInt();
					System.out.println("enter the amount : ");
					float amount = sc.nextInt();
					ilr.loanRepayment(lid, amount);
					break;
				default:
					System.out.println("give correct choice..");
				}

				break;
			case 4:

				System.out.println("1.getting all loans 2. getting loan by ID");
				System.out.print("Enter your choice : ");
				int getLoan = sc.nextInt();

				switch (getLoan) {
				case 1:
					ilr.getAllLoan();
					break;
				case 2:
					System.out.println("entery loan Id : ");
					ilr.getLoanById(sc.nextInt());
					break;
				default:
					System.out.println("give correct choice..");
				}
				break;
			case 5:
				System.out.println("Thank you");
				exit = true;
				break;
			default:
				System.out.println("Invalid choice!");

			}

			if (exit)
				break;
		}
	}

}
