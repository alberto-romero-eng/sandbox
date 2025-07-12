package localhost.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import localhost.cache.service.PersonCacheGatewayService;
import localhost.cache.service.PersonCacheService;
import localhost.cache.service.PersonCacheWrapperService;
import localhost.cache.service.PersonService.PersonPojo;
import localhost.cache.service.InfoCacheService;
import localhost.cache.service.InfoCacheWrapperService;
import localhost.cache.util.SpringBootCacheHelper;


@Component
public class CLR_alberto implements CommandLineRunner {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private ConfigurableApplicationContext cac;

	@Autowired
	InfoCacheService infoCacheService;

	@Autowired
	InfoCacheWrapperService infoCacheWrapperService;

	@Autowired
	PersonCacheService personCacheService;

	@Autowired
	PersonCacheWrapperService personCacheWrapperService;


	@Autowired
	PersonCacheGatewayService cacheGatewayService;

	@Autowired
	SpringBootCacheHelper sbCacheHelper;


	private static Logger log = LoggerFactory.getLogger(CLR_alberto.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello from CLR_alberto");

		// tests
		// test04_CacheGateway();
		// test03_CacheWrapper();
		test02_CacheSimple();
		// test01_SpringBootCacheHelper();
		// test00_ApplicationContext();
	}


	/**
	 * <p><b>Important:</br> Read {@link PersonCacheGatewayService} javaDoc.
	 * 
	 * <p>This method is to be run two times, regarding {@link PersonCacheGatewayService}
	 * initialization, <i>personCacheEnabled</i> parameter:
	 * <ul>
	 * <li>once with <i>false</i>, to test {@link PersonService} implementation. 
	 * <li>once with <i>true</i>, to test {@link InfoCacheService} implementation.
	 * </ul>
	 * 
	 * @since 2025-05-26
	 * @see PersonCacheGatewayService
	 * 
	 */
	private void test04_CacheGateway() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		personCacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPersonCacheSyncFalseUnlessResultNull(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// done
		log.info("done!");
	}



	private void test03_CacheWrapper() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheWrapperService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		personCacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheWrapperService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheWrapperService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// done
		log.info("done!");
	}



	private void test02_CacheSimple() {
		String country = null;
		String continent = null;
		String model = null;
		String brand = null;


		// Spain exists in InfoService
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		country = "Spain";
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);

		// Pakistan does NOT exist in InfoService
		// Thread should ALWAYS enter CacheService class if 'unless = "#result == null"' (veto) is set into @Cacheable
		country = "Pakistan";
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);

		// Neon exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value
		model = "Neon";
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);

		// Beetle does NOT exist in InfoService
		// Thread should ALWAYS enter CacheService class if 'unless = "#result == null"' (veto) is set into @Cacheable
		model = "Beetle";
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);


		// clear cache
		infoCacheService.clearContinentCache();
		infoCacheService.clearBrandCache();


		// after executing clearCache, attempt getting values again

		// Spain exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value AGAIN
		country = "Spain";
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("country: {}, continent: {}", country, continent);


		// Neon exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value AGAIN
		model = "Neon";
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("model: {}, brand: {}", model, brand);


		log.info("done!");
	}


	private void test01_SpringBootCacheHelper() {
		CacheProperties cacheProp = sbCacheHelper.getCachePropertiesBean();
		CacheType cacheType = cacheProp.getType();
		log.info("CacheType: {}", cacheType);
	}



	private void test00_ApplicationContext() {
		int count = ac.getBeanDefinitionCount();
		String[] names = ac.getBeanDefinitionNames();
		log.info("count: {}", count);
		for (String name : names) {
			if (name.matches("(?i)^.*cache.*$")) {
				log.info("beanName: {}", name);
			}
		}
	}


}
