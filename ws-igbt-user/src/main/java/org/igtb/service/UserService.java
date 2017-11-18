package org.igtb.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.igtb.model.UserDetails;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	public Map<Integer, UserDetails> userDetailsMap = new HashMap<Integer, UserDetails>();

	public Map<Boolean, HttpStatus> saveUser(UserDetails user) throws java.text.ParseException {
		Map<Boolean, HttpStatus> response = new HashMap<>();
		Map<Integer, UserDetails> userMap = storeUsers();

		for (Map.Entry<Integer, UserDetails> entry : userMap.entrySet()) {
			UserDetails userValue = entry.getValue();
			if (entry.getKey().equals(user.getId())) {
				response.put(false, HttpStatus.CONFLICT);
				return response;
			} else {
				if (userValue.getEmail().equals(user.getEmail())) {
					if (userValue.isActive()) {
						response.put(false, HttpStatus.CONFLICT);
						return response;
					}
				} else {
					userDetailsMap.put(user.getId(), user);
					storeUsers();
					response.put(true, HttpStatus.OK);
					return response;
				}

			}
		}
		return response;

	}

	private Map<Integer, UserDetails> storeUsers() {

		Map<Integer, UserDetails> userMap = new HashMap<Integer, UserDetails>();
		userMap.putAll(userDetailsMap);
		return userMap;
	}

	public boolean deleteUser(int id) {
		if (null != userDetailsMap.get(id)) {
			userDetailsMap.remove(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUserDetails(int id, UserDetails user) {
		if (null != userDetailsMap.get(id)) {
			UserDetails userDetails = userDetailsMap.get(id);
			userDetails.setPincode(user.getPincode());
			userDetails.setBirthDate(user.getBirthDate());
			userDetailsMap.put(userDetails.getId(), userDetails);
			return true;
		} else {
			return false;
		}
	}

	public static boolean StringToDate(String s) throws java.text.ParseException {

		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MON-YYYY");
			result = (Date) dateFormat.parse(s);
			if (!s.equals(dateFormat.format(result))) {
				return false;
			}
			if (result.compareTo(new Date(0)) > 0) {
				return false;
			} else {
				return true;
			}

		} catch (ParseException e) {
			return false;

		}

	}

	public static void main(String[] args) throws java.text.ParseException {
		System.out.println(StringToDate("02-Mar-1990"));

	}
}
