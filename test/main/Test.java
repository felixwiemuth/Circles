package main;

import circles.api.Circle;
import circles.backend.Simulator;
import circles.impl.CirclesRenderer;
import circles.impl.MyCollisionHandler;

/**
 *
 * @author Felix Wiemuth
 */
public class Test {    
    public static void main(String[] args) {
        Simulator sim = new Simulator(new MyCollisionHandler());
        sim.addCirlce(new Circle(50, 60, 25));
        Circle c = new Circle(100, 120, 15);
        c.setGroupID(1);
        c.accelerate(-0.01, -0.02);
        sim.addCirlce(c);
        CirclesRenderer renderer = new CirclesRenderer(sim);
        sim.setPrecision(5);
        sim.setSpeed(10);
        renderer.startUp();
        renderer.play();
        sim.play();
    }
}
