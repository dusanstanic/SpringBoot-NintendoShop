package com.nintendo.shop.dto;

import java.util.ArrayList;
import java.util.List;

public class UserLoginErrorDto {
	List<String> errorMessage = new ArrayList<>();
	String token;
	private int userId;
	private int expiresIn = 3600;

	public UserLoginErrorDto() {
	}

	public UserLoginErrorDto(List<String> errorMessage, String token, int userId) {
		this.errorMessage = errorMessage;
		this.token = token;
		this.userId = userId;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
