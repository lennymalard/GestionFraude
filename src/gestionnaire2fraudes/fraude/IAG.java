package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public class IAG extends Fraude {
    private String nomService;

    public IAG(LocalDateTime dateReleve, String contenu, String description, String nomService) {
        super(dateReleve, contenu, description);
        this.nomService = nomService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
}
