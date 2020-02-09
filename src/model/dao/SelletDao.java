package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SelletDao {
	 void insert (Seller obj);
	 void update (Seller obj);
	 void deleteById(Integer id);
	 Seller findByid(Integer id);
	 List <Seller> FindAll();
}