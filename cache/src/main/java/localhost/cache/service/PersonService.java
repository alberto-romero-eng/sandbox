package localhost.cache.service;

import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonInterface {

	private static Logger log = LoggerFactory.getLogger(PersonService.class);

	private static LinkedHashSet<PersonPojo> personSet = new LinkedHashSet<>();

	static {
		// PersonPojos
		PersonPojo p1 = new PersonPojo("One", 11, 1.1f, true);
		PersonPojo p2 = new PersonPojo("Two", 12, 1.2f, false);
		PersonPojo p3 = new PersonPojo("Three", 13, 1.3f, true);
		PersonPojo p4 = new PersonPojo("Four", 14, 1.4f, false);

		// populate personSet
		personSet.add(p1);
		personSet.add(p2);
		personSet.add(p3);
		personSet.add(p4);
	}

	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled) {
		PersonPojo resPerson = null;
		if (name == null || name.isEmpty()) {
			return null;
		}
		for (PersonPojo p : personSet) {
			if (true
					&& name.equals(p.name)
					&& age == p.age
					&& height == p.height
					&& militaryEnabled == p.militaryEnabled
					&& true ) {
				resPerson = p;
				break;
			}
		}
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}


	public static class PersonPojo {

		public String name;
		public int age;
		public float height;
		public boolean militaryEnabled;


		public PersonPojo(String name, int age, float height, boolean militaryEnabled) {
			this.name = name;
			this.age = age;
			this.height =height;
			this.militaryEnabled = militaryEnabled;
		}


		public String toString() {
			String out = "{ "
					+ "" + "name" + " : " + this.name
					+ ", " + "age" + " : " + this.age
					+ ", " + "height" + " : " + this.height
					+ ", " + "militaryEnabled" + " : " + this.militaryEnabled
					+ " }"
					;
			return out;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
			result = prime * result + (Integer.valueOf(this.age).hashCode());
			result = prime * result + ((this.militaryEnabled) ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode());
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

			if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
				return false;
			}

			if (this.age != other.age) {
				return false;
			}

			if (this.militaryEnabled != other.militaryEnabled) {
				return false;
			}

			return true;
		}

	}


}
