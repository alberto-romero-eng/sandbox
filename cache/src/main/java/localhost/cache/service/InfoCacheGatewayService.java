package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


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
public class InfoCacheGatewayService {

	private static Logger log = LoggerFactory.getLogger(InfoCacheGatewayService.class);

	/**
	 * As there is no initialization method for this class, this attribute is final.
	 */
	private static final CacheForGetContinent CACHE_FOR_GET_CONTINENT_DEFAULT = CacheForGetContinent.NONE;

	/**
	 * As there is no initialization method for this class, this attribute is final.
	 */
	private static final CacheForGetBrand CACHE_FOR_GET_BRAND_DEFAULT = CacheForGetBrand.NONE;

	@Autowired
	private InfoService infoService;

	@Autowired
	private InfoCacheService infoCacheService;

	public static enum CacheForGetContinent {
		NONE,
		CACHE_DEFAULT
	}

	public static enum CacheForGetBrand {
		NONE,
		CACHE_DEFAULT
	}

	public String getContinentByCountry(String country, CacheForGetContinent cacheForGetContinent) {
		// waitForInitialization();
		String resContinent = null;
		try {
			switch (cacheForGetContinent) {
			case CACHE_DEFAULT:
				resContinent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
			case NONE:
			default:
				resContinent = infoService.getContinentByCountry(country);

			}
		} catch (Throwable ex) {
			log.error("Finish getContinent() -- params -- country: {}, cacheForGetContinent: {} -- results -- exception: ", country, cacheForGetContinent, ex);
			return null;
		}

		log.info("Finish getContinent() -- params -- country: {}, cacheForGetContinent: {} -- results -- resContinent: {}", country, cacheForGetContinent, resContinent);
		return resContinent;
	}

	public String getContinentByCountry(String country) {
		// waitForInitialization();
		String resContinent = null;
		resContinent = getContinentByCountry(country, CACHE_FOR_GET_CONTINENT_DEFAULT);
		log.info("Finish getContinent() -- params -- country: {}, CACHE_FOR_GET_CONTINENT_DEFAULT: {} -- results -- resContinent: {}", country, CACHE_FOR_GET_CONTINENT_DEFAULT, resContinent);
		return resContinent;
	}



	public String getBrandByModel(String model, CacheForGetBrand cacheForGetBrand) {
		// waitForInitialization();
		String resBrand = null;
		try {
			switch (cacheForGetBrand) {
			case CACHE_DEFAULT:
			default:
				resBrand = infoCacheService.getBrandByModelCacheSyncTrue(model);
			case NONE:
				resBrand = infoService.getBrandByModel(model);
			}
		} catch (Throwable ex) {
			log.error("Finish getBrandByModel() -- params -- model: {}, cacheForGetBrand: {} -- results -- exception: ", model, cacheForGetBrand, ex);
			return null;
		}
		log.info("Finish getBrandByModel() -- params -- model: {}, cacheForGetBrand: {} -- results -- resBrand: {}", model, cacheForGetBrand, resBrand);
		return resBrand;
	}

	public String getBrandByModel(String model) {
		// waitForInitialization();
		String resBrand = null;
		resBrand = getBrandByModel(model, CACHE_FOR_GET_BRAND_DEFAULT);
		log.info("Finish getBrandByModel() -- params -- model: {}, CACHE_FOR_GET_BRAND_DEFAULT: {} -- results -- resBrand: {}", model, CACHE_FOR_GET_BRAND_DEFAULT, resBrand);
		return resBrand;
	}

}
