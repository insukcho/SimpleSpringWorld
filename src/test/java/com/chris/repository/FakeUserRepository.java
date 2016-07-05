package com.chris.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chris.entity.User;
import com.chris.model.UserType;

public class FakeUserRepository implements UserRepository {
	
	List<User> users = new ArrayList<User>();

	@Override
	public <S extends User> S save(S entity) {
		users.add(entity);
		return entity;
	}

	@Override
	public <S extends User> Iterable<S> save(Iterable<S> entities) {
		users.addAll((Collection<? extends User>) entities);
		return entities;
	}

	@Override
	public User findOne(Long id) {
		for(User u: users) {
			if(u.getRegNo().equals(id)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		deleteAll();
		
		save(new User(11L, "wara",  "1234", "Ryan White", "2010.11.02", "wara@airline.com", "I love SPRING!!", "Staff"));
		save(new User(12L, "napal",  "4321", "Chris Cho", "1980.04.05", "napal@bbb.com", "You love SPRING!!", "Passenger"));
		
		return users;
	}

	@Override
	public Iterable<User> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		users.clear();
	}

}
