package com.objectifycars;

import javax.persistence.Id;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Person {
	@Id private Long id;
	private String name;
	/* setters and getters omitted for brevity */

	public void Person(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Person(String name){
		this.name = name;
	}
	
	public Person(){
		//nothing
	}

	public Long getId (){
		return id;
	}
	
	public void setId (Long id){
		this.id = id;
	}
	
	public String getName (){
		return name;
	}
	
	public void setId (String name){
		this.name = name;
	}
}
