package com.tap.foods;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
	
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/food_delivery";
	static String username="root";
	static String password="Ihvss@786";
	static Statement stmt;
	static PreparedStatement pstmt;
	static ResultSet result;
	
	
	static String insert="insert into user(userid,username,password,email,address,role) values(?,?,?,?,?,?)";
	static String fetchAll="select * from user";
	static String fetchSpecific="select * from user where userid=?";
	static String updatePwd="update user set password=? where username=?";
	static String dele="delete from user where userid=?";

	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	static void insertUser() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.print("enter the username ");
			String uname=sc.nextLine();
			System.out.print("enter the password ");
			String pas=sc.nextLine();
			System.out.print("enter the email ");
			String email=sc.nextLine();
			System.out.print("enter the address ");
			String address=sc.nextLine();
			System.out.print("enter the role ");
			String role=sc.nextLine();
			System.out.println("enter the id ");
			int id=sc.nextInt();
			pstmt=con.prepareStatement(insert);
			pstmt.setInt(1, id);
			pstmt.setString(2, uname);
			pstmt.setString(3, pas);
			pstmt.setString(4, email);
			pstmt.setString(5, address);
			pstmt.setString(6, role);
			int x=pstmt.executeUpdate();
			System.out.println(x+" "+"row/s updated");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	static void selectAll() {
		try {
			stmt=con.createStatement();
			 result=stmt.executeQuery(fetchAll);
			 while(result.next()) {
				 System.out.println(result.getInt(1)+" "+
						 			result.getString(2)+" "+
						 			result.getString(3)+" "+
						 			result.getString(4)+" "+
						 			result.getString(5)
						 			);
			 }
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	static void specific(int userid) {
		
		try {
			pstmt=con.prepareStatement(fetchSpecific);
			pstmt.setInt(1,userid);
			result=pstmt.executeQuery();
			if(result.next()) {
				 System.out.println(result.getInt(1)+" "+
						 			result.getString(2)+" "+
						 			result.getString(3)+" "+
						 			result.getString(4)+" "+
						 			result.getString(5)+" "+
						 			result.getString(6)
						 			);
			 }
			 
			else {
				System.out.println("no results  found");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	static void updateUser( String password,String username) {
		try {
			pstmt=con.prepareStatement(updatePwd);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			int x=pstmt.executeUpdate();
			System.out.println(x+" "+"row/s updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	static void deleteUser(int userid) {
		try {
			pstmt=con.prepareStatement(dele);
			pstmt.setInt(1, userid);
			
			int x=pstmt.executeUpdate();
			System.out.println(x+" "+"row/s updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void disp() {
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		String st;
		  
			do {
				System.out.println("enter the option \n 1.new user \n 2.to fetch the details \n 3.to fetch specific \n 4.update password \n 5.delete the entry ");
				int n=sc.nextInt();
				if(n<=5) {
					if(n==1) {
						insertUser();
					}
					else if(n==2) {
						selectAll();
					}
					else if(n==3) {
						System.out.println("enter the id to fetch");
						int id2=sc.nextInt();
					
						specific(id2);
					}
					
					else if(n==4) {
						System.out.println("enter the username");
						sc.nextLine();
						String u=sc.nextLine();
						System.out.println("enter the password");
						String p=sc.nextLine();
						updateUser(p, u);
					}
					else {
						System.out.println("enter the id to delete");
						int id1=sc.nextInt();
						deleteUser(id1);
					}
					
				}
				else {
					System.out.println("enter the correct input");
				
				}
				System.out.println("do you want to continue? Y/N");
				sc.nextLine();
				st=sc.nextLine();
				
				
			}while(st.equalsIgnoreCase("y"));
			
		
		
		
	}

}
