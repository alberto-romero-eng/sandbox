package localhost.cache.service;

import localhost.cache.service.PersonService.PersonPojo;

/**
 * <p>Considering that:
 * <ul>
 * <li>Method with annotation {@link Cacheable} cannot throw exception
 * <li>It is desired that methods in Service, CacheService and CacheGatewayService be somewhat inter-exchangeable.
 * </ul>
 * 
 * <p>It is <i>convenient</i> that this interface, which is to be implemented by Service and CacheGatewayService, do NOT throw exception.
 * 
 */
public interface PersonInterface {

	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled);

}
