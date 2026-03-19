package localhost.sandbox.jse17.A0Helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import localhost.sandbox.jse17.A0Helper.UrlQueryParamsHelper.UQPH;

/**
 * <p> Implementation to ease use of Java's-17 {@link HttpClient}.
 * 
 * <p> Requires {@link UrlQueryParamsHelper}.
 * 
 * @author Alberto Romero
 * @since 2026-03-19
 */
public class HttpClientHelper {


	public static void main() {
		System.out.println("Hello from HttpClientHelper!");
		test00();
		System.out.println("done!");
	}


	public static void test00() {
		HttpMethod httpMethod = HttpMethod.POST;
		String baseUrl = "https://postman-echo.com";
		String apiPath = "/post";
		Map<String,String> urlQueryParams = new LinkedHashMap<>();
		urlQueryParams.put("uqParamKey01", "uqParamValue01");
		urlQueryParams.put("uqParamKey02", "uqParamValue02");
		Map<String,String> headers = new LinkedHashMap<>();
		headers.put("content-type", "application/json");
		headers.put("headerKey01", "headerValue01");
		headers.put("headerKey02", "headerValue02");
		String requestBody = "{\"field01\":\"fieldValue01\"}";
		HttpResponse<String> httpResponse = HCH.execute(
				httpMethod,
				baseUrl,
				apiPath,
				urlQueryParams,
				headers,
				requestBody);
		HCH.SystemPrintResponse(httpResponse);
	}


	public static HttpResponse<String> execute (
			HttpMethod httpMethod,
			String baseUrl,
			String apiPath,
			Map<String,String> urlQueryParams,
			Map<String,String> headers,
			String requestBody ) {

		// HttpClient, instance
		HttpClient client = HttpClient.newHttpClient();

		// Request, URI
		String urlQueryString = UQPH.toUrlQueryString(urlQueryParams);
		URI uri = URI.create(baseUrl + apiPath + urlQueryString);

		// Request, headers
		// do nothing

		// Request, body
		// do nothing

		// HttpRequest, build
		HttpRequest.Builder hrBuilder = HttpRequest.newBuilder()
				.uri(uri)
				.timeout(Duration.of(600, ChronoUnit.SECONDS));

		switch (httpMethod) {
		case GET:
			hrBuilder.GET();
			break;
		case POST:
			hrBuilder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
			break;
		case PUT:
			hrBuilder.PUT(HttpRequest.BodyPublishers.ofString(requestBody));
			break;
		case DELETE:
			hrBuilder.DELETE();
			break;
		default:
			hrBuilder.GET();
			break;
		}

		headers.keySet().forEach( key -> {
			hrBuilder.header(key, headers.get(key));
		});
		HttpRequest httpRequest = hrBuilder.build();

		// Send the request synchronously (blocking), receive response
		HttpResponse<String> httpResponse = null;
		try {
			httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		return httpResponse;
	}


	public static void SystemPrintResponse (HttpResponse<String> httpResponse) {
		// Access the response details
		System.out.println("httpResponse: " + httpResponse);
		System.out.println("httpResponse.request: " + httpResponse.request());
		System.out.println("httpResponse.request.headers: " + httpResponse.request().headers());
		System.out.println("httpResponse.request.bodyPublisher.get: " + httpResponse.request().bodyPublisher().get());
		System.out.println("httpResponse.statusCode: " + httpResponse.statusCode());
		System.out.println("httpResponse.body: " + httpResponse.body());
	}


	public enum HttpMethod {
		GET,
		POST,
		PUT,
		DELETE
	}


	/**
	 * Class to aid aliasing for {@link HttpClientHelper}.
	 */
	public static class HCH extends HttpClientHelper {
	}

}
