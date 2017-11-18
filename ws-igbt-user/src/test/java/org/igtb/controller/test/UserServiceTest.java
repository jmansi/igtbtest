package org.igtb.controller.test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import org.igtb.model.UserDetails;
import org.igtb.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class UserServiceTest {

	UserService service = new UserService();

	@Before
	public void setUp() {
		UserService service = new UserService();
		service.userDetailsMap = generateData();

	}

	@Test
	public void testUserCreation() throws ParseException {
		UserDetails user1 = new UserDetails();
		user1.setActive(false);
		user1.setBirthDate("02-MAR-1980");
		user1.setEmail("j@gmail.com");
		user1.setfName("user1");
		user1.setfName("user1last");
		user1.setId(1);
		user1.setPincode(180001L);
		Map<Boolean, HttpStatus> map = service.saveUser(user1);
		for (Map.Entry<Boolean, HttpStatus> entry : map.entrySet()) {
			boolean response = entry.getKey();
			assert (!response);
		}

	}

	
	@Test
	public void testDeleteUser() {
		service.userDetailsMap = generateData();
		boolean value = service.deleteUser(1);
		assert (value);
	}

	@Test
	public void testUpdateUser() {
		service.userDetailsMap = generateData();
		UserDetails user1 = new UserDetails();
		user1.setActive(false);
		user1.setBirthDate("02-MAR-1980");
		user1.setEmail("j@gmail.com");
		user1.setfName("user1");
		user1.setfName("user1last");
		user1.setId(1);
		user1.setPincode(180002L);
		boolean value = service.updateUserDetails(1, user1);
		assert (value);
	}

	private Map<Integer, UserDetails> generateData() {
		Map<Integer, UserDetails> userDetailsMap = new HashMap<>();
		UserDetails user1 = new UserDetails();
		UserDetails user2 = new UserDetails();
		user1.setActive(false);
		user1.setBirthDate("02-MAR-1980");
		user1.setEmail("j@gmail.com");
		user1.setfName("user1");
		user1.setfName("user1last");
		user1.setId(1);
		user1.setPincode(180001L);

		user2.setActive(false);
		user2.setBirthDate("02-MAR-1980");
		user2.setEmail("jk@gmail.com");
		user2.setfName("user2");
		user2.setfName("user2last");
		user2.setId(2);
		user2.setPincode(180001L);
		userDetailsMap.put(1, user1);
		userDetailsMap.put(2, user2);

		return userDetailsMap;

	}

}
