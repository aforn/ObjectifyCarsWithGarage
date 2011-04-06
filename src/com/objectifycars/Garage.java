package com.objectifycars;
	import javax.persistence.Id;
	import com.googlecode.objectify.Key;
	import com.googlecode.objectify.annotation.Entity;
	import com.googlecode.objectify.annotation.Parent;

	@Entity
	public class Garage {
		@Id 
		public Long id;
		private String name;
		private String cityOfGarage;
		
		@Parent 
		public Key<Person> owner;
		
		private Garage() {
			//nothing
		}
		
		public Garage(Key<Person> owner) {
			this();
			this.owner = owner;
		}
		
		public Long getId(){
			return id;
		}
		
		public void setId(Long id){
			this.id = id;
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getCityOfGarage(){
			return cityOfGarage;
		}
		
		public void setCityOfGarage(String cityOfGarage){
			this.cityOfGarage = cityOfGarage;
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
