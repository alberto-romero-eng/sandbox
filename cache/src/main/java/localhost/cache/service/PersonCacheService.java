package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import localhost.cache.configuration.CacheNameDefn.CacheName;
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
 * @see InfoCacheGatewayService
 * @author Alberto Romero
 * @since 2025-05-25
 * 
 */
@Service
public class PersonCacheService {

	private static Logger log = LoggerFactory.getLogger(PersonCacheService.class);

	@Autowired
	PersonService personService;



	@CacheEvict(cacheNames = { CacheName.PERSON_SYNC_FALSE, CacheName.PERSON_SYNC_TRUE }, allEntries = true)
	public void clearPersonCache() {
		log.info("Finish clearPersonCache()");
	}

	@Cacheable(value = CacheName.PERSON_SYNC_FALSE, sync = false, unless = "#result == null")
	public PersonPojo getPersonCacheSyncFalseUnlessResultNull (String name, int age, float height, boolean militaryEnabled) {
		PersonPojo resPerson = null;
		try {
			resPerson = personService.getPerson(name, age, height, militaryEnabled);
		} catch (Throwable ex) {
			log.error("Finish getPersonCacheSyncFalseUnlessResultNull() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- exception: ", name, age, height, militaryEnabled, ex);
		}
		log.info("Finish getPersonCacheSyncFalseUnlessResultNull() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}


	@Cacheable(value = CacheName.PERSON_SYNC_TRUE, sync = true)
	public PersonPojo getPersonCacheSyncTrue(String name, int age, float height, boolean militaryEnabled) {
		PersonPojo resPerson = null;
		try {
			resPerson = personService.getPerson(name, age, height, militaryEnabled);
		} catch (Throwable ex) {
			log.error("Finish getPersonCacheSyncTrue() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- exception: ", name, age, height, militaryEnabled, ex);
		}
		log.info("Finish getPersonCacheSyncTrue() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}

}
