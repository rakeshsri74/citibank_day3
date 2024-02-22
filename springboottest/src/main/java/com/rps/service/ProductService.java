package com.rps.service;

import java.util.List;

import com.rps.model.Product;

public interface ProductService {
	
	public Product addProduct(Product prod) ;
	public Product findProduct(int id);
	public  Product deleteProduct(int id);
	public List<Product> getAllProduct();

}
