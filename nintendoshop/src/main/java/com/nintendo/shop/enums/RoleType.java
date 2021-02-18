package com.nintendo.shop.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
	ROLE_ADMIN("role_admin"), ROLE_USER("role_manager"), ROLE_MANAGER("role_user");

	private final String value;

	RoleType(String value) {
		this.value = value;
	}

	public String getAuthority() {
		return name();
	}
}
