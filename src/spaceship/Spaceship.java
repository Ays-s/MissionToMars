package spaceship;

public interface Spaceship {
    // La méthode doit retourner true ou false suivant si le
    // décollage s'est bien passé ou non
    default boolean launch() {
        return false;
    }

    // La méthode doit retourner true ou false suivant si
    // l'aterrissage s'est bien passé ou non
    default boolean land() {
        return false;
    }

    // La méthode doit retourner true si la fusée peut porter
    // l'item en argument et false sinon
    default boolean canCarry(Item item) {
        return false;
    }

    // La méthode doit ajouter le poids de l'item passé en argument
    // au poids courant de la fusée
    default void carry(Item item) {

    }

}
