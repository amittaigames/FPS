package com.amittaigames.fps;

public class Game extends CoreGame {

	private Model cube;

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
		cube = new Model(0, 0, -5, "/models/Cube.obj");
		cube.setColor(0, 255, 128);
	}

	/**
	 * @see CoreGame#render(Render)
	 * @param r Render object to be used
	 */
	@Override
	public void render(Render r) {
		r.clear(0, 0, 0);
		r.drawModel(cube);
	}

	/**
	 * @see CoreGame#update(float)
	 * @param delta Multiplier to keep transformations separate from frame rate
	 */
	@Override
	public void update(float delta) {
		cube.rotate(2.5f * delta, 1, 1, 1);
	}

	/**
	 * @see CoreGame#cleanUp()
	 */
	@Override
	public void cleanUp() {
		cube.delete();
	}

}