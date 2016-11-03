package com.amittaigames.fps;

import static org.lwjgl.opengl.GL11.*;

public class Render {

	/**
	 * Clears the color and depth buffer
	 * @param r red clear color
	 * @param g green clear color
	 * @param b blue clear color
	 */
	public void clear(int r, int g, int b) {
		glClearColor((float)r/255.0f, (float)g/255.0f, (float)b/255.0f, 1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

}