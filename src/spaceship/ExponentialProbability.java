package spaceship;

import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExponentialProbability extends RealProbability{

    public ExponentialProbability(double lambda){
        super(new ExponentialDistribution(lambda));
    }

    @Override
    public double probability(int weight, int minWeight, int maxWeight){
        return(this.getDistribution().cumulativeProbability((float) weight/ (float) maxWeight));
    }
}