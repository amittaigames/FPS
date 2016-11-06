package com.amittaigames.fps;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

	private static long window;

	private static CoreGame game;
	private static Render render;

	/**
	 * Creates a single-instance window for the game to be rendered on
	 * @param width Window width
	 * @param height Window height
	 * @param title Window title
	 * @param game CoreGame implementation
	 */
	public static void init(int width, int height, String title, CoreGame game) {
		if (!glfwInit()) {
			System.err.println("Could not create GLFW instance!");
			System.exit(1);
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		window = glfwCreateWindow(width, height, title, 0, 0);
		if (window == 0) {
			System.err.println("Could not create window!");
			glfwTerminate();
			System.exit(1);
		}

		glfwMakeContextCurrent(window);
		GL.createCapabilities();

		setupOpenGL(60.0f, (float)width/(float)height, 0.1f, 1000f);

		Input.init(window);

		Window.game = game;
		Window.render = new Render();

		start();
	}

	/**
	 * Sets up OpenGL for rendering (params are for frustum calculations)
	 * @param fov Field of view
	 * @param aspect Aspect ratio (width/height)
	 * @param zNear Z near rendering plane
	 * @param zFar Z far rendering plane
	 */
	private static void setupOpenGL(float fov, float aspect, float zNear, float zFar) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		// Replacement for gluPerspective();
		float fH = (float) Math.tan(fov / 360 * Math.PI) * zNear;
		float fW = fH * aspect;
		glFrustum(-fW, fW, -fH, fH, zNear, zFar);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glShadeModel(GL_SMOOTH);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glEnable(GL_NORMALIZE);

		glEnable(GL_LIGHTING);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHT0);
	}

	/**
	 * Starts CoreGame initialization and game loop
	 */
	private static void start() {
		game.init();

		double now;
		double last;
		float delta = 1;

		while (!glfwWindowShouldClose(window)) {
			last = glfwGetTime();

			glfwSwapBuffers(window);
			glfwPollEvents();

			game.render(render);
			game.update(delta);

			now = glfwGetTime();
			delta = (float)(now - last) * 10.0f;
		}

		game.cleanUp();

		glfwDestroyWindow(window);
		glfwTerminate();

		System.exit(0);
	}

}
