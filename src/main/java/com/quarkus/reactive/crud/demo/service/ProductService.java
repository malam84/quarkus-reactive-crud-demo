package com.quarkus.reactive.crud.demo.service;

import java.util.List;

import com.quarkus.reactive.crud.demo.exception.RecordNotFoundException;
import com.quarkus.reactive.crud.demo.model.Product;

public interface ProductService {
	
	Product getProductById(long prodId) throws RecordNotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(long prodId, Product product) throws RecordNotFoundException;
    Product saveProduct(Product product);
    void deleteProduct(long prodId) throws RecordNotFoundException;

}
