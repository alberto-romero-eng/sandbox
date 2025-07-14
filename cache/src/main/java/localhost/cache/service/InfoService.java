package localhost.cache.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

	private static Logger log = LoggerFactory.getLogger(InfoService.class);

	private static HashMap<String,String> continentMap = new HashMap<>();

	private static HashMap<String,String> brandMap = new HashMap<>();

	static {
		// continent
		continentMap.put("Canada", "North America");
		continentMap.put("Colombia", "South America");
		continentMap.put("Spain", "Europe");
		continentMap.put("China", "Asia");
		continentMap.put("Egypt", "Africa");
		continentMap.put("Australia", "Australia");
		// car
		brandMap.put("Neon", "Chrysler");
		brandMap.put("Fiesta", "Ford");
		brandMap.put("Camry", "Toyota");
		brandMap.put("Spark", "Chevrolet");
		brandMap.put("Accent", "Hyundai");
	}

	/**
	 * <p>For methods to have annotation {@link Cacheable}, it is convenient not to throw exception.
	 * 
	 * @see PersonInterface
	 */
	public String getContinentByCountry(String country) {
		if (country == null) {
			return null;
		}
		String continent = null;
		try {
			if ("exception".equalsIgnoreCase(country)) {
				throw new Exception("country is Exception");
			}
			continent = continentMap.get(country);
		} catch (Throwable ex) {
			log.error("Finish getContinentByCountry() -- params -- country: {} -- results -- exception:  ", country, ex);
		}
		log.info("Finish getContinentByCountry() -- params -- country: {} -- results -- continent: {}", country, continent);
		return continent;
	}



	/**
	 * <p>For methods to have annotation {@link Cacheable}, it is convenient not to throw exception.
	 * 
	 * @see PersonInterface
	 */
	public String getBrandByModel(String model) throws Throwable {
		if (model == null) {
			return null;
		}
		String brand = null;
		try {
			if ("exception".equalsIgnoreCase(model)) {
				throw new Exception("country is Exception");
			}
			brand = brandMap.get(model);
		} catch (Throwable ex) {
			log.error("Finish getBrandByModel() -- params -- model: {} -- results -- exception: ", model, ex);
			return null;
		}
		log.info("Finish getBrandByModel() -- params -- model: {} -- results -- brand: {}", model, brand);
		return brand;
	}

}
