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
 * Base class for simulation renderers. Note: Implementing renderer should call
 * 'simulationUpdate()' during each rendering loop.
 *
 * @author Felix Wiemuth
 */
public abstract class AbstractRenderer {

    protected Simulation simulation;

    public abstract void startUp();

    public abstract void play();

    public abstract void pause();

    public AbstractRenderer(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Simulate if necessary.
     */
    protected void simulationUpdate() {
        simulation.simulate();
    }
}
