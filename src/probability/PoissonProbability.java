package probability;

import org.apache.commons.math3.distribution.PoissonDistribution;

public class PoissonProbability extends IntegerProbability{
    public PoissonProbability(double lambda){
        super(new PoissonDistribution(lambda));
    }

    @Override
    public double probability(int weight, int minWeight, int maxWeight) {
        float kf;
        kf = (float) weight * (weight - maxWeight) / (float) (maxWeight - weight); // division entière
        int k;
        k = Float.floatToIntBits(kf);
        // quel lambda choisir ?
        // division par 0. -> INFINITY, floattointbits -> 0x7f800000
        return(this.getDistribution().cumulativeProbability(k));
    }
}
