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
public class InfoCacheWrapperService {

	private static Logger log = LoggerFactory.getLogger(InfoCacheWrapperService.class);

	@Autowired
	private InfoCacheService infoCacheService;


	/**
	 * <p>Wrapper for {@link InfoCacheService#getContinentByCountryCacheSyncFalseUnlessResultNull(String)}.
	 * 
	 * @see InfoCacheWrapperService
	 * @see InfoCacheService
	 * @author Alberto Romero
	 * @since 2025-05-25
	 * 
	 */
	public String getContinentByCountry(String country) {
		// waitForInitialization();
		String resContinent = null;
		resContinent = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull(country);
		log.info("Finish getContinent() -- params -- country: {} -- results -- resContinent: {}", country, resContinent);
		return resContinent;
	}

	public String getBrandByModel(String model) {
		// waitForInitialization();
		String resBrand = null;
		resBrand = infoCacheService.getBrandByModelCacheSyncFalseUnlessResultNull(model);
		log.info("Finish getBrandByModel() -- params -- Model: {} -- results -- resBrand: {}", model, resBrand);
		return resBrand;
	}


}
