package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	 private Connection conn;
	 
	 public  DepartmentDaoJDBC(Connection conn) {
		 this.conn =conn;
	 }

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			   st = conn.prepareStatement(
						"INSERT INTO department "
								+ "(Name) VALUES  (?)", 
								Statement.RETURN_GENERATED_KEYS);
			   
			   st.setString(1,obj.getName());
			   int rowsAffected = st.executeUpdate();
			   if (rowsAffected > 0 ) {
				   ResultSet rs = st.getGeneratedKeys();
					while (rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}
					DB.closeResultSet(rs);
			   }
			   else {
					throw new DbException("Erro: nenhuma linha alterada");
			   }
			 
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			   st = conn.prepareStatement(
						"update department set " 
								+ "Name = ?  where Id = ?");
			   
			   st.setString(1,obj.getName());
			   st.setInt(2, obj.getId());
			  st.executeUpdate();
			  
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			   st = conn.prepareStatement(
						"delete from department where Id = ?");
			   st.setInt(1,id);
			   st.executeUpdate();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
		
	}

	@Override
	public Department findByid(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		   st = conn.prepareStatement(
				     "SELECT * from department " 
					 + "where Id = ?");
		    st.setInt(1, id);
		    rs = st.executeQuery();
		    if (rs.next()) {
		    	Department dep = instatiateDepartment(rs);
		    	return dep;
		    }
		    return null;
		  
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Department> FindAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		   st = conn.prepareStatement( "SELECT * from department ORDER BY Name");
		   
		    rs = st.executeQuery();
		    List<Department> list = new ArrayList<>();
		    while (rs.next()) {
		    	Department dep =  instatiateDepartment(rs);
		    	list.add(dep);
		    }
		    return list;
		  
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
    	dep.setName(rs.getString("Name"));
    	return dep;
	}
	
}
