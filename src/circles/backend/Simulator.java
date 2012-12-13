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

import circles.api.AbstractSimulator;
import circles.api.Circle;
import circles.api.Collision;
import circles.api.CollisionHandler;

/**
 *
 * @author Felix Wiemuth
 */
public class Simulator extends AbstractSimulator {
    private int precision = 1; //number of calculations to perform per step (call of 'simulate()'

    public Simulator(CollisionHandler collisionHandler) {
        super(collisionHandler);
    }
    
    @Override
    public void simulate(double time) {
        double factor = time / precision;
        for (int i = 0; i < precision; i++) {
            for (Circle c : circles) {
                c.move(factor);
            }
            for (Collision c : getCollisions()) {
                collisionHandler.handleCollision(c, circles);
            }
        }
    }

    @Override
    public Iterable<Circle> getCircles() {
        return circles;
    }

    @Override
    public void addCirlce(Circle circle) {
        circles.add(circle);
    }
}
