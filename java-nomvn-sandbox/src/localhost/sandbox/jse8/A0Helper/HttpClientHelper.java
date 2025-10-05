package localhost.sandbox.jse8.A0Helper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
// import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class HttpClientHelper {

	public static void main() {
		System.out.println("Hello from HttpClientHelper main!");

		// test 01
		LinkedHashMap<String, String> paramsMap = new LinkedHashMap<>();
		String query = getQueryString(paramsMap);
		String path = "";
		String urlStr = "https://example.com" + "/" + path + "?" + "&" + query;
		processRequest(urlStr, true, ByteProcessingMethod.BYTE_ARRAY_OUTPUT_STREAM_AS_BUFFER, null);
	}


	public static enum ByteProcessingMethod {
		BYTE_ARRAY_OUTPUT_STREAM_AS_BUFFER,
		BUFFERED_READER
	}


	public static void processRequest(String urlStr, boolean gzipEncodeEnabled, ByteProcessingMethod byteProcessingMethod, String postData) {

		System.out.println("Hello from processRequest!");

		// params
		// boolean gzipEncodeEnabled = false;
		// ByteProcessingMethod byteProcessingMethod = ByteProcessingMethod.BYTE_ARRAY_OUTPUT_STREAM_AS_BUFFER;
		// String postData = null; // GET
		// postData = "key1=value1" + "&" + "key2=value2"; // POST

		int connectTimeoutMs = 30 * 1000;
		int readTimeoutMs = 120 * 1000;

		// process
		String userAgent = "Java-HttpClientHelper";

		int responseCode = -1;
		String fullResponseContent = null;

		try {
			URL url = new URL(urlStr);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); // HttpURLConnection
			conn.setUseCaches(false);
			conn.setRequestProperty("User-Agent", userAgent);
			conn.setRequestProperty("Connection", "keep-alive");
			if (gzipEncodeEnabled) {
				conn.setRequestProperty("Accept-Encoding", "gzip");
			}
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

			if (gzipEncodeEnabled) {
				try (InputStream is = conn.getInputStream() ; // conn.getErrorStream()
						GZIPInputStream gis = new GZIPInputStream(is)) {
					switch (byteProcessingMethod) {
					case BYTE_ARRAY_OUTPUT_STREAM_AS_BUFFER:
						fullResponseContent = getFullResponseContentByBufferedByteArrayOutputStream(gis);
						break;
					case BUFFERED_READER:
						fullResponseContent = getFullResposeContentByBufferedReader(gis);
						break;
					}
				} catch (Exception ex) {
					System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
				}
			} else {
				try (InputStream is = conn.getInputStream()) { // conn.getErrorStream()
					switch (byteProcessingMethod) {
					case BYTE_ARRAY_OUTPUT_STREAM_AS_BUFFER:
						fullResponseContent = getFullResponseContentByBufferedByteArrayOutputStream(is);
						break;
					case BUFFERED_READER:
						fullResponseContent = getFullResposeContentByBufferedReader(is);
						break;
					}
				} catch (Exception ex) {
					System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
				}
			}

		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
		}

		System.out.println("byteProcessingMethod: " + byteProcessingMethod);
		System.out.println("gzipEnabled: " + gzipEncodeEnabled);
		System.out.println("responseCode: " + responseCode);
		System.out.println("fullResponseContent: ");
		System.out.println(fullResponseContent);
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
		int nRead;
		byte[] data = new byte[4];
		while ((nRead = is.read(data, 0, data.length)) != -1) { // ((nRead = is.read()) != -1)
			buffer.write(data, 0, nRead); // buffer.write(nRead);
		}
		buffer.flush();
		byte[] targeBytetArray = buffer.toByteArray();
		fullResponseContent = new String(targeBytetArray, StandardCharsets.UTF_8);
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

	private static String getQueryString(Map<String,String> paramsMap) {
		String query = paramsMap.entrySet().stream()
				.map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
				.reduce((p1, p2) -> p1 + "&" + p2)
				.orElse("");
		return query;
	}
}
