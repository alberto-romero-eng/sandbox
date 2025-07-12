package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import localhost.cache.service.PersonService.PersonPojo;



/**
 * <p><b>Important: </b> in <i>application.yml</i>, property <code>spring.cache.type</code> refers to 
 * {@link CacheType}, and could be used to disable cache mechanism.
 * 
 * <p>Class to contain methods annotated with {@link CacheEvict} and {@link Cacheable}.
 * 
 * <p>Wrapper methods, to ease logging or troubleshooting, should not be in this class.
 *
 * @see CacheType
 * @see InfoCacheWrapperService
 * @author Alberto Romero
 * @since 2025-05-25
 * 
 */
@Service
public class PersonCacheService implements PersonInterface {

	private static Logger log = LoggerFactory.getLogger(PersonCacheService.class);

	@Autowired
	PersonService personService;



	@CacheEvict(cacheNames = {"person"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearPersonCache() {
		log.info("Finish clearPersonCache()");
	}




	/**
	 * <p> See {@link #getContinentByCountryCacheSyncFalseUnlessResultNull(String)} comment.
	 */
	@Cacheable(value = "person", sync = false, unless = "#result == null")
	public PersonPojo getPersonCacheSyncFalseUnlessResultNull(String name, int age, float height, boolean militaryEnabled) {
		PersonPojo resPerson = null;
		resPerson = personService.getPersonCacheSyncFalseUnlessResultNull(name, age, height, militaryEnabled);
		log.info("Finish getPersonCacheSyncFalseUnlessResultNull() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}

}
