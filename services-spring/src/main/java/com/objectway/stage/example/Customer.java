package com.objectway.stage.example;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {
	private int idRef;
	private String name;
	private String lastName;
	private int age;
	private Date birthDate;
	private List<String> phones;
	
	public int getIdRef() {
		return idRef;
	}
	public void setIdRef(int idRef) {
		this.idRef = idRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
}
