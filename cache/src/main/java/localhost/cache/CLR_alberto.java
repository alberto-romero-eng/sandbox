package localhost.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import localhost.cache.service.CacheGatewayService;
import localhost.cache.service.CacheService;
import localhost.cache.service.CacheWrapperService;
import localhost.cache.service.PersonService;
import localhost.cache.service.PersonService.PersonPojo;


@Component
public class CLR_alberto implements CommandLineRunner{

	@Autowired
	CacheService cacheService;

	@Autowired
	CacheWrapperService cacheWrapperService;

	@Autowired
	CacheGatewayService cacheGatewayService;


	private static Logger log = LoggerFactory.getLogger(CLR_alberto.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello from CLR_alberto");

		// tests
		test03_CacheGateway();
		// test02_CacheWrapper();
		// test01_CacheSimple();

	}


	/**
	 * <p><b>Important:</br> Read {@link CacheGatewayService} javaDoc.
	 * 
	 * <p>This method is to be run two times, regarding {@link CacheGatewayService}
	 * initialization, <i>personCacheEnabled</i> parameter:
	 * <ul>
	 * <li>once with <i>false</i>, to test {@link PersonService} implementation. 
	 * <li>once with <i>true</i>, to test {@link CacheService} implementation.
	 * </ul>
	 * 
	 * @since 2025-05-26
	 * @see CacheGatewayService
	 * 
	 */
	private void test03_CacheGateway() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		cacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheGatewayService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// done
		log.info("done!");
	}



	private void test02_CacheWrapper() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheWrapperService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		cacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = cacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = cacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = cacheWrapperService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = cacheWrapperService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// done
		log.info("done!");
	}



	private void test01_CacheSimple() {
		String country = null;
		String continent = null;
		String model = null;
		String brand = null;

		// Canada positive in InfoService, cached in InitializerCLR
		// Thread should enter InfoService class ONLY FIRST TIME, to cache value
		// disabled in InitializerCLR
		// country = "Canada";
		// continent = cacheService.getContinentByCountryCacheSimple(country);
		// log.info("country: {}, continent: {}", country, continent);
		// continent = cacheService.getContinentByCountryCacheSimple(country);
		// log.info("country: {}, continent: {}", country, continent);
		// continent = cacheService.getContinentByCountryCacheSimple(country);
		// log.info("country: {}, continent: {}", country, continent);


		// Spain exists in InfoService
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		country = "Spain";
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);

		// Pakistan does NOT exist in InfoService
		// Thread should ALWAYS enter CacheService class if 'unless = "#result == null"' (veto) is set into @Cacheable
		country = "Pakistan";
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);

		// Neon exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value
		model = "Neon";
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);

		// Beetle does NOT exist in InfoService
		// Thread should ALWAYS enter CacheService class if 'unless = "#result == null"' (veto) is set into @Cacheable
		model = "Beetle";
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);


		// clear cache
		cacheService.clearContinentCache();
		cacheService.clearBrandCache();


		// after executing clearCache, attempt getting values again

		// Spain exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value AGAIN
		country = "Spain";
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);
		continent = cacheService.getContinentByCountryCacheWithUnlessIsNull(country);
		log.info("country: {}, continent: {}", country, continent);


		// Neon exists in InfoService
		// Thread should enter CacheService class ONLY FIRST TIME, to cache value AGAIN
		model = "Neon";
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = cacheService.getBrandByModelCacheWithUnlessIsNull(model);
		log.info("model: {}, brand: {}", model, brand);


		log.info("done!");
	}

}
