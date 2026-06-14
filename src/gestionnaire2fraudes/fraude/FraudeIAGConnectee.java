package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

/**
 * Représente une fraude utilisant une IAG connecté au réseau.
 */
public class FraudeIAGConnectee extends FraudeIAG {
    private String ipUtilisee;

    /**
     * Constructeur d'une fraude IAG connectée.
     *
     * @param dateReleve Date et heure.
     * @param contenu Le contenu généré.
     * @param description La description.
     * @param nomService  Le nom du service d'IA.
     * @param ipUtilisee L'adresse IP utilisé.
     */
    public FraudeIAGConnectee(LocalDateTime dateReleve, String contenu, String description, String nomService, String ipUtilisee) {
        super(dateReleve, contenu, description, nomService);
        this.ipUtilisee = ipUtilisee;
    }

    /**
     * Retourne l'adresse IP.
     *
     * @return L'IP utilisée.
     */
    public String getIpUtilisee() {
        return ipUtilisee;
    }

    /**
     * Modifie l'adresse IP.
     *
     * @param ipUtilisee La nouvelle adresse IP.
     */
    public void setIpUtilisee(String ipUtilisee) {
        this.ipUtilisee = ipUtilisee;
    }

    /**
     * Retourne la représentation textuelle de cette fraude spécifique.
     *
     * @return Les détails de la fraude connectée.
     */
    @Override
    public String toString() {
        return "Fraude IAG connectee - " +
                ", Date de relevé : " + this.getDateReleve() +
                ", Contenu : " + this.getContenu() +
                ", Description : " + this.getDescription() +
                ", Nom du service : " + this.getNomService() +
                ", IP Utilisee : " + ipUtilisee;
    }
}