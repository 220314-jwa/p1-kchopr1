package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.exceptions.StatusNotFoundException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Event;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.utilities.ConnectionFactory;

public class ReimbursementDaoImpli implements ReimbursementDao {
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();
	private static DepartmentDaoImpli dep_dao=new DepartmentDaoImpli();
	private static EventDaoImpli evnt_dao=new EventDaoImpli();
	private static StatusDaoImpli status_dao=new StatusDaoImpli();
	private static EmployeeDaoImpli employee_dao=new EmployeeDaoImpli();
	Department dep;
	Event evnt;
	Status status;
	Employee emp;
	
	@Override
	public Reimbursement creat(Reimbursement st) throws InterruptedException {
		System.out.println("R creat");
		Thread.sleep(2000);
		try {
			Connection conn = connFactory.getConnection();
				String sql ="INSERT INTO public.reimbursement(cost, description, event_date, location, submited_date, employee_id, event_id, status_id)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				//employee_id, event_id, status_id
				
				pStmt.setInt(1, st.getCost());
				pStmt.setString(2, st.getDescription());
				pStmt.setDate(3, (Date) st.getEvent_date());
				pStmt.setString(4, st.getLocation());
				pStmt.setDate(5, (Date) st.getSubmited_date());
				pStmt.setInt(6, st.getEmployee().getId());
				pStmt.setInt(7, st.getEvent().getId());
				pStmt.setInt(8, st.getStatus().getId());
				st.setId(databaseOperation(pStmt,conn));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		emp=employee_dao.get(st.getEmployee().getId());
		st.setEmployee(emp);
		evnt=evnt_dao.get(st.getEvent().getId());
		st.setEvent(evnt);
		status=status_dao.get(st.getStatus().getId());
		st.setStatus(status);
		return st;
	}

	@Override
	public Reimbursement get(int id) throws InterruptedException {
		System.out.println("R get");
		Thread.sleep(2000);
		Reimbursement temp = null;
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.reimbursement Where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				if (resultSet.next()) {
					temp=new Reimbursement();
					temp.setId(resultSet.getInt("id"));
					temp.setDescription(resultSet.getString("description"));
					temp.setEvent_date(resultSet.getDate("event_date"));
					temp.setLocation(resultSet.getString("location"));
					temp.setSubmited_date(resultSet.getDate("submited_date"));
					temp.setCost(resultSet.getInt("cost"));
					emp=employee_dao.get(resultSet.getInt("employee_id"));
					temp.setEmployee(emp);
					evnt=evnt_dao.get(resultSet.getInt("event_id"));
					temp.setEvent(evnt);
					status=status_dao.get(resultSet.getInt("status_id"));
					temp.setStatus(status);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			}catch (Exception e) {
				return null;
			}
        if(temp==null)
        	new StatusNotFoundException("INVALID ID :"+id);
		return temp;
	}

	@Override
	public List<Reimbursement> getAll() throws InterruptedException {
		System.out.println("R getall");
		Thread.sleep(2000);
		List<Reimbursement> all=new ArrayList<Reimbursement>();
		try {
			Connection conn = connFactory.getConnection();
			String sql ="SELECT * FROM public.reimbursement ORDER BY id ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			try {
				conn.setAutoCommit(false);
				ResultSet resultSet = pStmt.executeQuery();
				while (resultSet.next()) {
					Reimbursement temp=new Reimbursement();
					temp.setId(resultSet.getInt("id"));
					temp.setDescription(resultSet.getString("description"));
					temp.setEvent_date(resultSet.getDate("event_date"));
					temp.setLocation(resultSet.getString("location"));
					temp.setSubmited_date(resultSet.getDate("submited_date"));
					temp.setCost(resultSet.getInt("cost"));
					emp=employee_dao.get(resultSet.getInt("employee_id"));
					temp.setEmployee(emp);
					evnt=evnt_dao.get(resultSet.getInt("event_id"));
					temp.setEvent(evnt);
					status=status_dao.get(resultSet.getInt("status_id"));
					temp.setStatus(status);
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
	public int update(Reimbursement st, int id) throws InterruptedException {
		System.out.println("R update");
		Thread.sleep(2000);
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="UPDATE public.reimbursement SET cost=?, description=?, event_date=?, location=?, submited_date=?, employee_id=?, event_id=?, status_id=? WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, st.getCost());
			pStmt.setString(2, st.getDescription());
			pStmt.setDate(3, (Date) st.getEvent_date());
			pStmt.setString(4, st.getLocation());
			pStmt.setDate(5, (Date) st.getSubmited_date());
			pStmt.setInt(6, st.getEmployee().getId());
			pStmt.setInt(7, st.getEvent().getId());
			pStmt.setInt(8, st.getStatus().getId());
			pStmt.setInt(9, id);
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
	public String delete(int id) throws InterruptedException {
		System.out.println("R delete");
		Thread.sleep(2000);
		int rowsUpdated = 0;
		Connection conn = connFactory.getConnection();
		try {
			String sql ="Delete from public.reimbursement  WHERE id=?";
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
