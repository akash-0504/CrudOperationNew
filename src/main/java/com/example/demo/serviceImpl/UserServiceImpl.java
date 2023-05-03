package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
 
	@Override
	public User addUser(User user) {
		if (user.getCategory().equals("minor"))
			user.setStatus("NOT_ELIGIBLE");
		else
			user.setStatus("ELIGIBLE");

		return userRepository.save(user);
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = (List<User>) userRepository.findAll();
		return userList;
	}

	@Override
	public User getUser(int id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	@Override
	public User updateUser(User user) {
		User savedUser = userRepository.findById(user.getId()).get();
		savedUser.setAddress(user.getAddress());
		savedUser.setCategory(user.getCategory());
		if (user.getCategory().equals("minor"))
			savedUser.setStatus("NOT_ELIGIBLE");
		else
			savedUser.setStatus("ELIGIBLE");
		savedUser.setName(user.getName());
		
		return userRepository.save(savedUser);
	}

	@Override
	public int deleteUser(int id) {
		int userId = userRepository.findById(id).orElse(null).getId();
		userRepository.deleteById(id);
		return userId;
	}

}
