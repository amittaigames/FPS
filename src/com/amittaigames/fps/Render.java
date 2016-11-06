package com.amittaigames.fps;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Render {

	/**
	 * Renders a mesh to the screen
	 * @param mesh The mesh to draw
	 */
	public void drawMesh(Mesh mesh) {
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glBindBuffer(GL_ARRAY_BUFFER, mesh.getPositionBuffer());
		glVertexPointer(3, GL_FLOAT, 0, 0);
		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, mesh.getColorBuffer());
		glColorPointer(3, GL_FLOAT, 0, 0);
		glEnableClientState(GL_COLOR_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, mesh.getNormalBuffer());
		glNormalPointer(GL_FLOAT, 0, 0);
		glEnableClientState(GL_NORMAL_ARRAY);

		glDrawArrays(GL_TRIANGLES, 0, mesh.getPosition().length);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
	}

	/**
	 * Should be used to draw models if rotation is desired
	 * @param model Model to be rendered
	 */
	public void drawModel(Model model) {
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glTranslatef(model.getCenterX(), model.getCenterY(), model.getCenterZ());
		glRotatef(model.getAngle(), model.getRotationX(), model.getRotationY(), model.getRotationZ());
		glTranslatef(-model.getCenterX(), -model.getCenterY(), -model.getCenterZ());

		glBindBuffer(GL_ARRAY_BUFFER, model.getMesh().getPositionBuffer());
		glVertexPointer(3, GL_FLOAT, 0, 0);
		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, model.getMesh().getColorBuffer());
		glColorPointer(3, GL_FLOAT, 0, 0);
		glEnableClientState(GL_COLOR_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, model.getMesh().getNormalBuffer());
		glNormalPointer(GL_FLOAT, 0, 0);
		glEnableClientState(GL_NORMAL_ARRAY);

		glDrawArrays(GL_TRIANGLES, 0, model.getMesh().getPosition().length / 3);

		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
	}

	/**
	 * Clears the color and depth buffer
	 * @param r Red clear color
	 * @param g Green clear color
	 * @param b Blue clear color
	 */
	public void clear(int r, int g, int b) {
		glClearColor((float)r/255.0f, (float)g/255.0f, (float)b/255.0f, 1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

}