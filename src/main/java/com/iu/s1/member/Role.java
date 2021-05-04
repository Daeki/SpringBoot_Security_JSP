package com.iu.s1.member;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
	
	final private String name;

	private Role(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	

}
