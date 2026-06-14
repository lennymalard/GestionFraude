package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

/**
 * Représente une fraude commise en faisant appel à un service d'Intelligence Artificielle Générative.
 */
public class FraudeIAG extends Fraude {
    private String nomService;

    /**
     * Constructeur d'une fraude par IAG.
     *
     * @param dateReleve Date et heure.
     * @param contenu Le contenu généré.
     * @param description La description.
     * @param nomService Le nom du service d'IA.
     */
    public FraudeIAG(LocalDateTime dateReleve, String contenu, String description, String nomService) {
        super(dateReleve, contenu, description);
        this.nomService = nomService;
    }

    /**
     * Retourne le nom du service IA utilisé.
     *
     * @return Le nom du service.
     */
    public String getNomService() {
        return nomService;
    }

    /**
     * Modifie le nom du service IA.
     *
     * @param nomService Le nouveau nom du service.
     */
    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    /**
     * Retourne la représentation textuelle de cette fraude spécifique.
     *
     * @return Les détails de la fraude à l'IAG.
     */
    @Override
    public String toString() {
        return "Fraude IAG - " + super.toString() +
                ", Nom du service : " + nomService;
    }
}