package gestionnaire2fraudes.fraude;

import gestionnaire2fraudes.utils.Tuple;

import java.time.LocalDateTime;

/**
 * Représente une fraude réalisée avec du papier.
 */
public class FraudePapier extends Fraude{
    private Tuple dimensions;
    private boolean plie;

    /**
     * Constructeur d'une fraude papier.
     *
     * @param dateReleve Date et heure.
     * @param contenu Le contenu.
     * @param description La description.
     * @param dimensions Les dimensions du papier.
     * @param plie Booléen indiquant si le papier était plié ou non.
     */
    public FraudePapier(LocalDateTime dateReleve, String contenu, String description, Tuple dimensions, boolean plie) {
        super(dateReleve, contenu, description);
        this.dimensions = dimensions;
        this.plie = plie;
    }

    /**
     * Retourne les dimensions du papier.
     *
     * @return Le Tuple contenant les dimensions.
     */
    public Tuple getDimensions() {
        return dimensions;
    }

    /**
     * Modifie les dimensions du papier.
     *
     * @param dimensions Le nouveau Tuple de dimensions.
     */
    public void setDimensions(Tuple dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Indique si le papier était plié.
     *
     * @return true si le papier était plié, false sinon.
     */
    public boolean isPlie() {
        return plie;
    }

    /**
     * Modifie l'état de pliage du papier.
     *
     * @param plie Le nouvel état.
     */
    public void setPlie(boolean plie) {
        this.plie = plie;
    }

    /**
     * Retourne la représentation textuelle de cette fraude spécifique.
     *
     * @return Les détails de la fraude papier.
     */
    @Override
    public String toString() {
        return "Fraude papier - " + super.toString() +
                ", Dimensions : " + dimensions.getElement1() + "*" + dimensions.getElement2() +
                ", Plie : " + plie;
    }
}