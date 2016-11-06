package com.amittaigames.fps;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private float[] position;
	private float[] color;

	private float x;
	private float y;
	private float z;
	private float width;
	private float height;
	private float depth;

	private float centerX;
	private float centerY;
	private float centerZ;

	private float angle;
	private float rx;
	private float ry;
	private float rz;

	private Mesh mesh;

	/**
	 * Creates a model loaded from an OBJ file
	 * @param x Position X for model
	 * @param y Position Y for model
	 * @param z Position Z for model
	 * @param path Path to load model from (must be OBJ!)
	 */
	public Model(float x, float y, float z, String path) {
		this.x = x;
		this.y = y;
		this.z = z;
		System.out.println("Loading model '" + path + "'");
		loadModel(path);
	}

	/**
	 * Translate the model in world space
	 * @param x How much to translate X
	 * @param y How much to translate Y
	 * @param z How much to translate Z
	 */
	public void translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		for (int i = 0; i < position.length; i += 3) {
			position[i] += x;
			position[i + 1] += y;
			position[i + 2] += z;
		}

		this.centerX = (width / 2.0f) + this.x;
		this.centerY = (height / 2.0f) + this.y;
		this.centerZ = (depth / 2.0f) + this.z;

		mesh.updatePosition(position);
	}

	/**
	 * Rotates the model
	 * @param angle Angle to be added to current angle (degrees)
	 * @param rx Determines rotation for X axis
	 * @param ry Determines rotation for Y axis
	 * @param rz Determines rotation for Z axis
	 */
	public void rotate(float angle, float rx, float ry, float rz) {
		this.angle += angle;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}

	/**
	 * Set model color
	 * @param r Red color component
	 * @param g Green color component
	 * @param b Blue color component
	 */
	public void setColor(int r, int g, int b) {
		for (int i = 0; i < color.length; i += 3) {
			color[i] = (float)r/255.0f;
			color[i + 1] = (float)g/255.0f;
			color[i + 2] = (float)b/255.0f;
		}
		mesh.updateColor(color);
	}

	/**
	 * Actual method for loading in the model data
	 * @param path Path to model
	 */
	private void loadModel(String path) {
		String data = Util.loadResourceContents(path);
		String[] lines = data.split("\n");

		List<Vertex3f> dPosition = new ArrayList<>();
		List<Vertex3f> dNormals = new ArrayList<>();

		List<Vertex3f> position = new ArrayList<>();
		List<Vertex3f> normals = new ArrayList<>();

		for (int i = 0; i < lines.length; i++) {
			String[] args = lines[i].split(" ");

			if (args[0].equals("#") || args[0].equals("o") || args[0].equals("s")) {
				// We don't care about these
				continue;
			}

			if (args[0].equals("v")) {
				float x = Float.parseFloat(args[1]);
				float y = Float.parseFloat(args[2]);
				float z = Float.parseFloat(args[3]);
				dPosition.add(new Vertex3f(x, y, z));
				continue;
			}

			if (args[0].equals("vn")) {
				float x = Float.parseFloat(args[1]);
				float y = Float.parseFloat(args[2]);
				float z = Float.parseFloat(args[3]);
				dNormals.add(new Vertex3f(x, y, z));
				continue;
			}

			if (args[0].equals("f")) {
				for (int j = 1; j < 4; j++) {
					String[] parts = args[j].split("//");
					position.add(dPosition.get(Math.abs(Integer.parseInt(parts[0])) - 1));
					normals.add(dNormals.get(Math.abs(Integer.parseInt(parts[1])) - 1));
				}
			}
		}

		// Get centers
		float minX = 0, maxX = 0, minY = 0, maxY = 0, minZ = 0, maxZ = 0;

		for (int i = 0; i < dPosition.size(); i++) {
			float x = dPosition.get(i).getX();
			float y = dPosition.get(i).getY();
			float z = dPosition.get(i).getZ();

			if (x < minX)
				minX = x;

			if (x > maxX)
				maxX = x;

			if (y < minY)
				minY = y;

			if (y > maxY)
				maxY = y;

			if (z < minZ)
				minZ = z;

			if (z > maxZ)
				maxZ = z;
		}

		this.width = Math.abs(maxX - minX);
		this.height = Math.abs(maxY - minY);
		this.depth = Math.abs(maxZ - minZ);

		this.centerX = (width / 2.0f) + this.x;
		this.centerY = (height / 2.0f) + this.y;
		this.centerZ = (depth / 2.0f) + this.z;

		float[] fposition = Vertex3f.convertList(position);
		float[] fnormals = Vertex3f.convertList(normals);

		for (int i = 0; i < fposition.length; i += 3) {
			fposition[i] += this.x;
			fposition[i + 1] += this.y;
			fposition[i + 2] += this.z;
		}

		float[] colors = new float[fposition.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = 1;
		}

		this.mesh = new Mesh(fposition, colors, fnormals);
		this.position = fposition;
		this.color = colors;
	}

	/**
	 * Deletes mesh data
	 * @see Mesh#delete()
	 */
	public void delete() {
		this.mesh.delete();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getDepth() {
		return depth;
	}

	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}

	public float getCenterZ() {
		return centerZ;
	}

	public float getAngle() {
		return angle;
	}

	public float getRotationX() {
		return rx;
	}

	public float getRotationY() {
		return ry;
	}

	public float getRotationZ() {
		return rz;
	}

	public Mesh getMesh() {
		return mesh;
	}
}
