package com.nintendo.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nintendo.shop.dto.RoleDto;
import com.nintendo.shop.dto.UserDto;
import com.nintendo.shop.dto.UserLoginErrorDto;
import com.nintendo.shop.models.AuthRequest;
import com.nintendo.shop.models.AuthResponse;
import com.nintendo.shop.models.Game;
import com.nintendo.shop.models.User;
import com.nintendo.shop.service.interfaces.IUserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
		AuthResponse authResponse = userService.login(auth.getEmail(), auth.getPassword());
		if (authResponse == null) {
			return new ResponseEntity("Email or password is incorrect", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(authResponse);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		UserLoginErrorDto registrationIsValid = userService.register(user);

		if (registrationIsValid.getErrorMessage().size() == 0) {
			AuthResponse authResponse = new AuthResponse(registrationIsValid.getToken(), registrationIsValid.getUserId());
			return new ResponseEntity(authResponse, HttpStatus.OK);
		}
		System.out.println(registrationIsValid.getErrorMessage().get(0));
		return new ResponseEntity<>(registrationIsValid, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/role/{id}")
	public ResponseEntity<?> findRoleByUserId(@PathVariable int id) {
		RoleDto roleDto = userService.findRoleByUserId(id);

		if (roleDto != null) {
			return new ResponseEntity(roleDto, HttpStatus.OK);
		}

		return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {
		UserDto userDto = userService.findUserById(id);

		if (userDto != null) {
			return new ResponseEntity(userDto, HttpStatus.OK);
		}

		return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Boolean> update(@RequestBody User user) {
		boolean updateUser = userService.update(user);
		return ResponseEntity.ok(updateUser);
	}

}
