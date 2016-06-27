package com.egloos.realmove.practicefirebase;

import java.util.List;

public class Group {

	private String name;
	private List<Scheme> schemes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Scheme> getSchemes() {
		return schemes;
	}

	public void setSchemes(List<Scheme> schemes) {
		this.schemes = schemes;
	}
}
