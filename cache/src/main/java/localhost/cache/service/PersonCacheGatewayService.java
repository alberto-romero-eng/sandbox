package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import localhost.cache.service.PersonService.PersonPojo;



/**
 * <p>Class to contain wrapper methods for {@link Cacheable} and {@link CacheEvict} annotated ones, 
 * since if both of them belong to the same class, the cache mechanism does not work.
 * 
 * <p>This Wrapping is useful for logging references, verify inner working of cache mechanism, 
 * troubleshooting.
 * 
 * <p><b>Important:</b> in <i>application.yml</i>, property <code>spring.cache.type</code> refers to 
 * {@link CacheType}, and could be used to disable cache mechanism.
 * 
 * <p>Note that interface {@link PersonInterface} must be implemented by this class and {@link PersonService}.
 * 
 * <p>There are several ways to achieve what is intended in {@link #initialize(boolean, boolean)}.
 * Other posibility could be by using {@link Bean}.
 * 
 * @see CacheType
 * @see PersonInterface
 * @see PersonService
 * @author Alberto Romero
 * @since 2025-05-26
 * 
 */
@Service
public class PersonCacheGatewayService implements PersonInterface {

	private static Logger log = LoggerFactory.getLogger(PersonCacheGatewayService.class);

	private static boolean initialized = false;

	/**
	 * As there is initialization method for this class, this attribute is variable.
	 */
	private static CacheForGetPerson cacheForGetPersonDefault = CacheForGetPerson.NONE;

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonCacheService personCacheService;


	public void initialize(CacheForGetPerson cacheForGetPersonDefault, boolean initialized) {
		if (cacheForGetPersonDefault == null) {
			PersonCacheGatewayService.cacheForGetPersonDefault = CacheForGetPerson.NONE;
		} else {
			PersonCacheGatewayService.cacheForGetPersonDefault = cacheForGetPersonDefault;
		}
		PersonCacheGatewayService.initialized = initialized;
	}


	/**
	 * This method should NOT be syhchronized!
	 */
	public static void waitForInitialization() {
		while (!initialized) {
			try {
				Thread.sleep(5L * 1000L);
			} catch (Exception e) {
				log.error("waitForInitialization() -- Exception ", e);
			}
		}
	}


	public static enum CacheForGetPerson {
		NONE,
		SYNC_FALSE,
		SYNC_TRUE
	}

	/**
	 * <p>Gateway method for {@link InfoCacheService#getPerson(String, int, float, boolean)} / 
	 * {@link PersonService#getPerson(String, int, float, boolean)}.
	 * 
	 * @see PersonCacheGatewayService
	 * @author Alberto Romero
	 * @since 2025-05-25
	 * 
	 */
	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled, CacheForGetPerson cacheForGetPerson) {
		waitForInitialization();
		PersonPojo resPerson = null;
		try {
			switch (cacheForGetPerson) {
			case NONE:
				resPerson = personService.getPerson(name, age, height, militaryEnabled);
			case SYNC_FALSE:
				resPerson = personCacheService.getPersonCacheSyncFalseUnlessResultNull(name, age, height, militaryEnabled);
			case SYNC_TRUE:
				resPerson = personCacheService.getPersonCacheSyncTrue(name, age, height, militaryEnabled);
			default:
				resPerson = getPerson(name, age, height, militaryEnabled, cacheForGetPersonDefault);
			}
		} catch (Throwable ex) {
			log.error("exception -- ", ex);
			resPerson = null;
		}
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {}, cacheForGetPerson: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, cacheForGetPerson, resPerson);
		return resPerson;
	}

	/**
	 * <p>Gateway method for {@link InfoCacheService#getPerson(String, int, float, boolean)} / 
	 * {@link PersonService#getPerson(String, int, float, boolean)}.
	 * 
	 * @see PersonCacheGatewayService
	 * @author Alberto Romero
	 * @since 2025-05-25
	 * 
	 */
	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled) {
		waitForInitialization();
		PersonPojo resPerson = null;
		resPerson = getPerson(name, age, height, militaryEnabled, cacheForGetPersonDefault);
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {}, cacheForGetPersonDefault: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, cacheForGetPersonDefault, resPerson);
		return resPerson;
	}

}
