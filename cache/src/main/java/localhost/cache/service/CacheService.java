package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	private static Logger log = LoggerFactory.getLogger(CacheService.class);

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
	 * If 'sync=true', 'unless' cannot be used.  See {@link Cacheable#sync()} reference.
	 */
	@Cacheable(value="continent", /*sync = true,*/ unless = "#result == null")
	public String getContinentByCountryCacheWithUnlessIsNull(String country) {
		String continent = null;
		continent = infoService.getContinentByCountry(country);
		log.info("Finish getContinentByCountryCacheSimple() -- params -- country: {} -- results -- continent: {}", country, continent);
		return continent;
	}

	/**
	 * If 'sync=true', 'unless' cannot be used.  See {@link Cacheable#sync()} reference.
	 */
	@Cacheable(value="brand", /*sync = true,*/ unless = "#result == null")
	public String getBrandByModelCacheWithUnlessIsNull(String model) {
		String brand = null;
		brand = infoService.getBrandByModel(model);
		log.info("Finish getBrandByModelCacheSimple() -- params -- country: {} -- results -- continent: {}", model, brand);
		return brand;
	}

}
