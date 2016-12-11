
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



    public class FBConnection{
        
	public static final String APP_ID = "197824393958102";
	public static final String APP_SECRET = "79f2034c304c5220865ee394799b1416";
	public static final String REDIRECT_URI = "http://localhost:8090/Lab_1_WS/callback";

	static String accessToken = "EAACz65S60tYBAEZBU4o0DcLID9E3zoxZCb8Qe9ZCs1U4nKcvQRXptZBl4xZCNtxO2hMGdg3ZBauLLeM8uq7gsf2ofm0Dl8H1al9jF6lRpztZAcHgZA0W8EgZBbwPl10tyoO9l95qlTm7xexBKxBKoUaK5rpGeANdMkvcdhknv4drdx21aubX3ludFFlDXPx0DVa6FxrYTUcqo2gZDZD";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FBConnection.APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FBConnection.APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine).append("\n");
				in.close();
			} catch (IOException e) {
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
}


