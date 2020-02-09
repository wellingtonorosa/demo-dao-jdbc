package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
 private Connection conn;
 
 public  SellerDaoJDBC(Connection conn) {
	 this.conn =conn;
 }
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findByid(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		   st = conn.prepareStatement(
				     "SELECT seller.*,department.Name as Depname from seller" 
				     + " inner join department" 
					 + " on seller.id = seller.Id"
					 + " where seller.Id = ?");
		    st.setInt(1, id);
		    rs = st.executeQuery();
		    if (rs.next()) {
		    	Department dep = new Department();
		    	dep.setId(rs.getInt("DepartmentId"));
		    	dep.setName(rs.getString("DepName"));
		    	Seller obj = new Seller();
		    	obj.setId(rs.getInt("Id"));
		    	obj.setName(rs.getString("Name"));
		    	obj.setEmail(rs.getString("Email"));
		    	obj.setBaseSalary(rs.getDouble("BaseSalary"));
		    	obj.setBirthDate(rs.getDate("BirthDate"));
		    	obj.setDepartment(dep);
		    	return obj;
		    }
		    return null;
		  
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Seller> FindAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
