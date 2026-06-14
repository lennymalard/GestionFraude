package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

/**
 * Représente une fraude commise à l'aide d'une calculatrice.
 */
public class FraudeCalculatrice extends Fraude{
    private String marque;
    private String programme;

    /**
     * Constructeur d'une fraude par calculatrice.
     *
     * @param dateReleve Date et heure du constat.
     * @param contenu Le contenu.
     * @param description La description.
     * @param marque La marque de la calculatrice.
     * @param programme Le programme stocké.
     */
    public FraudeCalculatrice(LocalDateTime dateReleve, String contenu, String description, String marque, String programme) {
        super(dateReleve, contenu, description);
        this.marque = marque;
        this.programme = programme;
    }

    /**
     * Retourne la marque de la calculatrice.
     *
     * @return La marque.
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Modifie la marque de la calculatrice.
     *
     * @param marque La nouvelle marque.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Retourne le programme identifié.
     *
     * @return Le programme.
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * Modifie le programme de la calculatrice.
     *
     * @param programme Le nouveau programme.
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * Retourne la représentation textuelle de cette fraude spécifique.
     *
     * @return Les détails de la fraude à la calculatrice.
     */
    @Override
    public String toString() {
        return "Fraude calculatrice - " + super.toString() +
                ", Marque : " + marque +
                ", Programme : " + programme;
    }
}