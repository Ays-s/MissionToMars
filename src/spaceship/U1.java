package spaceship;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

public class U1 extends U {
    private static final int initialCost = 100;
    public static final int initialNbrPeople = 3;
    public static final int initialWeight = 10;
    public static final int initialMaxWeight = 18;

    public U1() {
        // Constructeur de la classe par défaut, avec distribution uniforme
        super(initialWeight, initialWeight,  initialMaxWeight, initialCost, initialNbrPeople, 0.01, 0.05, new LinearProbability());
    }
    public U1(Probability probability) {
        // Constructeur de la classe par défaut, avec distribution uniforme
        super(initialWeight, initialWeight,  initialMaxWeight, initialCost, initialNbrPeople, 0.01, 0.05, probability);
    }
}

//#--module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls.javafx.fxml