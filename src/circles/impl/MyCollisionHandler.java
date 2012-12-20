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

import circles.api.Circle;
import circles.api.Collision;
import circles.api.CollisionHandler;
import java.util.Set;

/**
 *
 * @author Felix Wiemuth
 */
public class MyCollisionHandler implements CollisionHandler {

    @Override
    public void handleCollision(Collision c, Set<Circle> circles) {
        if (!c.c1().isActive() || !c.c2().isActive()) {
            return;
        }
        c.c1().setActive(false);
        c.c2().setActive(false);
        //TODO active / unactive control (set, unset, ...)
        //DEBUG
        System.out.println("Collision! - C1=" + c.c1().getGroupID() + " C2=" + c.c2().getGroupID());
        c.c1().setVx(
                (c.c1().getVx() * (c.c1().getMass() - c.c2().getMass())
                + 2 * c.c2().getMass() * c.c2().getVx())
                / (c.c1().getMass() + c.c2().getMass())
        );
        c.c1().setVy(
                (c.c1().getVy() * (c.c1().getMass() - c.c2().getMass())
                + 2 * c.c2().getMass() * c.c2().getVy())
                / (c.c1().getMass() + c.c2().getMass())
        );
        c.c2().setVx(
                (c.c2().getVx() * (c.c2().getMass() - c.c1().getMass())
                + 2 * c.c1().getMass() * c.c1().getVx())
                / (c.c2().getMass() + c.c1().getMass())
        );
        c.c2().setVy(
                (c.c2().getVy() * (c.c2().getMass() - c.c1().getMass())
                + 2 * c.c1().getMass() * c.c1().getVy())
                / (c.c2().getMass() + c.c1().getMass())
        );
    }
    
}
