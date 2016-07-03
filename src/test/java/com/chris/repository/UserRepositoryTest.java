package com.chris.repository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chris.TechtrialApplication;
import com.chris.entity.User;
import com.chris.model.UserType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TechtrialApplication.class)
@WebAppConfiguration
public class UserRepositoryTest {

	@Autowired
	protected UserRepository userRepository;

	@Test
	public void findUser() {
		User user = userRepository.findOne(1L);
		
		assertThat(user.getRegNo(), equalTo(1L));
		assertThat(user.getName(), equalTo("Chris Cho"));
		assertThat(user.getType(), equalTo(UserType.PASSENGER));
	}
	
	@Test
	public void findAllUsers() {
		List<User> findAll = (List<User>) userRepository.findAll();
		
		assertThat(findAll.size(), equalTo(3));
		
		User firstUser = findAll.get(0);
		
		assertThat(firstUser.getRegNo(), equalTo(1L));
		assertThat(firstUser.getName(), equalTo("Chris Cho"));
		assertThat(firstUser.getType(), equalTo(UserType.PASSENGER));
		
		User lastUser = findAll.get(findAll.size() - 1);
		assertThat(lastUser.getRegNo(), equalTo(3L));
		assertThat(lastUser.getName(), equalTo("Max Johns"));
		assertThat(lastUser.getType(), equalTo(UserType.STAFF));
		
		
		
	}
	
	@Test
	public void deleteUser() {
		List<User> findAll = (List<User>) userRepository.findAll();
		int size = findAll.size();
		
		User user = findAll.get(0);
		
		userRepository.delete(user.getRegNo());
		findAll = (List<User>) userRepository.findAll();
		assertThat(findAll.size(), equalTo(size -1));
		userRepository.save(user);
	}
	
	@Test
	public void saveUser() {
		List<User> findAll = (List<User>) userRepository.findAll();
		int size = findAll.size();
		
		userRepository.save(new User(123L, "wara",  "1234", "Ryan White", "2010.11.02", "wara@airline.com", "I love SPRING!!", UserType.STAFF));
		
		findAll = (List<User>) userRepository.findAll();
		assertThat(findAll.size(), equalTo(size + 1));
		
		User user = userRepository.findOne(123L);
		
		assertThat(user.getName(), equalTo("Ryan White"));
		assertThat(user.getEmail(), equalTo("wara@airline.com"));
		assertThat(user.getType(), equalTo(UserType.STAFF));
		
		userRepository.delete(123L);
	}
	
	@Test
	public void updateUser() {
		List<User> users = (List<User>) userRepository.findAll();
		User user = users.get(0);
		user.setEmail("new.one@new.com");
		userRepository.save(user);
		
		assertThat(userRepository.findOne(user.getRegNo()).getEmail(), equalTo("new.one@new.com"));
	}

}
