/**
 * 
 */
package io.crs.hsys.server.service.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import io.crs.hsys.shared.dto.chat.NotificationDto;

/**
 * @author robi
 *
 */
public class FcmService2 {
	private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
//	private static final String FCM_URL = "https://fcm.googleapis.com/v1/projects/hw-cloud3/messages:send";
	private static final String SERVER_KEY = "AAAAGAt6I4o:APA91bF4FBaCBtd9s6_ASAd0RaYiFFx6eb0TurFomRI26cmDwEiG_3OH5ax4PdJSxK-ZVkl_Y6fDv_L-9o-ti-rlfwQwCZ6yq-SklwQF7LEgwJEihJjyLF4p2iUPgtECgR0be4NJsZ0b";

	/**
	 *
	 * Method to send push notification to Android FireBased Cloud messaging Server.
	 *
	 * @param tokenId
	 *            Generated and provided from Android Client Developer
	 * @param message
	 *            which contains actual information.
	 *
	 */
	public static void send_FCM_Notification(String tokenId, NotificationDto message) {
		try {
			// Create URL instance.
			URL url = new URL(FCM_URL);

			// create connection.
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// set method as POST or GET
			conn.setRequestMethod("POST");

			// pass FCM server key
			conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);

			// Specify Message Format
			conn.setRequestProperty("Content-Type", "application/json");

			// Create JSON Object & pass value
			JSONObject notificationJson = new JSONObject();
			notificationJson.put("icon", message.getIcon());
			notificationJson.put("title", message.getTitle());
			notificationJson.put("body", message.getBody());
			notificationJson.put("click_action", message.getAction());

//			JSONObject dataJson = new JSONObject();
//			dataJson.put("notification", notificationJson);

			JSONObject messageJson = new JSONObject();
			messageJson.put("data", notificationJson);
			messageJson.put("to", tokenId.trim());

			System.out.println("json.toString()=" + messageJson.toString());

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			wr.write(messageJson.toString());
			wr.flush();

			int status = 0;

			if (null != conn) {
				status = conn.getResponseCode();
			}

			if (status != 0) {
				if (status == 200) {
					// SUCCESS message
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					System.out.println("Notification Response : " + reader.readLine());
				} else if (status == 401) {
					// client side error
					System.out.println("Notification Response : TokenId : " + tokenId + " Error occurred :");
				} else if (status == 501) {
					// server side error
					System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + tokenId);
				} else if (status == 503) {
					// server side error
					System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);
				}
			}
		} catch (MalformedURLException mlfexception) {
			// Prototcal Error
			System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (IOException mlfexception) {
			// URL problem
			System.out.println(
					"Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (JSONException jsonexception) {
			// Message format error
			System.out.println(
					"Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());
		} catch (Exception exception) {
			// General Error or exception.
			System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());
		}
	}
}
