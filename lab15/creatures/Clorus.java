package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        double babyEnergy = energy * 0.5;
        energy = energy * 0.5;
        return new Clorus(babyEnergy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        java.util.List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else {
            List<Direction> plips = getNeighborsOfType(neighbors, "plip");
            if (!plips.isEmpty()) {
                Direction attackDir = HugLifeUtils.randomEntry(plips);
                return new Action(Action.ActionType.ATTACK, attackDir);
            }
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            if (energy >= 1.0) {
                return new Action(Action.ActionType.REPLICATE, moveDir);
            }
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
