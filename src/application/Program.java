package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Department obj = new Department(1, "Libros");
Seller seller = new Seller(21,"bob","bob@gmail.com", new Date(),3000.00,obj);

System.out.println(seller);

	}

}