package localhost.sandbox.jse8.A0Helper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * TODO review
 * @since 2025-12-11
 */
public class CountryIsoCodeHelper {

	/**
	 * TODO review
	 * @since 2025-12-11
	 */
	public static String getCountryISOCode(List<String> countries) {
		for(String country: countries) {
			if(country!=null) {
				if(Arrays.asList(Locale.getISOCountries()).contains(country.toUpperCase())) {
					return country;
				}
				for (Locale locale : Locale.getAvailableLocales()) {
					if (locale.getDisplayCountry(new Locale("es")).equalsIgnoreCase(country) || locale.getDisplayCountry(Locale.ENGLISH).equalsIgnoreCase(country)) {
						return locale.getCountry();
					}
				}
			}
		}
		return null;
	}

}
