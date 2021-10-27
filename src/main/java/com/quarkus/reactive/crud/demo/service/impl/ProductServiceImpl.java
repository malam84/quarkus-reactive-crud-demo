package com.quarkus.reactive.crud.demo.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.quarkus.reactive.crud.demo.exception.RecordNotFoundException;
import com.quarkus.reactive.crud.demo.model.Product;
import com.quarkus.reactive.crud.demo.repository.ProductRepository;
import com.quarkus.reactive.crud.demo.service.ProductService;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

	private final ProductRepository prodRepository;
	
	@Inject
	public ProductServiceImpl(ProductRepository prodRepository) {
		this.prodRepository = prodRepository;
	}
	
	@Override
	public Product getProductById(long prodId) throws RecordNotFoundException {
		return prodRepository.findByIdOptional(prodId).orElseThrow(
				()-> new RecordNotFoundException("Record not found for id " + prodId));
	}

	@Override
	public List<Product> getAllProducts() {
		return prodRepository.listAll();
	}

	@Override
	@Transactional
	public Product updateProduct(long prodId, Product product) throws RecordNotFoundException {
		Product dbProd = getProductById(prodId);
		dbProd.setProductName(product.getProductName());
		dbProd.setProductCategory(product.getProductCategory());
		dbProd.setMinPeriod(product.getMinPeriod());
		dbProd.setMaxPeriod(product.getMaxPeriod());
		dbProd.setMinAmount(product.getMinAmount());
		dbProd.setMaxAmount(product.getMaxAmount());
		dbProd.setStatus(product.isStatus());
		dbProd.setCreatedDate(product.getCreatedDate());
        prodRepository.persist(dbProd);
        return dbProd;
	}

	@Transactional
	@Override
	public Product saveProduct(Product product) {
		prodRepository.persistAndFlush(product);
		return product;
	}

	@Override
	@Transactional
	public void deleteProduct(long prodId) throws RecordNotFoundException {
		prodRepository.delete(getProductById(prodId));
	}

}
