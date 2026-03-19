package localhost.sandbox.jse8.A0Helper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @author Alberto Romero
 * @since 2026-03-19
 */
public class UrlQueryParamsHelper {

	public static void main() {
		test00();
	}

	private static void test00() {
		Map<String,String> urlQueryParams = new LinkedHashMap<>();
		urlQueryParams.put("param01", "al.ro+store@email.com");
		urlQueryParams.put("param02", "123&456");
		urlQueryParams.put("param03", "789 012");
		System.out.println("urlQueryParams: " + urlQueryParams);
		String urlQueryString = toUrlQueryString(urlQueryParams);
		System.out.println("urlQueryString: " + urlQueryString);
	}


	public static String toUrlQueryString(Map<String,String> urlQueryParams) {
		String urlQueryString = urlQueryParams.keySet().stream()
				.map( (key) -> {
					String encodedValue = "__INITIAL__";
					try {
						encodedValue = URLEncoder.encode(urlQueryParams.get(key), StandardCharsets.UTF_8.name());
					} catch (Throwable ex) {
						encodedValue = "__ERROR__";
					}
					return key + "=" + encodedValue;
				})
				.collect(Collectors.joining("&", "?", ""));
		return urlQueryString;
	}


	/**
	 * Class to aid aliasing for {@link UrlQueryParamsHelper}.
	 */
	public static class UQPH extends UrlQueryParamsHelper {
	}

}
