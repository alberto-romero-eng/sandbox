package localhost.sandbox.jse8.A0Helper;

import java.net.URLEncoder;

public class UrlParamHelper {

	public static void main() {
		String username = "al.ro+store@email.com";
		String password = "1234";
		String urlParams = "username" + "=" + username + "&" + "password" + password;
		System.out.println("urlParams: " + urlParams);

		String encodedUrlParams = null;
		try {
			encodedUrlParams = "username" + "=" + URLEncoder.encode(username, "UTF-8") + "&" + "password" + "=" + URLEncoder.encode(password, "UTF-8");
		} catch (Throwable ex) {
			System.out.println("error attempting URLEncoder.encode()");
		}
		System.out.println("encodedUrlParams: " + encodedUrlParams);
	}

}
