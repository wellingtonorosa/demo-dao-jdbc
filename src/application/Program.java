package application;

import java.util.Date;

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

	}

}
