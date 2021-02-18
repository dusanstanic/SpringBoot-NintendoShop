package com.nintendo.shop.service.interfaces;

import com.nintendo.shop.dto.RoleDto;
import com.nintendo.shop.dto.UserDto;
import com.nintendo.shop.dto.UserLoginErrorDto;
import com.nintendo.shop.models.AuthResponse;
import com.nintendo.shop.models.User;

public interface IUserService {
	public AuthResponse login(String email, String password);

	public UserLoginErrorDto register(User user);

	public RoleDto findRoleByUserId(int id);

	public UserDto findUserById(int id);

	public boolean update(User user);
	
}
