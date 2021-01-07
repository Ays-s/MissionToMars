package probability;

import org.apache.commons.math3.distribution.RealDistribution;

public abstract class RealProbability implements Probability{
    private final RealDistribution distribution;

    public RealProbability(RealDistribution distribution) {
        this.distribution = distribution;
    }

    public RealDistribution getDistribution() {
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

