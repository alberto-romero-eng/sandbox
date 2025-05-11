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

	public String getContinentByCountry(String country) {
		String continent = null;
		continent = continentMap.get(country);
		log.info("Finish getContinentByCountry() -- params -- country: {} -- results -- continent: {}", country, continent);
		return continent;
	}

	public String getBrandByModel(String model) {
		String brand = null;
		brand = brandMap.get(model);
		log.info("Finish getBrandByModel() -- params -- model: {} -- results -- brand: {}", model, brand);
		return brand;
	}

}
