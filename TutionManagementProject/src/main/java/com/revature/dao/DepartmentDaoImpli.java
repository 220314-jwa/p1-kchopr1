package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DepartmentNotFoundException;
import com.revature.exceptions.StatusNotFoundException;
import com.revature.model.Department;
import com.revature.model.Status;
import com.revature.utilities.ConnectionFactory;


public class DepartmentDaoImpli implements DepartmentDao {

	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	
	@Override
	public Department creat(Department st) {
		try {
			Connection conn = connFactory.getConnection();
				String sql ="INSERT INTO public.department(dep_head_id, dept_name, deptid)VALUES (?, ?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pStmt.setInt(1, st.getDep_head_id());
				pStmt.setString(2, st.getDept_name());
				pStmt.setInt(3, st.getDeptid());
				st.setId(databaseOperation(pStmt,conn));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return st;
	}

	@Override
	public Department get(int id) {
		
		Department temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.department Where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Department();
					temp.setId(resultSet.getInt("id"));
					temp.setDep_head_id((resultSet.getInt("dep_head_id")));
					temp.setDept_name(resultSet.getString("dept_name"));
					temp.setDeptid(resultSet.getInt("deptid"));
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
	public List<Department> getAll() {
		List<Department> all=new ArrayList<Department>();
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.department ORDER BY id ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				while (resultSet.next()) {
					Department temp=new Department();
					temp.setId(resultSet.getInt("id"));
					temp.setDep_head_id((resultSet.getInt("dep_head_id")));
					temp.setDept_name(resultSet.getString("dept_name"));
					temp.setDeptid(resultSet.getInt("deptid"));
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
	public int update(Department st, int id) {
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="UPDATE public.department SET dep_head_id=?, dept_name=?, deptid=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, st.getDep_head_id());
			pStmt.setString(2, st.getDept_name());
			pStmt.setInt(3, st.getDeptid());
			pStmt.setInt(4, id);
			conn.setAutoCommit(false); // for ACID (transaction management)
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
			String sql ="Delete from public.department  WHERE id=?";
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

