package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class BankingOperation {
	 private static final int NULL = 0;
	 
	    static Connection con = DatabaseConnection.getConnection();
	    
	    public static boolean createAccount(String name, int passCode) // create account function
	    {
	        try {
	            // validation
	            if (name == "" || passCode == NULL) {
	                System.out.println("Please Enter The Details");
	                return false;
	            }
	            // query
	            Statement st = con.createStatement();
	             
	 
	            // Execution
	            if (st.executeUpdate("insert into customerDetails(cname,balance,pass_code) values('"+ name + "',1000," + passCode + ")") == 1) {
	                System.out.println(name+ ", Account Created Successfully");
	                                   
	                return true;
	            }
	            
	        }
	        catch (SQLIntegrityConstraintViolationException e) {
	            System.out.println("Username Not Available!");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    public static boolean loginAccount(String name, int passCode) // login method
	    {
	        try {
	            // validation
	            if (name == "" || passCode == NULL) {
	                System.out.println("Please Enter The Details");
	                return false;
	            }
	            // query
	            
	                  
	            PreparedStatement st= con.prepareStatement("select * from customer where cname='"+ name + "' and pass_code=" + passCode);
	                
	            ResultSet rs = st.executeQuery();
	            // Execution
	            BufferedReader sc = new BufferedReader(
	                new InputStreamReader(System.in));
	 
	            if (rs.next()) {
	                // after login menu driven interface method
	 
	                int ch = 5;
	                int amt = 0;
	                int senderAc = rs.getInt("ac_no");
	                ;
	                int receiveAc;
	                while (true) {
	                    try {
	                        System.out.println(
	                            "Hallo, "
	                            + rs.getString("cname"));
	                        System.out.println(
	                            "1)Transfer Money");
	                        System.out.println("2)View Balance");
	                        System.out.println("5)LogOut");
	 
	                        System.out.print("Enter Choice:");
	                        ch = Integer.parseInt(
	                            sc.readLine());
	                        if (ch == 1) {
	                            System.out.print(
	                                "Enter Receiver  A/c No:");
	                            receiveAc = Integer.parseInt(
	                                sc.readLine());
	                            System.out.print(
	                                "Enter Amount:");
	                            amt = Integer.parseInt(
	                                sc.readLine());
	 
	                            if (BankingOperation.transferMoney(senderAc, receiveAc,amt)) {
	                                System.out.println("MSG : Transaction  Successfully!\n");
	                                    
	                            }
	                            else {
	                                System.out.println(
	                                    "ERR :  Transaction Failed\n");
	                            }
	                        }
	                        else if (ch == 2) {
	 
	                            BankingOperation.getBalance(
	                                senderAc);
	                        }
	                        else if (ch == 5) {
	                            break;
	                        }
	                        else {
	                            System.out.println("Err :Invalid input!\n");
	                                
	                        }
	                    }
	                    catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	            else {
	                return false;
	            }
	            // return
	            return true;
	        }
	        catch (SQLIntegrityConstraintViolationException e) {
	            System.out.println("Username Not Available!");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    public static void
	    getBalance(int acNo) // fetch balance method
	    {
	        try {
	 
	            // query
	            
	            PreparedStatement st
	                = con.prepareStatement("select * from customer where ac_no="+ acNo);
	 
	            ResultSet rs = st.executeQuery("select * from customer where ac_no="+ acNo);
	            System.out.println(
	                "-----------------------------------------------------------");
	            System.out.printf("%12s %10s %10s\n",
	                              "Account No", "Name",
	                              "Balance");
	 
	            // Execution
	 
	            while (rs.next()) {
	                System.out.printf("%12d %10s %10d.00\n",
	                                  rs.getInt("ac_no"),
	                                  rs.getString("cname"),
	                                  rs.getInt("balance"));
	            }
	            System.out.println(
	                "-----------------------------------------------------------\n");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static boolean transferMoney(int sender_ac,
	                                        int reveiver_ac,
	                                        int amount)
	        throws SQLException // transfer money method
	    {
	        // validation
	        if (reveiver_ac == NULL || amount == NULL) {
	            System.out.println("All Field Required!");
	            return false;
	        }
	        try {
	            con.setAutoCommit(false);
	            
	            PreparedStatement ps= con.prepareStatement("select * from customer where ac_no="+ sender_ac);
	            ResultSet rs = ps.executeQuery();
	 
	            if (rs.next()) {
	                if (rs.getInt("balance") < amount) {
	                    System.out.println(
	                        "Insufficient Balance!");
	                    return false;
	                }
	            }
	 
	            Statement st = con.createStatement();
	 
	            // debit
	            con.setSavepoint();
	 
	            
	            if (st.executeUpdate("update customer set balance=balance-"+ amount + " where ac_no=" + sender_ac) == 1) {
	                System.out.println("Amount Debited!");
	            }
	 
	            // credit
	            
	            st.executeUpdate("update customer set balance=balance+"+ amount + " where ac_no=" + reveiver_ac);
	 
	            con.commit();
	            return true;
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            con.rollback();
	        }
	        // return
	        return false;
	    }
	}

