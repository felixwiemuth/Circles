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
package circles.api;

import circles.backend.AbstractRenderer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felix Wiemuth
 */
public class SimulationDirector {
    private Simulation simulation;
    private AbstractRenderer renderer;

    public SimulationDirector(Simulation simulation, AbstractRenderer renderer) {
        this.simulation = simulation;
        this.renderer = renderer;
        renderer.startUp();
        for (int i = 0; i < 10; i++) {
            renderer.play();
            try {
                Thread.sleep(1000);
                renderer.pause();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulationDirector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void simulate(double time) {
        simulation.simulate(time);
    }
    
}
