package com.nintendo.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nintendo.shop.dto.RoleDto;
import com.nintendo.shop.dto.UserDto;
import com.nintendo.shop.dto.UserLoginErrorDto;
import com.nintendo.shop.enums.RoleType;
import com.nintendo.shop.exception.ApiErrorException;
import com.nintendo.shop.models.AuthResponse;
import com.nintendo.shop.models.Role;
import com.nintendo.shop.models.User;
import com.nintendo.shop.repository.UserRepository;
import com.nintendo.shop.service.interfaces.IUserService;
import com.nintendo.shop.util.JwtUtil;
import com.nintendo.shop.repository.RoleRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthResponse login(String email, String password) {
		User user = userRepository.findUserByEmail(email);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			String token = jwtUtil.generateToken(email);
			int id = user.getId();
			return new AuthResponse(token, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public UserLoginErrorDto register(User user) {
		List<String> errorMessages = new ArrayList<>();

		if (userRepository.findUserByEmail(user.getEmail()) == null) {
			Role role = roleRepository.getRoleById(3);
			user.getRoles().add(role);
			User savedUser = userRepository.save(user);
			return new UserLoginErrorDto(new ArrayList<>(), jwtUtil.generateToken(user.getEmail()), savedUser.getId());
		} else {
			errorMessages.add("Email already exists");
			return new UserLoginErrorDto(errorMessages, "", 0);
		}

	}

	@Override
	public UserDto findUserById(int id) {
		User user = userRepository.findById(id);

		if (user == null) {
			return null;
		}

		UserDto userDto = new UserDto();
		userDto.setCity(user.getCity());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setPhone(user.getPhone());
		userDto.setPostalCode(user.getPostalCode());
		userDto.setStreet(user.getStreet());
		userDto.setStreetNumber(user.getStreetNumber());
		userDto.setGender(user.getGender());

		return userDto;
	}

	public List<String> save(User user) {
		List<String> errorMessages = new ArrayList<>();
		String email = user.getEmail();

		User userWithEmailExists = userRepository.findUserByEmail(email);
		if (userWithEmailExists != null) {
			errorMessages.add("Email already exists");
		}

		if (errorMessages.size() == 0) {
			Role role = roleRepository.getRoleById(3);
			user.getRoles().add(role);
			User savedCustomer = userRepository.save(user);
		}

		return errorMessages;
	}

	@Override
	public RoleDto findRoleByUserId(int id) {
		User user = userRepository.findById(id);
		Role role = null;
		RoleDto roleDto = null;
		
		if(user != null) {
			role = user.getRoles().get(0);
			roleDto = new RoleDto();
			
			if(RoleType.ROLE_ADMIN.name().equals(role.getRole())) {
				roleDto.setRole("Admin");
			}
			else if(RoleType.ROLE_MANAGER.name().equals(role.getRole())) {
				roleDto.setRole("Manager");
			} else {
				roleDto.setRole("User");
			}
		}
		

		return roleDto;
	}

	@Override
	public boolean update(User user) {
		userRepository.update(user);
		return true;
	}

}
