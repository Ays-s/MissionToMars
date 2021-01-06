package spaceship;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

public abstract class RealProbability {
    private final RealDistribution distribution;

    public RealProbability(RealDistribution distribution) {
        this.distribution = distribution;
    }

    public RealDistribution getDistribution() {
        return distribution;
    }

    public abstract double probability(int weight, int minWeight, int maxWeight);

}

