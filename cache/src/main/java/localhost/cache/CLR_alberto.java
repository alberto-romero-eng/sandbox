package localhost.cache;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import localhost.cache.service.PersonCacheGatewayService;
import localhost.cache.service.PersonCacheService;
import localhost.cache.service.PersonService.PersonPojo;
import localhost.cache.service.InfoCacheService;
import localhost.cache.configuration.CacheNameDefn;
import localhost.cache.configuration.CacheNameDefn.CacheName;
import localhost.cache.service.InfoCacheGatewayService;
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
	InfoCacheGatewayService infoCacheGatewayService;

	@Autowired
	PersonCacheService personCacheService;

	@Autowired
	PersonCacheGatewayService personCacheGatewayService;

	@Autowired
	CacheManager cacheManager;

	@Autowired
	SpringBootCacheHelper sbCacheHelper;


	private static Logger log = LoggerFactory.getLogger(CLR_alberto.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello from CLR_alberto");

		// tests
		test07_testSimplest();
		// test06_CacheManager();
		// test05_ConcurrenceSyncFalse();
		// test05_ConcurrenceSyncTrue();
		// test04_CacheGateway_B(); // TODO: re-organize this method, possible collisions with A
		// test03_CacheGateway_A(); // TODO: re-organize this method, possible collisions with B
		// test02_CacheSimple();
		// test01_SpringBootCacheHelper();
		// test00_ApplicationContext();
	}


	/**
	 * <p>Important: {@link Cacheable}'s synchronization operates on a <b><i>key</i></b>, not on the 
	 * whole {@link ConcurrentMap}.  Hence:
	 * 
	 * <ul>
	 * 
	 * <li>A thread entering a method annotated with {@link Cacheable} and synchronization <b>true</b>, 
	 * establish a restriction on others threads carrying the same parameters or <b><i>key</i></b>, 
	 * preventing them to operate on such <i>key</i> (this restriction includes methods with 
	 * synchronization <i>false</i>, or any underlying method of {@link ConcurrentMap} on 
	 * the <i>key</i>).
	 * 
	 * <li>A thread entering a method annotated with {@link Cacheable} and synchronization <b>false</b>,
	 * do not restrict other threads as mentioned above.  Even such threads, carrying the same 
	 * parameters or <i>key</i>, may enter enter a <i>synchronized-true</i> method, or perform a {@link ConcurrentMap}'s
	 * operation on the <i>key</i>.
	 * 
	 * </ul>
	 * 
	 * <p>Lock mechanism is shown in debugger as {@link ConcurrentMap}'s ReservationNode, on acting thread.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-31
	 */
	private void test07_testSimplest() throws Exception {

		log.info("test07, start!");

		Runnable seeMapRunnable = () -> {
			Cache simplestCache = cacheManager.getCache(CacheName.SIMPLEST);
			log.info("done! -- {}", simplestCache);
		};


		Runnable getSimplestSyncTrueValueOneFirstRunnable = () -> {
			String anyValue = "valueOne";
			String result = infoCacheService.getSimplestSyncTrue(anyValue);
			log.info("done! -- {}", result);
		};

		Runnable getSimplestSyncVaryingValueOneSecondRunnable = () -> {
			String anyValue = "valueOne";
			String result = infoCacheService.getSimplestSyncFalse(anyValue); // vary sync: true, false
			log.info("done! -- {}", result);
		};

		Runnable getSimplestSyncFalseValueTwoFirstRunnable = () -> {
			String anyValue = "valueTwo";
			String result = infoCacheService.getSimplestSyncTrue(anyValue);
			log.info("done! -- {}", result);
		};

		Runnable getSimplestSyncVaryingValueTwoSecondRunnable = () -> {
			String anyValue = "valueTwo";
			String result = infoCacheService.getSimplestSyncFalse(anyValue); // vary sync: true, false
			log.info("done! -- {}", result);
		};

		Runnable clearRunnable = () -> {
			infoCacheService.clearSimplestCache();
			log.info("done!");
		};

		Thread tMap = new Thread(seeMapRunnable, "tMap");
		Thread tSyncTrueOneFirst = new Thread(getSimplestSyncTrueValueOneFirstRunnable, "tSyncTrueOneFirst");
		Thread tSyncTrueOneSecond = new Thread(getSimplestSyncVaryingValueOneSecondRunnable, "tSyncTrueOneSecond");
		Thread tSyncFalseTwoFirst = new Thread(getSimplestSyncFalseValueTwoFirstRunnable, "tSyncFalseTwoFirst");
		Thread tSyncFalseTwoSecond = new Thread(getSimplestSyncVaryingValueTwoSecondRunnable, "tSyncFalseTwoSecond");
		Thread tClear = new Thread(clearRunnable, "tClear");

		tMap.start();
		TimeUnit.SECONDS.sleep(1);
		tSyncTrueOneFirst.start();
		TimeUnit.SECONDS.sleep(1);
		tSyncTrueOneSecond.start();
		TimeUnit.SECONDS.sleep(1);
		tSyncFalseTwoFirst.start();
		TimeUnit.SECONDS.sleep(1);
		tSyncFalseTwoSecond.start();
		TimeUnit.SECONDS.sleep(1);
		tClear.start();

		log.info("done!");
	}


	private void test06_CacheManager() {
		// load sample values
		infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull("Spain");
		infoCacheService.getBrandByModelCacheSyncTrue("Neon");

		// cacheNames
		Collection<String> cacheNames = cacheManager.getCacheNames();
		log.info("cacheNames: {}", cacheNames);

		// continent-cache, value for key "Spain"
		Cache continentCache = cacheManager.getCache(CacheName.CONTINENT_SYNC_FALSE);
		ValueWrapper vwContinentForSpain = continentCache.get("Spain");
		String continentForSpain = (String) vwContinentForSpain.get();
		log.info("Spain, valueWrapper: {}, valueWrapper.get: {}", vwContinentForSpain, continentForSpain);

		// continent-cache, native, size
		Map<String,String> continentNativeCache = (Map<String,String>) continentCache.getNativeCache();
		log.info("continentNativeCache -> class: {}, size: {}, keySet: {}", continentNativeCache.getClass().getSimpleName(), continentNativeCache.size(), continentNativeCache.keySet());

		// brand-cache, value for key "Neon"
		Cache brandCache = cacheManager.getCache(CacheName.BRAND_SYNC_TRUE);
		ValueWrapper vwBrandForNeon = brandCache.get("Neon");
		String brandForNeon = (String) vwBrandForNeon.get();
		log.info("Neon, valueWrapper: {}, valueWrapper.get: {}", vwBrandForNeon, brandForNeon);

		// brand-cache, native, size
		Map<String,String>  brandNativeCache = (Map<String,String> ) brandCache.getNativeCache();
		log.info("brandNativeCache -> class: {}, size: {}, keySet: {}", brandNativeCache.getClass().getSimpleName(), brandNativeCache.size(), brandNativeCache.keySet());

		// end
		log.info("done!");
	}

	/**
	 * <p>All threads can access cached method, even with all of them having the same <i>key</i> used for
	 * <i>ConcurrentMap</i> / cache.
	 * 
	 * <p>In other words, no synchronization whatsoever is implemented.
	 * 
	 */
	private void test05_ConcurrenceSyncFalse() {

		Runnable runnableSF = () -> {
			String country = null;
			String continent = null;

			country = "Spain";
			continent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
			log.info("country: {}, continent: {}", country, continent);

		};

		Thread t1 = new Thread(runnableSF, "t1");
		Thread t2 = new Thread(runnableSF, "t2");
		Thread t3 = new Thread(runnableSF, "t3");

		t1.start();
		t2.start();
		t3.start();

		log.info("done!");

	}

	/**
	 * <p>For threads attempting to access cached method, concurrently, and having the same <i>key</i> used for
	 * <i>ConcurrentMap</i> / cache, such access is managed <i>synchronously</i>.
	 * 
	 * <p>In other words, synchronization applies on <i>ConcurrentMap$Node</i>, or pair <i>key / value</i>.
	 * 
	 */
	private void test05_ConcurrenceSyncTrue() {
		Runnable runnableST1 = () -> {
			String model = null;
			String brand = null;

			model = "Neon";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Fiesta";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Beetle";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);
		};

		Runnable runnableST2 = () -> {
			String model = null;
			String brand = null;

			model = "Fiesta";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Neon";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Beetle";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);
		};

		Runnable runnableST3 = () -> {
			String model = null;
			String brand = null;

			model = "Beetle";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Neon";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);

			model = "Fiesta";
			brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			log.info("model: {}, brand: {}", model, brand);
		};


		Thread t1 = new Thread(runnableST1, "t1");
		Thread t2 = new Thread(runnableST2, "t2");
		Thread t3 = new Thread(runnableST3, "t3");

		t1.start();
		t2.start();
		t3.start();

		log.info("done!");
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
	private void test04_CacheGateway_B() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		personCacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// done
		log.info("done!");
	}



	private void test03_CacheGateway_A() {
		String name = null;
		int age = 0;
		float height = 0.0f;
		boolean militaryEnabled = false;
		PersonPojo person = null;


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, true);
		log.info("name: {}, person: {}", name, person);


		// clear cache
		personCacheService.clearPersonCache();


		// after executing clearCache, attempt getting values again


		// Person name:"One" exists
		// Thread should enter CacheService ONLY FIRST TIME, to cache value
		name = "One";
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 11, 1.1f, true);
		log.info("name: {}, person: {}", name, person);

		name = "Two";
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 12, 1.2f, false);
		log.info("name: {}, person: {}", name, person);


		// Person name:"Seven" does not exist
		name = "Seven";
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, false);
		log.info("name: {}, person: {}", name, person);
		person = personCacheGatewayService.getPerson(name, 17, 1.7f, true);
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
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);

		// Beetle does NOT exist in InfoService
		// Thread should ALWAYS enter CacheService class if 'unless = "#result == null"' (veto) is set into @Cacheable
		model = "Beetle";
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
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
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
		log.info("model: {}, brand: {}", model, brand);
		brand = infoCacheService.getBrandByModelCacheSyncTrue(model);
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
