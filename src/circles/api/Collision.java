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

/**
 *
 * @author Felix Wiemuth
 */
public class Collision {

    private Circle c1;
    private Circle c2;

    public Collision(Circle c1, Circle c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Circle c1() {
        return c1;
    }

    public Circle c2() {
        return c2;
    }
}
