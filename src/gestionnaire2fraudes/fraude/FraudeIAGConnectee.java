package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public class FraudeIAGConnectee extends FraudeIAG {
    private String ipUtilisee;

    public FraudeIAGConnectee(LocalDateTime dateReleve, String contenu, String description, String nomService, String ipUtilisee) {
        super(dateReleve, contenu, description, nomService);
        this.ipUtilisee = ipUtilisee;
    }

    public String getIpUtilisee() {
        return ipUtilisee;
    }

    public void setIpUtilisee(String ipUtilisee) {
        this.ipUtilisee = ipUtilisee;
    }

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
