package com.amittaigames.fps;

public class Game extends CoreGame {

	/**
	 * Main method for entry
	 * @param args Not used
	 */
	public static void main(String[] args) {
		Window.init(800, 600, "Game Stuff", new Game());
	}

	/**
	 * @see CoreGame#init()
	 */
	@Override
	public void init() {

	}

	/**
	 * @see CoreGame#render(Render)
	 * @param r Render object to be used
	 */
	@Override
	public void render(Render r) {
		r.clear(0, 0, 0);
	}

	/**
	 * @see CoreGame#update(float)
	 * @param delta Multiplier to keep transformations separate from frame rate
	 */
	@Override
	public void update(float delta) {

	}

	/**
	 * @see CoreGame#cleanUp()
	 */
	@Override
	public void cleanUp() {

	}

}