package com.springdata.mvcexample.SpringDataMvc.repository;

import com.springdata.mvcexample.SpringDataMvc.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
