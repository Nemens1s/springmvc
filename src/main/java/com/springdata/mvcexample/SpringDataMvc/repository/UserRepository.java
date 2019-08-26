package com.springdata.mvcexample.SpringDataMvc.repository;

import com.springdata.mvcexample.SpringDataMvc.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
