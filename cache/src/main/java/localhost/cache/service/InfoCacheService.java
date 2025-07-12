package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;




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
public class InfoCacheService {

	private static Logger log = LoggerFactory.getLogger(InfoCacheService.class);

	@Autowired
	InfoService infoService;



	@CacheEvict(cacheNames = {"continent"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearContinentCache() {
		log.info("Finish clearContinentCache()");
	}

	@CacheEvict(cacheNames = {"brand"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearBrandCache() {
		log.info("Finish clearBrandCache()");
	}



	/**
	 * <p>If 'sync=true', 'unless' cannot be used.  See {@link Cacheable#sync()} reference.
	 * 
	 * <p>For sake of simplification, {@link Cacheable#unless()} can be paraphrased as 'veto'.
	 * 
	 * <p>See {@link Cacheable} reference.
	 * 
	 */
	@Cacheable(value = "continent", sync = false, unless = "#result == null")
	public String getContinentByCountryCacheSyncFalseUnlessResultNull(String country) {
		String continent = null;
		continent = infoService.getContinentByCountry(country);
		log.info("Finish getContinentByCountryCacheSyncFalseUnlessResultNull() -- params -- country: {} -- results -- continent: {}", country, continent);
		return continent;
	}

	/**
	 * <p> See {@link #getContinentByCountryCacheSyncFalseUnlessResultNull(String)} comment.
	 */
	@Cacheable(value = "brand", sync = false, unless = "#result == null")
	public String getBrandByModelCacheSyncFalseUnlessResultNull(String model) {
		String brand = null;
		brand = infoService.getBrandByModel(model);
		log.info("Finish getBrandByModelCacheSyncFalseUnlessResultNull() -- params -- country: {} -- results -- continent: {}", model, brand);
		return brand;
	}

}
