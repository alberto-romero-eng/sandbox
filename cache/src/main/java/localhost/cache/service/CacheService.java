package localhost.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import localhost.cache.service.PersonService.PersonPojo;


/**
 * <p>Class to contain methods annotated with {@link CacheEvict} and {@link Cacheable}.
 * 
 * <p>Wrapper methods, to ease logging or troubleshooting, should not be in this class.
 * 
 * @see CacheWrapperService
 * @author Alberto Romero
 * @since 2025-05-25
 * 
 */
@Service
public class CacheService {

	private static Logger log = LoggerFactory.getLogger(CacheService.class);

	@Autowired
	InfoService infoService;

	@Autowired
	PersonService personService;



	@CacheEvict(cacheNames = {"continent"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearContinentCache() {
		log.info("Finish clearContinentCache()");
	}

	@CacheEvict(cacheNames = {"brand"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearBrandCache() {
		log.info("Finish clearBrandCache()");
	}

	@CacheEvict(cacheNames = {"person"}, allEntries = true) // adding more cache-names is possible, Strings separated by comma
	public void clearPersonCache() {
		log.info("Finish clearPersonCache()");
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

	/**
	 * If 'sync=true', 'unless' cannot be used.  See {@link Cacheable#sync()} reference.
	 */
	@Cacheable(value="person", /*sync = true,*/ unless = "#result == null")
	public PersonPojo getPerson(String name, int age, float height, boolean militaryEnabled) {
		PersonPojo resPerson = null;
		resPerson = personService.getPerson(name, age, height, militaryEnabled);
		log.info("Finish getPerson() -- params -- name: {}, age: {}, height: {}, militaryEnabled: {} -- results -- resPerson: {}", name, age, height, militaryEnabled, resPerson);
		return resPerson;
	}

}
