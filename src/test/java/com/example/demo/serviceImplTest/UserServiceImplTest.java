package com.example.demo.serviceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {

	User userDummy;

	User userDummy2;

	List<User> userList;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setup() {
		userList = getUserList();

		userDummy = getUser();
		userDummy.setCategory("minor");
		userDummy.setStatus("NOT_ELIGIBLE");

		userDummy2 = getUser();
		userDummy2.setCategory("adult");
		userDummy2.setStatus("ELIGIBLE");

	}

	@Test
	void addUser_when_category_is_minor_test() {

		when(userRepository.save(Mockito.any(User.class))).thenReturn(userDummy);
		User actualoutput = userServiceImpl.addUser(userDummy);
		assertEquals(userDummy.toString(), actualoutput.toString());

	}

	@Test
	void addUser_when_category_is_not_minor_test() {

		when(userRepository.save(Mockito.any(User.class))).thenReturn(userDummy2);
		User actualoutput = userServiceImpl.addUser(userDummy2);
		assertEquals(actualoutput.toString(), userDummy2.toString());

	}

	@Test
	void getUser_test() {

		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDummy));
		User actualOutput = userServiceImpl.getUser(userDummy.getId());
		assertEquals(actualOutput.toString(), userDummy.toString());
	}

	@Test
	void getUserList_test() {

		when(userRepository.findAll()).thenReturn(userList);
		List<User> actualUserList = userServiceImpl.getUserList();
		assertEquals(actualUserList.size(), userList.size());

	}

	@Test
	void updateUser_when_category_is_minor_test() {

		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDummy2));
		when(userRepository.save(Mockito.any(User.class))).thenReturn(userDummy);
		User actualOutput = userServiceImpl.updateUser(userDummy);
		assertEquals(userDummy.toString(), actualOutput.toString());

	}

	@Test
	void updateUser_when_category_is_not_minor_test() {

		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDummy));
		when(userRepository.save(Mockito.any(User.class))).thenReturn(userDummy2);
		User actualOutput = userServiceImpl.updateUser(userDummy2);
		assertEquals(userDummy2.toString(), actualOutput.toString());

	}

	@Test
	void deleteUser_test() {
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(userDummy));
		int id = userServiceImpl.deleteUser(userDummy.getId());
		assertEquals(userDummy.getId(), id);

	}

	private User getUser() {
		User user = new User();
		user.setId(101);
		user.setName("Shivam");
		user.setAddress("Indore");
		return user;
	}

	private List<User> getUserList() {
		List<User> userList = new ArrayList<>();

		User user1 = new User();
		user1.setId(101);
		user1.setName("Shivam");
		user1.setCategory("minor");
		user1.setAddress("Indore");
		user1.setStatus("NOT_ELIGIBLE");

		User user2 = new User();
		user2.setId(102);
		user2.setName("Aniket");
		user2.setCategory("adult");
		user2.setAddress("Kolkata");
		user2.setStatus("ELIGIBLE");

		userList.add(user1);
		userList.add(user2);
		return userList;
	}

}
