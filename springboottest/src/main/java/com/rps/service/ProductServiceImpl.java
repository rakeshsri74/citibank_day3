package com.rps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rps.dao.ProductRepository;
import com.rps.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product addProduct(Product prod) {
		Optional<Product> findById = productRepository.findById(prod.getId());
		Product p = null;
		if(!findById.isPresent())
			p = productRepository.save(prod);
		
		
		return p;
	}

	@Override
	public Product findProduct(int id) {
		Optional<Product> findById = productRepository.findById(id);
		Product p = null;
		if(findById.isPresent())
			p = findById.get();
		
		
		return p;
	}

	@Override
	public Product deleteProduct(int id) {
		Optional<Product> findById = productRepository.findById(id);
		Product p = null;
		if(findById.isPresent())
			p = findById.get();
			
		productRepository.deleteById(id);
			
		return p;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> productList = null;
		if(productRepository.findAll().size() > 0)
			productList = productRepository.findAll();
		return productList;
	}

}
