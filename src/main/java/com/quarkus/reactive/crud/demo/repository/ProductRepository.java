package com.quarkus.reactive.crud.demo.repository;

import javax.enterprise.context.ApplicationScoped;

import com.quarkus.reactive.crud.demo.model.Product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

}
