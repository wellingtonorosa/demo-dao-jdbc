package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 

	SellerDao sellerDao = DaoFactory.createSellerDao();
	System.out.println("---- Teste 1 - FindByid----");
	Seller seller = sellerDao.findByid(3);
	System.out.println(seller);
	System.out.println("\n---- Teste 2 - seller findByDepartment----");
	Department department = new Department(2,null);
	List<Seller> list = sellerDao.findByDepartment(department);
    for (Seller obj : list) {
    	System.out.println(obj);
    }
    
	System.out.println("\n---- Teste 3 - seller findAll----");
	list = sellerDao.FindAll();
    for (Seller obj : list) {
    	System.out.println(obj);
    }
	
	}

}
