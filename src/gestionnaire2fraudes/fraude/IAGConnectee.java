package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public class IAGConnectee extends IAG{
    private String ipUtilisee;

    public IAGConnectee(LocalDateTime dateReleve, String contenu, String description, String nomService, String ipUtilisee) {
        super(dateReleve, contenu, description, nomService);
        this.ipUtilisee = ipUtilisee;
    }

    public String getIpUtilisee() {
        return ipUtilisee;
    }

    public void setIpUtilisee(String ipUtilisee) {
        this.ipUtilisee = ipUtilisee;
    }
}
