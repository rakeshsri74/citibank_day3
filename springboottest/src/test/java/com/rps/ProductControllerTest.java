package com.rps;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rps.controller.ProductController;
import com.rps.model.Product;
import com.rps.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@Mock
	private ProductService productService;
	private Product product;
	private List<Product> productList;

	 @InjectMocks
	 private ProductController productController;
	 @Autowired
	 private MockMvc mockMvc;

	@BeforeEach
	public void setup(){
	product = new Product(1,"ball","670");
	mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@AfterEach
	void tearDown() {
	product = null;
	}
	
	@Test
	public void PostMappingOfProduct() throws Exception{
	when(productService.addProduct(any())).thenReturn(product);
	 mockMvc.perform(post("/product/save").
	contentType(MediaType.APPLICATION_JSON)
	.content(asJsonString(product)))
	.andExpect(status().isCreated());
	verify(productService,times(1)).addProduct(any());
	}
	
	@Test
	 public void GetMappingOfAllProduct() throws Exception {
	 when(productService.getAllProduct()).thenReturn(productList);
	mockMvc.perform(MockMvcRequestBuilders.get("/product/all").
	contentType(MediaType.APPLICATION_JSON).
	content(asJsonString(product))).
	 andDo(MockMvcResultHandlers.print());
	verify(productService).getAllProduct();
	 verify(productService,times(1)).getAllProduct();
	 }
	
	 @Test
	 public void GetMappingOfProductShouldReturnRespectiveProducct() throws Exception {
	when(productService.findProduct(product.getId())).thenReturn(product);
	mockMvc.perform(get("/product/search/1").
	contentType(MediaType.APPLICATION_JSON).
	 content(asJsonString(product))).
	andExpect(MockMvcResultMatchers.status().isOk()).
	andDo(MockMvcResultHandlers.print());
	}

	 @Test
	 public void DeleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
	 when(productService.deleteProduct(product.getId())).thenReturn(product);
	 mockMvc.perform(delete("/product/delete/1")
	 .contentType(MediaType.APPLICATION_JSON)
	 .content(asJsonString(product)))
	  .andExpect(MockMvcResultMatchers.status().isOk()).
	  andDo(MockMvcResultHandlers.print());
	 }

	  public static String asJsonString(final Object obj){
	 try{
	  return new ObjectMapper().writeValueAsString(obj);
	  }catch (Exception e){
	  throw new RuntimeException(e);
	 }
	  }
}