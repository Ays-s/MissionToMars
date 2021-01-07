package spaceship;

import probability.LinearProbability;
import probability.Probability;

public class U2 extends U {
    private static final int initialCost = 120;
    public static final int initialNbrPeople = 4;
    public static final int initialWeight = 18;
    public static final int initialMaxWeight = 29;

    public U2() {
        // Constructeur de la classe
        super(initialWeight, initialWeight, initialMaxWeight, initialCost, initialNbrPeople, 0.08, 0.04, new LinearProbability());
    }
    public U2(Probability probability) {
        // Constructeur de la classe
        super(initialWeight, initialWeight, initialMaxWeight, initialCost, initialNbrPeople, 0.08, 0.04, probability);
    }
}
