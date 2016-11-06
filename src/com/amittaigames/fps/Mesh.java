package com.amittaigames.fps;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;

public class Mesh {

	private float[] position;
	private float[] color;
	private float[] normals;

	private int vPos;
	private int vColor;
	private int vNormal;

	/**
	 * Container for rendering objects
	 * @param position vertices for position
	 * @param color vertices for color
	 * @param normals vertices for normals
	 */
	public Mesh(float[] position, float[] color, float[] normals) {
		this.position = position;
		this.color = color;
		this.normals = normals;
		init();
	}

	/**
	 * Initializes OpenGL VBOs
	 */
	private void init() {
		this.vPos = glGenBuffers();
		updatePosition(this.position);

		this.vColor = glGenBuffers();
		updateColor(this.color);

		// Probably won't update normals
		this.vNormal = glGenBuffers();
		FloatBuffer nbuf = Buffers.createFloatBuffer(this.normals);
		glBindBuffer(GL_ARRAY_BUFFER, this.vNormal);
		glBufferData(GL_ARRAY_BUFFER, nbuf, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Update position vertices (e.g., translate mesh)
	 * @param pos Position vertices
	 */
	public void updatePosition(float[] pos) {
		FloatBuffer buf = Buffers.createFloatBuffer(pos);
		glBindBuffer(GL_ARRAY_BUFFER, this.vPos);
		glBufferData(GL_ARRAY_BUFFER, buf, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Update color vertices
	 * @param color Color vertices
	 */
	public void updateColor(float[] color) {
		FloatBuffer buf = Buffers.createFloatBuffer(color);
		glBindBuffer(GL_ARRAY_BUFFER, this.vColor);
		glBufferData(GL_ARRAY_BUFFER, buf, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public float[] getPosition() {
		return position;
	}

	public float[] getColor() {
		return color;
	}

	public float[] getNormals() {
		return normals;
	}

	public int getPositionBuffer() {
		return vPos;
	}

	public int getColorBuffer() {
		return vColor;
	}

	public int getNormalBuffer() {
		return vNormal;
	}

}
