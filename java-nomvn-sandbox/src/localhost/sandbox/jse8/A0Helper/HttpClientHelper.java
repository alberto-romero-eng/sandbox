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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class HttpClientHelper {

	public static final Charset CHARSET_DEFAULT = StandardCharsets.UTF_8;



	public static void main() {
		System.out.println("Hello from HttpClientHelper main!");

		// test 01
		LinkedHashMap<String, String> paramsMap = new LinkedHashMap<>();
		String query = getQueryString(paramsMap);
		String path = "";
		String urlStr = "https://example.com" + "/" + path + "?" + "&" + query;
		processRequest(urlStr, true, InputStreamProcessingMethod.READ_ALL_BYTES_BAOS, null);
	}



	public static enum InputStreamProcessingMethod {
		READ_ALL_BYTES_BAOS,
		READ_ALL_LINES
	}



	public static void processRequest(String urlStr, boolean gzipEncodeEnabled, InputStreamProcessingMethod inputStreamProcessingMethod, String postData) {

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
					os.write(postData.getBytes(CHARSET_DEFAULT));
				}
			} else {
				conn.setRequestMethod("GET");
			}

			responseCode = conn.getResponseCode();

			if (gzipEncodeEnabled) {
				try (InputStream is = conn.getInputStream() ; // conn.getErrorStream()
						GZIPInputStream gis = new GZIPInputStream(is)) {
					switch (inputStreamProcessingMethod) {
					case READ_ALL_BYTES_BAOS:
						byte[] targetByteArray = readAllBytesBaos(gis);
						fullResponseContent = new String(targetByteArray, CHARSET_DEFAULT);
						break;
					case READ_ALL_LINES:
						List<String> targetStrList = readAllLines(gis);
						fullResponseContent = "";
						for (String line : targetStrList) {
							fullResponseContent += line + "\n";
						}
						break;
					}
				} catch (Exception ex) {
					System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
				}
			} else {
				try (InputStream is = conn.getInputStream()) { // conn.getErrorStream()
					switch (inputStreamProcessingMethod) {
					case READ_ALL_BYTES_BAOS:
						byte[] targetByteArray = readAllBytesBaos(is);
						fullResponseContent = new String(targetByteArray, CHARSET_DEFAULT);
						break;
					case READ_ALL_LINES:
						List<String> targetStrList = readAllLines(is);
						fullResponseContent = "";
						for (String line : targetStrList) {
							fullResponseContent += line + "\n";
						}
						break;
					}
				} catch (Exception ex) {
					System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
				}
			}

		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage() + ", ex.cause: " + ex.getCause());
		}

		System.out.println("inputStreamProcessingMethod: " + inputStreamProcessingMethod);
		System.out.println("gzipEnabled: " + gzipEncodeEnabled);
		System.out.println("postData: " + postData);
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



	private static byte[] readAllBytesBaos(InputStream is) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[4];
		while ((nRead = is.read(data, 0, data.length)) != -1) { // ((nRead = is.read()) != -1)
			buffer.write(data, 0, nRead); // buffer.write(nRead);
		}
		buffer.flush();
		byte[] targetByteArray = buffer.toByteArray();
		return targetByteArray;
	}



	private static List<String> readAllLines(InputStream is) throws Exception {
		ArrayList<String> targetStrList = new ArrayList<>();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is, CHARSET_DEFAULT));
		String line = bReader.readLine();
		while (line != null) {
			targetStrList.add(line);
			line = bReader.readLine();
		}
		return targetStrList;
	}



	private static String getQueryString(Map<String,String> paramsMap) {
		String query = paramsMap.entrySet().stream()
				.map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
				.reduce((p1, p2) -> p1 + "&" + p2)
				.orElse("");
		return query;
	}

}
