package com.nintendo.shop.models;

import java.io.Serializable;

public class AuthRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String email;
	private String password;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtTokenRequest [email=" + email + ", password=" + password + "]";
	}

}