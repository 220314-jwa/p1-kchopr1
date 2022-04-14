package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.StatusNotFoundException;
import com.revature.model.Department;
import com.revature.model.Event;
import com.revature.utilities.ConnectionFactory;


public class EventDaoImpli implements EventDao {
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	@Override
	public Event creat(Event st) {
		try {
			Connection conn = connFactory.getConnection();
				String sql ="INSERT INTO public.events(event_id, event_type)VALUES (?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pStmt.setInt(1, st.getEvent_id());
				pStmt.setString(2, st.getEvent_type());
				st.setId(databaseOperation(pStmt,conn));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return st;
	}

	@Override
	public Event get(int id) {
		Event temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.events Where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Event();
					temp.setId(resultSet.getInt("id"));
					temp.setEvent_id((resultSet.getInt("event_id")));
					temp.setEvent_type(resultSet.getString("event_type"));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        if(temp==null)
        	new StatusNotFoundException("INVALID ID :"+id);
		return temp;
	}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Event> getAll() {
		System.out.println("Event getAll");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Event> all=new ArrayList<Event>();
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.events ORDER BY id ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				while (resultSet.next()) {
					Event temp=new Event();
					temp.setId(resultSet.getInt("id"));
					temp.setEvent_id((resultSet.getInt("event_id")));
					temp.setEvent_type(resultSet.getString("event_type"));
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
	public int update(Event st, int id) {
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="UPDATE public.events SET event_id=?, event_type=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, st.getEvent_id());
			pStmt.setString(2, st.getEvent_type());
			pStmt.setInt(3, id);
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
			String sql ="Delete from public.events  WHERE id=?";
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
