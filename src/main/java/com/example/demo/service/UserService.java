package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.User;


public interface UserService {
	public List<User> getUserList();

	public User getUser(int id);

	public User addUser(User user);

	public User updateUser(User user);

	public int deleteUser(int id);
}
