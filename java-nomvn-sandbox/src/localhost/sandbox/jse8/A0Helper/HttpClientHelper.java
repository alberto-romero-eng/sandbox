package localhost.sandbox.jse8.A0Helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
// import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class HttpClientHelper {

	public static void main() {

		System.out.println("Hello from HttpClientHelper main!");

		int connectTimeoutMs = 30 * 1000;
		int readTimeoutMs = 120 * 1000;

		String userAgent = "Java-HttpClientHelper";

		LinkedHashMap<String,String> paramsMap = new LinkedHashMap<>();

		String query = paramsMap.entrySet().stream()
				.map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
				.reduce((p1, p2) -> p1 + "&" + p2)
				.orElse("");

		String path = "";

		String urlStr = "https://example.com" + "/" + path + "?" + "&" + query;

		String postData = null; // GET
		// postData = "key1=value1" + "&" + "key2=value2"; // POST

		int responseCode = -1;
		String fullResponseContent1 = null;
		String fullResponseContent2 = null;

		try {
			URL url = new URL(urlStr);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); // HttpURLConnection
			conn.setUseCaches(false);
			conn.setRequestProperty("User-Agent", userAgent);
			conn.setRequestProperty("Connection", "keep-alive");
			// conn.setRequestProperty("Accept-Encoding", "gzip");
			conn.setConnectTimeout(connectTimeoutMs);
			conn.setReadTimeout(readTimeoutMs);
			if (postData != null) {
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				conn.setDoOutput(true);
				try (OutputStream os = conn.getOutputStream()) {
					os.write(postData.getBytes(StandardCharsets.UTF_8));
				}
			} else {
				conn.setRequestMethod("GET");
			}

			responseCode = conn.getResponseCode();

			/* if (responseCode == 200) {
				try (InputStream is = con.getInputStream();
						GZIPInputStream gis = new GZIPInputStream(is)) {
					JsonReader reader = new JsonReader(new InputStreamReader(gis, "UTF-8"));
					response = gson.fromJson(reader, Response.class);
					response.status = ResponseStatus.OK;
				} catch (Exception ex) {
					response = new Response();
					response.status = ResponseStatus.FAIL;
					response.exception = ex;
				}

			} else {
				try (InputStream is = con.getErrorStream();
						GZIPInputStream gis = new GZIPInputStream(is)) {
					JsonReader reader = new JsonReader(new InputStreamReader(gis, "UTF-8"));
					response = gson.fromJson(reader, Response.class);
				} catch (Exception e) {
					response = new Response();
					response.status = ResponseStatus.FAIL;
					response.exception = e;
				} 
			} */

			try (InputStream is = conn.getInputStream()) {

				/*BufferedInputStream bis = new BufferedInputStream(is);
				int available = bis.available();
				int readInt = 65;
				byte[] bArray = null;
				String s = new String(bArray, StandardCharsets.UTF_8);
				System.out.println("s: " + s);*/

				// fullResponseContent1 = getFullResponseContentByBufferedByteArrayOutputStream(is);
				fullResponseContent2 = getFullResposeContentByBufferedReader(is);


			} catch (Exception ex) {
				System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
			}

		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
		}

		System.out.println("responseCode: " + responseCode);
		System.out.println("fullResponseContent1: ");
		System.out.println(fullResponseContent1);
		System.out.println("fullResponseContent2: ");
		System.out.println(fullResponseContent2);
	}


	public static String urlEncodeUTF8(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}


	private static String getFullResponseContentByBufferedByteArrayOutputStream(InputStream is) throws Exception {
		String fullResponseContent = null;
		// InputStream is = new ByteArrayInputStream(new byte[] { 0, 1, 2, 3, 4, 5, 6 }); // not really known
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int readByte;
		while ((readByte = is.read()) != -1) {
			buffer.write(readByte);
		}
		buffer.flush();
		byte[] targetArray = buffer.toByteArray();
		fullResponseContent = new String(targetArray, StandardCharsets.UTF_8);
		return fullResponseContent;
	}


	private static String getFullResposeContentByBufferedReader(InputStream is) throws Exception {
		String fullResponseContent = "";
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		String line = bReader.readLine();
		while (line != null) {
			fullResponseContent += line + "\n";
			line = bReader.readLine();
		}
		if (fullResponseContent.isEmpty()) {
			return null;
		} else {
			return fullResponseContent;
		}
	}
}
