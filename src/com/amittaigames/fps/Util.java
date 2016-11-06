package com.amittaigames.fps;

import java.io.*;

public class Util {

	/**
	 * Reads file contents as resource
	 * @param path Path to resource
	 * @return String of file contents
	 */
	public static String loadResourceContents(String path) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream(path)));
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}