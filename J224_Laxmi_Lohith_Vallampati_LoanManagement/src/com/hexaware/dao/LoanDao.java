package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Loan;
import com.hexaware.entity.LoanStatus;
import com.hexaware.entity.LoanType;
import com.hexaware.exception.InvalidLoanException;
import com.hexaware.util.DBUtil;

public class LoanDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	


	public Loan getLoanById(int loanId) {
	    Loan loan =null;
	    con = DBUtil.getDBConn();

	    try {
	        ps = con.prepareStatement("SELECT * FROM Loan WHERE LoanID = ?");
	        ps.setInt(1, loanId);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            loan = new Loan();
	            loan.setLoanID(loanId);
	            loan.setCustomerID(rs.getInt(2));
	            loan.setPrincipalAmount(rs.getFloat(3));
	            loan.setInterestRate(rs.getFloat(4));
	            loan.setLoanTerm(rs.getInt(5));
	            loan.setLoanType(LoanType.valueOf(rs.getString(6)));
	            loan.setLoanStatus(LoanStatus.valueOf(rs.getString(7)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources();
	    }

	    return loan;
	}


	public void updateLoan(int loanId, String res) throws InvalidLoanException {
		con = DBUtil.getDBConn();
		try {
			ps = con.prepareStatement("Update Loan set LoanStatus = ? where LoanID =?");
			ps.setString(1, res);
			ps.setInt(2, loanId);
			int rows = ps.executeUpdate();
			if (rows < 1) {

				throw new InvalidLoanException("Loan Id not Found");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeResources();
		}

	}



	public List<Loan> getAllLoans() {
		con = DBUtil.getDBConn();
	    List<Loan> loans = new ArrayList<>();
	    Loan loan =null;
	    try {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM Loan");
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	           
	            loan = new Loan();
	            loan.setLoanID(rs.getInt(1));
	            loan.setCustomerID(rs.getInt(2));
	            loan.setPrincipalAmount(rs.getFloat(3));
	            loan.setInterestRate(rs.getFloat(4));
	            loan.setLoanTerm(rs.getInt(5));
	            loan.setLoanType(LoanType.valueOf(rs.getString(6)));
	            loan.setLoanStatus(LoanStatus.valueOf(rs.getString(7)));	
	            loans.add(loan);
	            
	     
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(); // Close resources such as PreparedStatement and ResultSet
	    }
	    return loans;
	}
	
	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
