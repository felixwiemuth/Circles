/*
 * Copyright (C) 2012 Felix Wiemuth
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package circles.impl;

import circles.api.CircleDrawable;
import circles.api.Simulation;
import circles.backend.AbstractRenderer;
import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class CirclesRenderer extends AbstractRenderer implements GLEventListener {

    private Animator animator;

    public CirclesRenderer(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void startUp() {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        //canvas.addGLEventListener(new CirclesRenderer2());
        canvas.addGLEventListener(this);
        frame.add(canvas);
        frame.setSize(640, 480);
        frame.setResizable(false);
        animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        simulationUpdate();
        render(drawable);
    }

    private void render(GLAutoDrawable drawable) {
        //System.out.println("DISPLAY CALLED");
        GL gl = drawable.getGL();
        //Projection mode is for setting camera
        gl.glMatrixMode(GL.GL_PROJECTION);
        //This will set the camera for orthographic projection and allow 2D view
        //Our projection will be on 400 X 400 screen
        gl.glLoadIdentity();
        gl.glOrtho(0, 400, 400, 0, 0, 1);
        //Modelview is for drawing
        gl.glMatrixMode(GL.GL_MODELVIEW);
        //Depth is disabled because we are drawing in 2D
        gl.glDisable(GL.GL_DEPTH_TEST);
        //Setting the clear color (in this case black)
        //and clearing the buffer with this set clear color
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //This defines how to blend when a transparent graphics
        //is placed over another (here we have blended colors of
        //two consecutively overlapping graphic objects)
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_BLEND);
        //After this we start the drawing of object  

        //Draw circle
        //TODO: for each not working?
        for (CircleDrawable c : simulation.getCircles()) {
            drawCircle(gl, c);
        }
        //drawCircle(gl, new Circle(0, 0, 20));


        gl.glFlush();
    }

    private void drawCircle(GL gl, CircleDrawable c) {
        //Making circle in 50 small triangles	
        double increment = 2 * Math.PI / 50;
        //Set color
        switch (c.getGroupID()) {
            case 0:
                gl.glColor4f(1, 0, 0, 1);
                break;
            case 1:
                gl.glColor4f(0, 1, 0, 1);
                break;
            case 2:
                gl.glColor4f(1, 0, 1, 1);
                break;
            default:
                gl.glColor4f(1, 1, 0, 1);
                break;
        }
        //Starting loop for drawing triangles  
        for (double angle = 0; angle < 2 * Math.PI; angle += increment) {
            gl.glBegin(GL.GL_POLYGON);
            //One vertex of each triangle is at center of circle
            gl.glVertex2d(c.getPosX(), c.getPosY());
            //Other two vertices form the periphery of the circle		
            gl.glVertex2d(c.getPosX() + Math.cos(angle) * c.getRadius(), c.getPosY() + Math.sin(angle) * c.getRadius());
            gl.glVertex2d(c.getPosX() + Math.cos(angle + increment) * c.getRadius(), c.getPosY() + Math.sin(angle + increment) * c.getRadius());
            gl.glEnd();
        }
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void play() {
        animator.start();
    }

    @Override
    public void pause() {
        animator.stop();
    }
}
