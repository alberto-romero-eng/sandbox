package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import localhost.cache.service.PersonService.PersonPojo;

/**
 * <p><b>Important:</b> in <i>application.yml</i>, property <code>spring.cache.type</code> refers to 
 * {@link CacheType}, and could be used to disable cache mechanism.
 * 
 * <p><b>Important:</b> Hence, {@link CacheWrapperService} could be more useful than this class.
 * 
 * <p><i>Experimental</i> class to act as a gateway, allowing to use or not {@link Cacheable} methods implementation.
 * 
 * <p>Note that interface {@link PersonInterface} must be implemented by this class, {@link CacheService} 
 * and {@link PersonService}.
 * 
 * <p>There are several ways to achieve what is intended in {@link #initialize(boolean, boolean)}.
 * Other posibility could be by using {@link Bean}.
 * 
 * @see CacheType
 * @see CacheWrapperService
 * @see PersonInterface
 * @see CacheService
 * @see PersonService
 * @author Alberto Romero
 * @since 2025-05-26
 * 
 */
@Deprecated
@Service
public class CacheGatewayService implements PersonInterface {

	private static Logger log = LoggerFactory.getLogger(CacheGatewayService.class);

	private static boolean initialized = false;

	private static boolean personCacheEnabled = false;

	@Autowired
	private PersonService personService;

	@Autowired
	private CacheService cacheService;

	private PersonInterface personServiceImpl;


	public void initialize(boolean personCacheEnabled, boolean initialized) {
		CacheGatewayService.personCacheEnabled = personCacheEnabled;
		if (CacheGatewayService.personCacheEnabled) {
			personServiceImpl = cacheService;
		} else {
			personServiceImpl = personService;
		}
		CacheGatewayService.initialized = initialized;
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


	/**
	 * <p>Gateway method for {@link CacheService#getPerson(String, int, float, boolean)} / 
	 * {@link PersonService#getPerson(String, int, float, boolean)}.
	 * 
	 * @see CacheGatewayService
	 * @author Alberto Romero
	 * @since 2025-05-25
	 * 
	 */
	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled) {
		waitForInitialization();
		PersonPojo resPerson = null;
		resPerson = personServiceImpl.getPerson(name, age, height, militaryEnabled);
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}

}
