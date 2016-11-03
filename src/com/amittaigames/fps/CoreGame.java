package com.amittaigames.fps;

public abstract class CoreGame {

	/**
	 * Used for initialization within Window class
	 */
	public abstract void init();

	/**
	 * Used for rendering within the implementation of a CoreGame
	 * @param r Render object to be used
	 */
	public abstract void render(Render r);

	/**
	 * Used for controlled updating within the Window class
	 * @param delta Multiplier to keep transformations separate from frame rate
	 */
	public abstract void update(float delta);

	/**
	 * Used for cleaning up OpenGL buffers and objects that use them
	 */
	public abstract void cleanUp();

}
