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
package circles.backend;

import circles.api.Simulation;

/**
 * Base class for simulation renderers.
 * Note: Implementing renderer must use FPS from 'getFps()' and call 'step()'
 * before or after each rendered frame to ensure steady simulation speed.
 * @author Felix Wiemuth
 */
public abstract class AbstractRenderer {
    protected Simulation simulation;
    private double simulationSpeed = 1;
    //private int simulationPrecision = 1; //1,2,...
    private int fps = 0; //0: vsync, -1: maximum
    private double spf = 1; //simulation steps per frame
    //private double ssp = 0; //simulation steps pending
    private boolean fps_changed = true;
    
    public abstract void startUp();
    public abstract void play();
    public abstract void pause();

    public AbstractRenderer(Simulation simulation, int simulationSpeed) {
        this.simulation = simulation;
        this.simulationSpeed = simulationSpeed;
    }

    protected int getFps() {
        return fps;
    }
    
    protected void setFps(int fps) {
        this.fps = fps;
        fps_changed = true;
    }
    
    protected void step() {
        //recalculate spf
        if (fps_changed) {
            fps_changed = false;
            spf = simulationSpeed / fps;
        }
//        //add pending simulation steps
//        ssp += spf;
//        //check if simulator has to simulate
//        while (ssp >= 1) {
//            ssp--;
//        }
        simulation.simulate(spf);
    }
    
    
    
    
}
