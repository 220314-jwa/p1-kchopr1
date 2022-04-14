package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.model.Employee;
import com.revature.model.Login;
import com.revature.utilities.ConnectionFactory;

public class UtliiiesDao {
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

	public Employee login(Login login) {
		Employee temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.employee Where username=? and password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,login.getUserName());
			pStmt.setString(2,login.getPassWord());
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Employee();
					temp.setId(resultSet.getInt("id"));
					temp.setFirstname(resultSet.getString("firstname"));
					temp.setLastname(resultSet.getString("lastname"));
					temp.setPassword(resultSet.getString("password"));
					temp.setRole(resultSet.getString("role"));
					temp.setUsername(resultSet.getString("username"));
				}
				
			}catch (Exception e) {
				return null;
			}
		
			}catch (Exception e) {
				return null;
			}
		return temp;
	}
		
}
