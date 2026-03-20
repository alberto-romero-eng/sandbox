package localhost.sandbox.jse17.A0Helper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
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


	private static int READ_TIMEOUT_SECS_DEFAULT = 30;


	public static void main() {
		System.out.println("Hello from HttpClientHelper!");
		test00();
		test01();
		System.out.println("done!");
	}


	public static void test00() {
		System.out.println("test00 -- begin");
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
		Integer readTimeoutSecs = 30;
		HttpResponse<String> httpResponse = HCH.execute(
				httpMethod,
				baseUrl,
				apiPath,
				urlQueryParams,
				headers,
				requestBody,
				readTimeoutSecs);
		HCH.SystemPrintResponse(httpResponse);
		System.out.println("test00 -- end");
	}


	public static void test01() {
		System.out.println("test01 -- begin");
		HttpMethod httpMethod = null;
		String baseUrl = "https://postman-echo.com/get";
		String apiPath = null;
		Map<String,String> urlQueryParams = null;
		Map<String,String> headers = null;
		String requestBody = null;
		Integer readTimeoutSecs = null;
		HttpResponse<String> httpResponse = HCH.execute(
				httpMethod,
				baseUrl,
				apiPath,
				urlQueryParams,
				headers,
				requestBody,
				readTimeoutSecs);
		HCH.SystemPrintResponse(httpResponse);
		System.out.println("test01 -- end");
	}



	/**
	 * <p>Simple synchronous (blocking) implementation of {@link HttpClient}.
	 * 
	 * <p>In the simplest case, all parameters excepting <i>baseUrl</i> can be null.
	 * 
	 * @author Alberto Romero
	 * @since 2026-03-19
	 */
	public static HttpResponse<String> execute (
			HttpMethod httpMethod,
			String baseUrl,
			String apiPath,
			Map<String,String> urlQueryParams,
			Map<String,String> headers,
			String requestBody,
			Integer readTimeoutSecs ) {

		// validation, baseUrl
		if (baseUrl == null || baseUrl.isEmpty() || baseUrl.isBlank()) {
			return null;
		}

		// request, httpMethod
		if (httpMethod == null) {
			httpMethod = HttpMethod.GET;
		}

		// request, apiPath
		if (apiPath == null || apiPath.isBlank()) {
			apiPath = "";
		}

		// request, urlQueryParams
		String urlQueryString = "";
		if (urlQueryParams != null && !urlQueryParams.isEmpty()) {
			urlQueryString = UQPH.toUrlQueryString(urlQueryParams);
		}

		// request, URI
		URI uri = URI.create(baseUrl + apiPath + urlQueryString);

		// request, headers
		if (headers == null) {
			headers = Collections.emptyMap();
		}

		// request, body
		if (requestBody == null) {
			requestBody = "";
		}

		// request, readTimeoutSecs
		if (readTimeoutSecs == null || readTimeoutSecs <= 0) {
			readTimeoutSecs = READ_TIMEOUT_SECS_DEFAULT;
		}

		// HttpRequest, build
		HttpRequest.Builder hrBuilder = HttpRequest.newBuilder();

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

		hrBuilder.uri(uri);

		for (String hKey : headers.keySet()) {
			hrBuilder.header(hKey, headers.get(hKey));
		}

		hrBuilder.timeout(Duration.of(600, ChronoUnit.SECONDS));

		HttpRequest httpRequest = hrBuilder.build();

		// HttpClient, instance
		HttpClient client = HttpClient.newHttpClient();

		// HttpResponse
		HttpResponse<String> httpResponse = null;

		// send the request synchronously (blocking), receive response
		try {
			httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		return httpResponse;
	}


	public static void SystemPrintResponse (HttpResponse<String> httpResponse) {
		if (httpResponse == null) {
			System.out.println("httpResponse is null");
			return;
		}
		System.out.println("httpResponse: " + httpResponse);
		System.out.println("httpResponse.request: " + httpResponse.request());
		System.out.println("httpResponse.request.headers: " + httpResponse.request().headers());
		System.out.println("httpResponse.request.bodyPublisher.isPresent: " + httpResponse.request().bodyPublisher().isPresent());
		if (httpResponse.request().bodyPublisher().isPresent()) {
			System.out.println("httpResponse.request.bodyPublisher.get: " + httpResponse.request().bodyPublisher().get());
		}
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
