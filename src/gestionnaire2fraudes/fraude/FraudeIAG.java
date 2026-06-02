package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public class FraudeIAG extends Fraude {
    private String nomService;

    public FraudeIAG(LocalDateTime dateReleve, String contenu, String description, String nomService) {
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
