package com.chris.repository;

import org.springframework.data.repository.CrudRepository;

import com.chris.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
