package spaceship;


import org.apache.commons.math3.distribution.*;

public abstract class U extends Rocket {

    // On préfère faire des variables pour être plus explicite et les modifier plus facilement
    private final double landExploRate;
    private final double launchExploRate;
    private final RealDistribution distribution;

    public U(int weight, int maxWeight, int cost, int nbrPeople, double landExploRate, double launchExploRate, RealDistribution distribution) {
        super(weight, maxWeight, cost, nbrPeople);
        this.landExploRate = landExploRate;
        this.launchExploRate = launchExploRate;
        this.distribution = distribution;
    }

    @Override
    public boolean launch() {
        // On calcule la probabilité d'explosion au décollage
//        super.launch(launchExploRate);
        double p;
//         launchExploRate*(this.getWeight()/this.getMaxWeight());
        p =launchExploRate*this.distribution.cumulativeProbability(this.getWeight()/this.getMaxWeight());
        // On calcule un nombre aléatoire entre 0 et 1
        double nb;
        nb = Math.random();

        // Si le nb trouvé est inférieur à p la fusée explose
        return (nb > p);

    }

    @Override
    public boolean land() {
        // On calcule la probabilité d'explosion à l'aterrissage
        double p;
        p = landExploRate*this.distribution.cumulativeProbability(this.getWeight()/this.getMaxWeight());

        // On calcule un nombre aléatoire entre 0 et 1
        double nb;
        nb = Math.random();

        // Si le nb trouvé est inférieur à p la fusée explose
        return (nb > p);
    }

//    @Override
//    public String toString() {
//        return "U{" +
//                "weight=" + weight +
//                ", cost=" + cost +
//                ", nbrPeople=" + nbrPeople +
//                '}';
//    }
}
