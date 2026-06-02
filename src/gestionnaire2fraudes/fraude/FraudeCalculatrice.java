package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public class FraudeCalculatrice extends Fraude{
    private String marque;
    private String programme;

    public FraudeCalculatrice(LocalDateTime dateReleve, String contenu, String description, String marque, String programme) {
        super(dateReleve, contenu, description);
        this.marque = marque;
        this.programme = programme;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }
}
