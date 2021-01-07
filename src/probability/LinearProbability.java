package probability;

import org.apache.commons.math3.distribution.UniformRealDistribution;

public class LinearProbability extends RealProbability {

    public LinearProbability() {
        super(new UniformRealDistribution());
    }

    public double probability(int weight, int minWeight, int maxWeight) {
        return (this.getDistribution().cumulativeProbability((float) weight / (float) maxWeight));
    }
}
