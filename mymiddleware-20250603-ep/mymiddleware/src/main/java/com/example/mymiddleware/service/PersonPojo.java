package com.example.mymiddleware.service;

public class PersonPojo {

	public int id;
	public String name;
	public float height;
	public boolean enabled;


	public PersonPojo(int id, String name, float height, boolean militaryEnabled) {
		this.id = id;
		this.name = name;
		this.height =height;
		this.enabled = militaryEnabled;
	}


	public String toString() {
		String out = "{ "
				+ "" + "id" + " : " + this.id
				+ ", " + "name" + " : " + this.name
				+ ", " + "height" + " : " + this.height
				+ ", " + "enabled" + " : " + this.enabled
				+ " }"
				;
		return out;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + (Integer.valueOf(this.id).hashCode());
		result = prime * result + ((this.enabled) ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		final PersonPojo other = (PersonPojo) obj;

		if (this.id != other.id) {
			return false;
		}

		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}

		if (this.enabled != other.enabled) {
			return false;
		}

		return true;
	}

}
