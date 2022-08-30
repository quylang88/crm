package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.connection.JDBCConnection;
import com.java.model.Account;

public class AccountDAO {
	
	public ArrayList<Account> getAll() {
		ArrayList<Account> models = new  ArrayList<>();
		String query = "SELECT * FROM servlet_crm.accounts";
		
		try(Connection conn = JDBCConnection .getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Account model = new Account();
				model.setId(res.getInt("account_id"));
				model.setFullname(res.getString("full_name"));
				model.setEmail(res.getString("email"));
				model.setPassword(res.getString("password"));
				model.setPhone(res.getString("phone"));
				model.setAddress(res.getString("address"));
				
				models.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return models;
	}
	
	public int insert(Account model) {
		String query = "INSERT INTO servlet_crm.accounts (full_name, email, password, phone, address) VALUES (?,?,?,?,?)";
		
		try(Connection conn = JDBCConnection .getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, model.getFullname());
			statement.setString(2, model.getEmail());
			statement.setString(3, model.getPassword());
			statement.setString(4, model.getPhone());
			statement.setString(5, model.getAddress());
			
			return statement.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
