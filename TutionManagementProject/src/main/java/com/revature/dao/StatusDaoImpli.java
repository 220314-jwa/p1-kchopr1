package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.StatusNotFoundException;
import com.revature.model.Status;
import com.revature.utilities.ConnectionFactory;

public class StatusDaoImpli implements StatusDao {
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	public StatusDaoImpli() {
	}
	@Override
	public Status creat(Status st) {
		try {
		Connection conn = connFactory.getConnection();
			String sql ="INSERT INTO public.status(status_name, statusid)VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, st.getStatus_name());
			pStmt.setInt(2, st.getStatusid());
			st.setId(databaseOperation(pStmt,conn));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

	@Override
	public Status get(int id) {
		Status temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.status Where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Status();
					temp.setId(resultSet.getInt("id"));
					temp.setStatus_name(resultSet.getString("status_name"));
					temp.setStatusid(resultSet.getInt("statusid"));
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
	public List<Status> getAll() {
		List<Status> all=new ArrayList<Status>();
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.status ORDER BY id ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				while (resultSet.next()) {
					Status temp=new Status();
					temp.setId(resultSet.getInt("id"));
					temp.setStatus_name(resultSet.getString("status_name"));
					temp.setStatusid(resultSet.getInt("statusid"));
					all.add(temp);
				}
			}catch (Exception e) {
				return null;
			}
		return all;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int update(Status st, int id) {
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="UPDATE public.status SET status_name=?,statusid=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, st.getStatus_name());
			pStmt.setInt(2,st.getStatusid());
			pStmt.setInt(3,id);
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
			String sql ="Delete from public.status  WHERE id=?";
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
