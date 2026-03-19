package localhost.sandbox.jse17.HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import localhost.sandbox.jse17.A0Helper.UrlQueryParamsHelper.UQPH;

public class TestHttpClient00 {

	public static void test00_GetHtml () {
		// Create an HttpClient instance
		HttpClient client = HttpClient.newHttpClient();

		// Build an HttpRequest
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://example.com"))
				.GET() // or POST, PUT, DELETE, etc.
				.build();

		// Send the request synchronously and receive the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// Access the response details
		System.out.println("response.statusCode: " + response.statusCode());
		System.out.println("response.body: " + response.body());
	}



	public static void test01_GetRest () {
		// Create an HttpClient instance
		HttpClient client = HttpClient.newHttpClient();

		// Build an HttpRequest
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://postman-echo.com/get?urlParamKey01=urlParamValue01&urlParamKey02=urlParamValue02"))
				.GET()
				.header("content-type", "application/json")
				.headers("headerKey01", "headerValue01", "headerKey02", "headerValue02")
				.build();

		// Send the request synchronously and receive the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// Access the response details
		System.out.println("response.statusCode: " + response.statusCode());
		System.out.println("response.body: " + response.body());
	}


	public static void test02_PostRest () {
		// HttpClient, instance
		HttpClient client = HttpClient.newHttpClient();

		// Request, URI
		String baseUrl = "https://postman-echo.com";
		String apiPath = "/post";
		Map<String,String> urlQueryParams = new LinkedHashMap<>();
		urlQueryParams.put("uqParamKey01", "uqParamValue01");
		urlQueryParams.put("uqParamKey02", "uqParamValue02");
		String urlQueryString = UQPH.toUrlQueryString(urlQueryParams);
		URI uri = URI.create(baseUrl + apiPath + urlQueryString);

		// Request, headers
		Map<String,String> headers = new LinkedHashMap<>();
		headers.put("content-type", "application/json");
		headers.put("headerKey01", "headerValue01");
		headers.put("headerKey02", "headerValue02");

		// Request, body
		String reqBody = "{\"field01\":\"fieldValue01\"}";

		// HttpRequest, build
		HttpRequest.Builder hrBuilder = HttpRequest.newBuilder()
				.uri(uri)
				.POST(HttpRequest.BodyPublishers.ofString(reqBody))
				.timeout(Duration.of(600, ChronoUnit.SECONDS));
		headers.keySet().forEach( key -> {
			hrBuilder.header(key, headers.get(key));
		});
		HttpRequest httpRequest = hrBuilder.build();

		// Send the request synchronously and receive the response
		HttpResponse<String> httpResponse = null;
		try {
			httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// Access the response details
		System.out.println("httpResponse: " + httpResponse);
		System.out.println("httpResponse.request: " + httpResponse.request());
		System.out.println("httpResponse.request.headers: " + httpResponse.request().headers());
		System.out.println("httpResponse.request.bodyPublisher.get: " + httpResponse.request().bodyPublisher().get());
		System.out.println("httpResponse.statusCode: " + httpResponse.statusCode());
		System.out.println("httpResponse.body: " + httpResponse.body());
	}

}
