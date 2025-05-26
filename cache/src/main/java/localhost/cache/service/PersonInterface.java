package localhost.cache.service;

import localhost.cache.service.PersonService.PersonPojo;

public interface PersonInterface {

	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled);

}
