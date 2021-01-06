package spaceship;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;


public interface Probability{
    public abstract double probability(int weight, int minWeight, int maxWeight);
    public double getMean();
    public double getVariance();
    }


