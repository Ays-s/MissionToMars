package spaceship;

import org.apache.commons.math3.distribution.UniformRealDistribution;

public class U2 extends U {
    private static final int initialCost = 120;
    public static final int initialNbrPeople = 4;
    public static final int initialWeight = 18;
    public static final int initialMaxWeight = 29;

    public U2() {
        // Constructeur de la classe
        super(initialWeight, initialMaxWeight, initialCost, initialNbrPeople, 0.08, 0.04, new UniformRealDistribution());
    }
}
