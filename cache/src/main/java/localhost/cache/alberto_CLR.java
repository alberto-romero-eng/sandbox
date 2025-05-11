package localhost.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import localhost.cache.service.CacheService;


@Component
public class alberto_CLR implements CommandLineRunner{

	@Autowired
	CacheService cacheService;

	private static Logger log = LoggerFactory.getLogger(alberto_CLR.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello from albertoCLR");

		// tests
		test01();

	}

	private void test01() {
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
