package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.entity.CarLoan;
import com.hexaware.util.DBUtil;

public class CarLoanDao extends LoanDao {

	public boolean createCarLoan(CarLoan carLoan) {
		String loanSql = "INSERT INTO Loan (CustomerID,principalAmount, interestRate,LoanType, loanTerm) VALUES (?, ?, ?, ?, ?)";
		String carLoanSql = "INSERT INTO CarLoan (loanId, carModel, carValue) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement loanStmt = null;
		PreparedStatement carLoanStmt = null;

		try {
			conn = DBUtil.getDBConn();

			// Insert loan details
			loanStmt = conn.prepareStatement(loanSql, Statement.RETURN_GENERATED_KEYS);
			loanStmt.setInt(1,carLoan.getCustomerID());
			loanStmt.setDouble(2, carLoan.getPrincipalAmount());
			loanStmt.setDouble(3, carLoan.getInterestRate());
			loanStmt.setInt(4, 1);
			loanStmt.setInt(5, carLoan.getLoanTerm());
			int rowsInserted = loanStmt.executeUpdate();

			if (rowsInserted == 0) {
				throw new SQLException("Creating loan failed, no rows affected.");
			}

			// Retrieve the generated loan ID
			try (ResultSet generatedKeys = loanStmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					carLoan.setLoanID(generatedKeys.getInt(1));
					System.out.println("your loan is placed and your loan id is : " + generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating loan failed, no ID obtained.");
				}
			}

			// Insert car loan specific details
			carLoanStmt = conn.prepareStatement(carLoanSql);
			carLoanStmt.setInt(1, carLoan.getLoanID());
			carLoanStmt.setString(2, carLoan.getCarModel());
			carLoanStmt.setDouble(3, carLoan.getCarValue());
			carLoanStmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {

			try {
				if (loanStmt != null) {
					loanStmt.close();
				}
				if (carLoanStmt != null) {
					carLoanStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean deleteCarLoan(int loanId) {
		return false;
		// Implementation
	}

	public void updateCarLoan(int loanId, String newCarModel, double newCarValue) {
		// Implementation
	}

	public CarLoan getCarLoanById(int loanId) {
		return null;
		// Implementation
	}

	// Other methods as needed...
}
