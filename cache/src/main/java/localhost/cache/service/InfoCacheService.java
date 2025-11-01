package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import localhost.cache.configuration.CacheNameDefn.CacheName;



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
public class InfoCacheService {

	private static Logger log = LoggerFactory.getLogger(InfoCacheService.class);

	@Autowired
	InfoService infoService;



	@CacheEvict(cacheNames = { CacheName.CONTINENT_SYNC_FALSE }, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearContinentCache() {
		log.info("Finish clearContinentCache()");
	}

	@CacheEvict(cacheNames = { CacheName.BRAND_SYNC_TRUE }, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearBrandCache() {
		log.info("Finish clearBrandCache()");
	}

	@CacheEvict(cacheNames = { CacheName.SIMPLEST }, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearSimplestCache() {
		log.info("Finish clearSimplestCache()");
	}



	/**
	 * <p>If 'sync=true', 'unless' cannot be used.  See {@link Cacheable#sync()} reference.
	 * 
	 * <p>For sake of simplification, {@link Cacheable#unless()} can be paraphrased as 'veto'.
	 * 
	 * <p>See {@link Cacheable} reference.
	 * 
	 */
	@Cacheable(value = CacheName.CONTINENT_SYNC_FALSE, sync = false, unless = "#result == null")
	public String getContinentByCountryCacheSyncFalseUnlessResultNull(String country) {
		String continent = null;
		try {
			continent = infoService.getContinentByCountry(country);
		} catch (Throwable ex) {
			log.error("Finish getContinentByCountryCacheSyncFalseUnlessResultNull() -- params -- country: {} -- results -- exception: ", country, ex);
			return null;
		}
		log.info("Finish getContinentByCountryCacheSyncFalseUnlessResultNull() -- params -- country: {} -- results -- continent: {}", country, continent);
		return continent;
	}

	/**
	 * <p> See {@link #getContinentByCountryCacheSyncFalseUnlessResultNull(String)} comment.
	 */
	@Cacheable(value = CacheName.BRAND_SYNC_TRUE, sync = true)
	public String getBrandByModelCacheSyncTrue(String model) {
		String brand = null;
		try {
			brand = infoService.getBrandByModel(model);
		} catch (Throwable ex) {
			log.error("Finish getBrandByModelCacheSyncTrue() -- params -- country: {} -- results -- exception: ", model, ex);
			return null;
		}
		log.info("Finish getBrandByModelCacheSyncTrue() -- params -- model: {} -- results -- continent: {}", model, brand);
		return brand;
	}


	@Cacheable(value = CacheName.SIMPLEST, sync = true)
	public String getSimplestSyncTrue(String anyValue) {
		log.info("Finish getSimplestSyncTrue -- params -- anyValue: {} -- results -- anyValue: {}", anyValue, anyValue);
		return anyValue;
	}


	@Cacheable(value = CacheName.SIMPLEST, sync = false)
	public String getSimplestSyncFalse(String anyValue) {
		log.info("Finish getSimplestSyncFalse -- params -- anyValue: {} -- results -- anyValue: {}", anyValue, anyValue);
		return anyValue;
	}

}
