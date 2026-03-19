package localhost.sandbox.jse17.HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TestHttpClient00 {

	public static void test00_GetHtml () {
		// 1. Create an HttpClient instance
		HttpClient client = HttpClient.newHttpClient();

		// 2. Build an HttpRequest
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://example.com"))
				.GET() // or POST, PUT, DELETE, etc.
				.build();

		// 3. Send the request synchronously and receive the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// 4. Access the response details
		System.out.println("response.statusCode: " + response.statusCode());
		System.out.println("response.body: " + response.body());
	}



	public static void test01_GetRest () {
		// 1. Create an HttpClient instance
		HttpClient client = HttpClient.newHttpClient();

		// 2. Build an HttpRequest
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://postman-echo.com/get?reqp01=value01"))
				.GET()
				.build();

		// 3. Send the request synchronously and receive the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// 4. Access the response details
		System.out.println("response.statusCode: " + response.statusCode());
		System.out.println("response.body: " + response.body());
	}


	public static void test02_PostRest () {
		// 1. Create an HttpClient instance
		HttpClient client = HttpClient.newHttpClient();

		// 2. Build an HttpRequest
		String reqBody = "{\"field01\":\"fieldValue01\"}";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://postman-echo.com/post?uReqP01=uReqValue01"))
				.header("content-type", "application/json")
				.header("header01", "headerValue01")
				.headers("header02", "headerValue02", "header03", "headerValue03")
				.POST(HttpRequest.BodyPublishers.ofString(reqBody))
				.timeout(Duration.of(600, ChronoUnit.SECONDS))
				.build();

		// 3. Send the request synchronously and receive the response
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}

		// 4. Access the response details
		System.out.println("response: " + response);
		System.out.println("response.request: " + response.request());
		System.out.println("response.request.headers: " + response.request().headers());
		System.out.println("response.request.bodyPublisher.get: " + response.request().bodyPublisher().get());
		System.out.println("response.statusCode: " + response.statusCode());
		System.out.println("response.body: " + response.body());
	}

}
