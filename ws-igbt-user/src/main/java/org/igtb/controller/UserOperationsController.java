package org.igtb.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.igtb.model.UserDetails;
import org.igtb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOperationsController {

	@Autowired
	private UserService userService;


	//Create User
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public Map<String, HttpStatus> createUser(@RequestBody UserDetails user) throws ParseException {
		Map<String, HttpStatus> responseMap = new HashMap<>();
		String msg = null;
		if (!UserService.StringToDate(user.getBirthDate())) {
			msg = "Wrong Date Value..Please provide date in DD-MON-YYYY fomat";
			responseMap.put(msg, HttpStatus.CONFLICT);
			return responseMap;
		}
		Map<Boolean, HttpStatus> response = userService.saveUser(user);
		if (response.containsKey(false)) {
			msg = "User is  active with this emailId";
			responseMap.put(msg, HttpStatus.CONFLICT);
			return responseMap;

		}
		responseMap.put("User is not active with the email id", HttpStatus.OK);
		return responseMap;
	}
	
   //Delete User
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Integer id) {
		String msg = null;
		if (userService.deleteUser(id)) {
			msg = "User is deleted";
		} else {
			msg = "User does not exist";
		}
		return msg;
	}

	//Update User
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable Integer id, @RequestBody UserDetails user) {
		String msg = null;
		if (userService.updateUserDetails(id, user)) {
			msg = "User is updated";
			return msg;
		} else {
			msg = "User does not exist";
			return msg;

		}

	}

}
