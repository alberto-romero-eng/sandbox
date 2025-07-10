package com.example.myserver.service;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonInterface {

	private static Logger log = LoggerFactory.getLogger(PersonService.class);

	private static LinkedHashMap<Integer,PersonPojo> personMap = new LinkedHashMap<>();

	static {
		// PersonPojos
		PersonPojo p1 = new PersonPojo(1, "One", 1.1f, true);
		PersonPojo p2 = new PersonPojo(2, "Two", 1.2f, false);
		PersonPojo p3 = new PersonPojo(3, "Three", 1.3f, true);
		PersonPojo p4 = new PersonPojo(4, "Four", 1.4f, false);

		// populate personSet
		personMap.put(1, p1);
		personMap.put(2, p2);
		personMap.put(3, p3);
		personMap.put(4, p4);
	}

	public Optional<PersonPojo> getById(Integer id) {
		if (id == null) {
			return Optional.empty();
		}
		PersonPojo resPerson = null;
		resPerson = personMap.get(id);
		log.info("Finish getPerson() -- params -- id: {} -- results -- resPerson: {}", id, resPerson);
		return Optional.of(resPerson);
	}


	public static class PersonPojo {

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


}
