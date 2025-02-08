package models;

public class User {
	private String name;
	private String job;

	// Default constructor (required for deserialization)
	public User() {
	}

	// Constructor for easy object creation
	public User(String name, String job) {
		this.name = name;
		this.job = job;
	}

	// Getters and Setters (needed for serialization/deserialization)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "User{name='" + name + "', job='" + job + "'}";
	}
}
