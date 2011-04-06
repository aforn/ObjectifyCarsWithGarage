package com.objectifycars;

import javax.persistence.Id;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Car {
	@Id 
	public Long id;
	private String license;
	
	@Parent 
	public Key<Person> owner;
	
	private Car() {
		//nothing
	}
	
	public Car(Key<Person> owner) {
		this();
		this.owner = owner;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getLicense(){
		return license;
	}
	
	public void setLicense(String license){
		this.license = license;
	}
	
	public void setKey(Long newId){
		id = newId;
	}//setId
	
	public Key<Person> getOwner(){
		return owner;
	}
	
	public void setOwner(Key<Person> owner){
		this.owner = owner;
	}

}
