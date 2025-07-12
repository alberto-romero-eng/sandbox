package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import localhost.cache.service.PersonService.PersonPojo;


/**
 * <p>Class to contain wrapper methods for {@link Cacheable} and {@link CacheEvict} annotated ones, 
 * since if both of them belong to the same class, the cache mechanism does not work.
 * 
 * <p>This Wrapping is useful for logging references, verify inner working of cache mechanism, 
 * troubleshooting.
 * 
 * @see InfoCacheService
 * @author Alberto Romero
 * @since 2025-05-25
 * 
 */

@Service
public class PersonCacheWrapperService {

	private static Logger log = LoggerFactory.getLogger(PersonCacheWrapperService.class);

	@Autowired
	private PersonCacheService personCacheService;


	/**
	 * <p>Wrapper for {@link PersonCacheService#getPersonCacheSyncFalseUnlessResultNull(String, int, float, boolean)}.
	 * 
	 * @see PersonCacheWrapperService
	 * @see InfoCacheService
	 * @author Alberto Romero
	 * @since 2025-05-25
	 * 
	 */
	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled) {
		// waitForInitialization();
		PersonPojo resPerson = null;
		resPerson = personCacheService.getPersonCacheSyncFalseUnlessResultNull(name, age, height, militaryEnabled);
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}

}
