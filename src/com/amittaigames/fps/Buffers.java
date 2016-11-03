package com.amittaigames.fps;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffers {

	/**
	 * Creates a FloatBuffer to be sent to OpenGL
	 * @param data - Float array to be converted
	 * @return - FloatBuffer for OpenGL
	 */
	public static FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buf = BufferUtils.createFloatBuffer(data.length);
		buf.put(data).flip();
		return buf;
	}

	/**
	 * Creates an IntBuffer to be sent to OpenGL
	 * @param data - Integer array to be converted
	 * @return - IntBuffer for OpenGL
	 */
	public static IntBuffer createIntBuffer(int[] data) {
		IntBuffer buf = BufferUtils.createIntBuffer(data.length);
		buf.put(data).flip();
		return buf;
	}

}
