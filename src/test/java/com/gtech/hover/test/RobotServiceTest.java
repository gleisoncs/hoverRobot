package com.gtech.hover.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class RobotServiceTest {

	@Test
	public void getPosition() {
		try {
			URL url = new URL("http://localhost:8080/robot1/rest/mars/MRMRMR");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "text/plain");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = br.readLine();
			Assert.assertEquals(output, "(1, 0, W)");

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
