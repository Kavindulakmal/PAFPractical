package com.paf10;
import java.sql.*;

public class User {

	//Connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf",	"root", "");
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}

	//Insert
	public String insertuser(String fname, String lname, String email,String address, String username,String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into customer(`id`,`fname`,`lname`,`email`,`address`,`username`,`password`) values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, username);
			preparedStmt.setString(7, password);

			//execute the statement
			preparedStmt.execute();
			con.close();

			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read
	public String readUser()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table  class='table table-dark table-striped'><tr><th>fName</th>"
					+"<th>lname</th><th>email</th>"
					+ "<th>address</th>"
					+ "<th>username</th>"
					+ "<th>password</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String userID = Integer.toString(rs.getInt("id"));
				String firstName = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String username = rs.getString("user_name");
				String password = rs.getString("password");

				// Add a row into the HTML table
				output += "<tr><td>" + firstName + "</td>";
				output += "<td>" + lastname + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='" + userID + "'></td>"
						+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + userID + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the HTML table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update
	public String updateUser(int id, String fname, String lname, String email, String address, String username,String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "update customer set `first_name`=?,`last_name`=?,`email`=?,`address`=?,`user_name`=?,`password`=? where `id`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, username);
			preparedStmt.setString(6, password);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newResearch = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Delete
	public String removeuser(int id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "delete from user where `id`=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newResearch = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
