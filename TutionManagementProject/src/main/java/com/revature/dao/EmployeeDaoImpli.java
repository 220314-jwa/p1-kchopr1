package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.StatusNotFoundException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.utilities.ConnectionFactory;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDaoImpli implements EmployeeDao {
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	private static DepartmentDaoImpli dep_dao=new DepartmentDaoImpli();
	Department dep;
	@Override
	public Employee creat(Employee st) {
		try {
			Connection conn = connFactory.getConnection();
				String sql ="INSERT INTO public.employee(firstname, lastname, password, role, username, department_id)VALUES (?, ?,?,?, ?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pStmt.setString(1, st.getFirstname());
				pStmt.setString(2, st.getLastname());
				pStmt.setString(3, st.getPassword());
				pStmt.setString(4, st.getRole());
				pStmt.setString(5, st.getUsername());
				
				System.out.println(st);
				dep=st.getDepartment();
				System.out.println(dep.getId());
				
				pStmt.setInt(6, dep.getId());
				
				st.setId(databaseOperation(pStmt,conn));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		dep=dep_dao.get(st.getDepartment().getId());
		st.setDepartment(dep);
		return st;
	}

	@Override
	public Employee get(int id) {
		
		Employee temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.employee Where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Employee();
					//firstname, lastname, password, role, username, department_id
					temp.setId(resultSet.getInt("id"));
					temp.setFirstname(resultSet.getString("firstname"));
					temp.setLastname(resultSet.getString("lastname"));
					temp.setPassword(resultSet.getString("password"));
					temp.setRole(resultSet.getString("role"));
					temp.setUsername(resultSet.getString("username"));
					int department_id=resultSet.getInt("department_id");
					Department dep=dep_dao.get(department_id);
					temp.setDepartment(dep);
				}
				
			}catch (Exception e) {
				return null;
			}
        if(temp==null)
        	new StatusNotFoundException("INVALID ID :"+id);
		return temp;
	}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> all=new ArrayList<Employee>();
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.employee ORDER BY id ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				while (resultSet.next()) {
					Employee temp=new Employee();
					temp.setId(resultSet.getInt("id"));
					temp.setFirstname(resultSet.getString("firstname"));
					temp.setLastname(resultSet.getString("lastname"));
					temp.setPassword(resultSet.getString("password"));
					temp.setRole(resultSet.getString("role"));
					temp.setUsername(resultSet.getString("username"));
					int department_id=resultSet.getInt("department_id");
					Department dep=dep_dao.get(department_id);
					temp.setDepartment(dep);
					all.add(temp);
				}
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		return all;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int update(Employee st, int id) {
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="UPDATE public.employee SET firstname=?, lastname=?, password=?, role=?, username=?, department_id=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, st.getFirstname());
			pStmt.setString(2, st.getLastname());
			pStmt.setString(3, st.getPassword());
			pStmt.setString(4, st.getRole());
			pStmt.setString(5, st.getUsername());
			
			Department dep=st.getDepartment();
			System.out.println(dep.getId());
			pStmt.setInt(6, dep.getId());
			
			pStmt.setInt(7, id);
			conn.setAutoCommit(false);
			rowsUpdated = pStmt.executeUpdate();
			if (rowsUpdated==1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsUpdated;
	}

	@Override
	public String delete(int id) {
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="Delete from public.employee  WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			conn.setAutoCommit(false); // for ACID (transaction management)
			rowsUpdated = pStmt.executeUpdate();
			if (rowsUpdated==1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Deleted "+id +" Error";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Deleted Succussfully :"+id;

	}
	
	private int databaseOperation(PreparedStatement pStmt, Connection conn) {
		int generatedId = 0;
		try {
			conn.setAutoCommit(false);
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();

			if (resultSet.next()) {
				generatedId = resultSet.getInt(1);
				System.out.println("generatedId : " + generatedId);
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("generatedId : " + generatedId);
		return generatedId;
	}
}
