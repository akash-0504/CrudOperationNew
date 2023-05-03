package com.example.demo.ControllerTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	UserService userServiceMock;

	User userDummy;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		userDummy = getUser();
		userDummy.setCategory("minor");
		userDummy.setStatus("NOT_ELIGIBLE");
	}

	@Test
	public void addUser_test() {
		when(userServiceMock.addUser(Mockito.any(User.class))).thenReturn(userDummy);
		User expectedOutput = userController.addUser(userDummy);
		assertEquals(userDummy.toString(), expectedOutput.toString());

	}

	@Test
	public void addUserTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(userDummy);
		when(userServiceMock.addUser(Mockito.any(User.class))).thenReturn(userDummy);
		MvcResult mvcResult = mockMvc
				.perform(post("/addUser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		User expectedOutputUser = objectMapper.readValue(expectedOutput, User.class);
		assertEquals(expectedOutputUser.getStatus(), userDummy.getStatus());
	}

	private User getUser() {
		User user = new User();
		user.setId(101);
		user.setName("Shivam");
		user.setAddress("Indore");
		return user;
	}

}
