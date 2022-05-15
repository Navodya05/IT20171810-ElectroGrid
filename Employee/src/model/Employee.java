package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/management", "root", "y1o2h3a4n5@#");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertEmployee(String empName, String empAddress, String empEmail, String empContactno) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into EMPLOYEEDATA(`ID`,`EMPNAME`,`EMPADDRESS`,`EMPEMAIL`,`EMPCONTACTNO`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, empName);
			preparedStmt.setString(3, empAddress);
			preparedStmt.setString(4, empEmail);
			preparedStmt.setString(5, empContactno);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newEmployee = readEmployee();
			output = "Inserted successfully";
			output = "{\"status\":\"success\", \"data\": \"" +newEmployee + "\"}";
		} catch (Exception e) {
			output = "Error while inserting the Employee.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Employee.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readEmployee() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table\"><tr><th>EMPName</th><th>Address</th><th>Email</th><th>Contact Number</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from EMPLOYEEDATA";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String empName = rs.getString("empName");
				String empAddress = rs.getString("empAddress");
				String empEmail = rs.getString("empEmail");
				String empContactno = rs.getString("empContactno");

				output += "<tr><td><input id='hideMPIDUpdate' name='hideMPIDUpdate' type='hidden' value='"+id+"'>"+empName+"</td>"; 
				output += "<td>" + empAddress + "</td>";
				output += "<td>" + empEmail + "</td>";
				output += "<td>" + empContactno + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>";
			
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateEmployee(String id, String empName, String empAddress, String empEmail, String empContactno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE EMPLOYEEDATA SET EMPNAME=?,EMPADDRESS=?,EMPEMAIL=?,EMPCONTACTNO=? WHERE ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, empName);
			preparedStmt.setString(2, empAddress);
			preparedStmt.setString(3, empEmail);
			preparedStmt.setString(4, empContactno);
			preparedStmt.setInt(5, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newEmployee = readEmployee();
			output = "Updated successfully";
			output = "{\"status\":\"success\", \"data\": \"" +newEmployee + "\"}";		
		} catch (Exception e) {
			output = "Error while updating the Employee.";
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Employee.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteEmployee(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from EMPLOYEEDATA where ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newEmployee = readEmployee();
			output = "Deleted successfully";
			output = "{\"status\":\"success\", \"data\": \"" +newEmployee + "\"}";
		} catch (Exception e) {
			output = "Error while deleting the Employee.";
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Employee.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
