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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Felix Wiemuth
 */
public abstract class AbstractSimulator implements Simulation {

    protected Set<Circle> circles = new HashSet<Circle>();
    protected CollisionHandler collisionHandler;
    private List<Collision> collisions; //current collisions
    private List<Circle> notColliding; //current circles that are not part of a collision

    public AbstractSimulator(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }
    
    protected void doCollisionCalculations() {
        Circle[] c = (Circle[]) circles.toArray(new Circle[0]);
        collisions = new ArrayList<Collision>();
        notColliding = new ArrayList<Circle>();
        
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i+1; j < circles.size(); j++) {
                if (c[i].collides(c[j])) {
                    collisions.add(new Collision(c[i], c[j]));
                } else {
                    notColliding.add(c[i]);
                    notColliding.add(c[j]);
                }
            }
        }
    }
    
    protected List<Collision> getCollisions() {
        return collisions;
    }

    protected List<Circle> getNotColliding() {
        return notColliding;
    }
    
    
}
