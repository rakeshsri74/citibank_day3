package com.rps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rps.model.Product;
import com.rps.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@RequestMapping("/all")
	public ResponseEntity<List<Product>> allProduct(){
		return new ResponseEntity<>(service.getAllProduct(),HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Product> searchProduct(@PathVariable("id") int id){
		return new ResponseEntity<Product>(service.findProduct(id),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product prod){
		return new ResponseEntity<Product>(service.addProduct(prod),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id){
		return new ResponseEntity<Product>(service.deleteProduct(id),HttpStatus.OK);
	}
	
	
}