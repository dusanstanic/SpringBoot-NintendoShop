package com.nintendo.shop.models;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private String token;
	private int userId;
	private int expiresIn = 3600;

	public AuthResponse(String token, int userId) {
		this.token = token;
		this.userId = userId;
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