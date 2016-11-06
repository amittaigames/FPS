package com.amittaigames.fps;

import java.util.List;

public class Vertex3f {

	private float x;
	private float y;
	private float z;

	public Vertex3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Convert Vertex3f list to Float array for OpenGL
	 * @param list List of vertices
	 * @return Float array for OpenGL
	 */
	public static float[] convertList(List<Vertex3f> list) {
		int length = list.size() * 3;
		float[] arr = new float[length];

		for (int i = 0; i < length; i += 3) {
			arr[i] = list.get(i / 3).getX();
			arr[i + 1] = list.get(i / 3).getY();
			arr[i + 2] = list.get(i / 3).getZ();
		}

		return arr;
	}

	/**
	 * Translates vertex
	 * @param x How much to translate on the X axis
	 * @param y How much to translate on the Y axis
	 * @param z How much to translate on the Z axis
	 */
	public void translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
