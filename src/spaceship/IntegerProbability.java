package spaceship;

import org.apache.commons.math3.distribution.IntegerDistribution;

public abstract class IntegerProbability implements Probability{
    private final IntegerDistribution distribution;

    public IntegerProbability(IntegerDistribution distribution) {
        this.distribution = distribution;
    }

    public IntegerDistribution getDistribution() {
        return distribution;
    }

    @Override
    public double getMean() {
        return getDistribution().getNumericalMean();
    }

    @Override
    public double getVariance() {
        return getDistribution().getNumericalVariance();
    }

    public abstract double probability(int weight, int minWeight, int maxWeight);

}
