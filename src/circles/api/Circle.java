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
public class Circle implements CircleDrawable {

    private static int defaultGroupID = 0;
    private double posX;
    private double posY;
    private double vx = 0;
    private double vy = 0;
    private double radius;
    private int groupID;
    private boolean active;

    public Circle(double posX, double posY, double radius) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.groupID = defaultGroupID;
        active = false;
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void move(double factor) {
        posX += vx * factor;
        posY += vy * factor;
    }

    public void move(double dx, double dy) {
        posX += dx;
        posY += dy;
    }

    public void accelerate(double dvx, double dvy) {
        vx += dvx;
        vy += dvy;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void rezise(double factor) {
        radius *= factor;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean collides(Circle c) {
        return getRadius() + c.getRadius() > Math.sqrt(Math.pow(getPosX() - c.getPosX(), 2) + Math.pow(getPosY() - c.getPosY(), 2));
    }
}
