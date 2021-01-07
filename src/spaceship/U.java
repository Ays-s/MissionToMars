package spaceship;


import probability.Probability;

public abstract class U extends Rocket {

    // On préfère faire des variables pour être plus explicite et les modifier plus facilement
    private final double landExploRate;
    private final double launchExploRate;
    private final Probability probabilityDistribution;

    public U(int weight, int minWeight, int maxWeight, int cost, int nbrPeople, double landExploRate, double launchExploRate, Probability distribution) {
        super(weight, minWeight, maxWeight, cost, nbrPeople);
        this.landExploRate = landExploRate;
        this.launchExploRate = launchExploRate;
        this.probabilityDistribution = distribution;
    }

    //public RealDistribution getDistribution() {
    //   return distribution;}


    public Probability getProbabilityDistribution() {
        return probabilityDistribution;
    }

    @Override
    public boolean launch() {
        // On calcule la probabilité d'explosion au décollage
//        super.launch(launchExploRate);
        double p;
//         launchExploRate*(this.getWeight()/this.getMaxWeight());
        p =launchExploRate*this.probabilityDistribution.probability(this.getWeight(), this.getMinWeight(), this.getMaxWeight());
        // On calcule un nombre aléatoire entre 0 et 1
        double nb;
        nb = Math.random();

        // Si le nb trouvé est inférieur à p la fusée explose
        return (nb > p);

    }

    @Override
    public boolean land() {
        // On calcule la probabilité d'explosion à l'atterrissage
        double p;
        p = landExploRate*this.probabilityDistribution.probability(this.getWeight(), this.getMinWeight(), this.getMaxWeight());
        // On calcule un nombre aléatoire entre 0 et 1
        double nb;
        nb = Math.random();

        // Si le nb trouvé est inférieur à p la fusée explose
        return (nb > p);
    }


//    private double probability(){
//        double p = 1;
//        if (this.distribution instanceof UniformRealDistribution){
//            p = this.distribution.cumulativeProbability(this.getWeight()/this.getMaxWeight());
//        }
//        if (this.distribution instanceof PoissonDistribution) {
//            float kf;
//            kf = this.getMaxWeight() * (this.getWeight() - this.getMinWeight()) / (this.getMaxWeight() - this.getWeight());
//            int k;
//            k = Float.floatToIntBits(kf);
//            // quel lambda choisir ?
//            // division par 0. -> INFINITY, floattointbits -> 0x7f800000
//            p = this.distribution.cumulativeProbability(k);
//        }
//        if (this.distribution instanceof ExponentialDistribution){
//            float x = (this.getWeight() - this.getMinWeight()) / (this.getMaxWeight() - this.getWeight());
//            p = this.distribution.cumulativeProbability(x);
//        }
//        return(p);
//    }
//    @Override
//    public String toString() {
//        return "U{" +
//                "weight=" + weight +
//                ", cost=" + cost +
//                ", nbrPeople=" + nbrPeople +
//                '}';
//    }
}
