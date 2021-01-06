package spaceship;

import org.apache.commons.math3.distribution.IntegerDistribution;

public abstract class IntegerProbability {
    private final IntegerDistribution distribution;

    public IntegerProbability(IntegerDistribution distribution) {
        this.distribution = distribution;
    }

    public IntegerDistribution getDistribution() {
        return distribution;
    }

    public abstract double probability(int weight, int minWeight, int maxWeight);

}
