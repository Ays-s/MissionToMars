package test;

import org.junit.jupiter.api.Test;
import spaceship.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProbabilityTest {

    @Test
    void exponentialProbability(){
        double lambda = 0.5;
        ExponentialProbability proba = new ExponentialProbability(lambda);
        boolean answer1 = (proba.probability(5,0,10) > 1);
        assertFalse(answer1);
        boolean answer2 = (proba.probability(5,0,10) < 0);
        assertFalse(answer2);
    }

    @Test
    void poissonProbability(){
        double lambda = 2;
        PoissonProbability proba = new PoissonProbability(lambda);
        boolean answer1 = (proba.probability(5,0,10) > 1);
        assertFalse(answer1);
        boolean answer2 = (proba.probability(5,0,10) < 0);
        assertFalse(answer2);
    }

    @Test
    void linearProbability(){
        LinearProbability proba = new LinearProbability();
        boolean answer1 = (proba.probability(5,0,10) > 1);
        assertFalse(answer1);
        boolean answer2 = (proba.probability(5,0,10) < 0);
        assertFalse(answer2);
    }

    @Test
    void getMeanReal() {
        LinearProbability proba = new LinearProbability();
        assertEquals(.5,proba.getMean());
    }

    @Test
    void getVarianceReal() {
        LinearProbability proba = new LinearProbability();
        assertEquals(0.08333333333333333,proba.getVariance());
    }

    @Test
    void getMeanInt() {
        double lambda = 1;
        PoissonProbability proba = new PoissonProbability(lambda);
        assertEquals(lambda, proba.getMean());
    }

    @Test
    void getVarianceInt() {
        double lambda = 1;
        PoissonProbability proba = new PoissonProbability(lambda);
        assertEquals(lambda, proba.getVariance());
    }
}
