package org.javacream.training.batch.file;


public class Person {

	@Override
	public String toString() {
		return "Person [lastname=" + lastname + ", firstname=" + firstname + "]";
	}
	private String lastname;
	private String firstname;
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
}