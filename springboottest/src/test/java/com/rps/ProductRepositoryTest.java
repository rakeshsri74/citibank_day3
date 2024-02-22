package com.rps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.rps.dao.ProductRepository;
import com.rps.model.Product;

/*@ExtendWith(SpringExtension.class)
@DataJpaTest
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
//@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

	 @Autowired
	 TestEntityManager testEntityManager;
	 
	@Autowired
	private ProductRepository productRepository;
	
	private Product product;

	@BeforeEach
	public void setUp() {

	product = new Product(11,"Bat","2500");
	}

	@AfterEach
	public void tearDown() {
	productRepository.deleteAll();
	product = null;
	}
	
	@Test
	public void givenProductToAddShouldReturnAddedProduct(){
	productRepository.save(product);
	Product fetchedProduct = productRepository.findById(product.getId()).get();
	assertEquals(11, fetchedProduct.getId());
	}
	
	@Test
	public void GivenGetAllProductShouldReturnListOfAllProducts(){
	Product product1 = new Product(12,"ball","400");
	Product product2 = new Product(22,"bat","500");
	productRepository.save(product1);
	productRepository.save(product2);
	List<Product> productList = (List<Product>) productRepository.findAll();
	assertEquals("ball", productList.get(1).getName());
	}
	
	@Test
	public void givenIdThenShouldReturnProductOfThatId() {
	Product product1 = new Product(111,"bat","3000");
	Product product2 = productRepository.save(product1);
	Optional<Product> optional = productRepository.findById(product2.getId());
	assertEquals(product2.getId(), optional.get().getId());
	assertEquals(product2.getName(), optional.get().getName());
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheProduct() {
	Product product = new Product(44, "pen","160");
	productRepository.save(product);
	productRepository.deleteById(product.getId());
	Optional<Product> optional = productRepository.findById(product.getId());
	assertEquals(Optional.empty(), optional);
	}

}