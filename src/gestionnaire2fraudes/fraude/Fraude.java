package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

public abstract class Fraude {
    private LocalDateTime dateReleve;
    private String contenu;
    private String description;

    public Fraude(LocalDateTime dateReleve, String contenu, String description) {
        this.dateReleve = dateReleve;
        this.contenu = contenu;
        this.description = description;
    }

    public LocalDateTime getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(LocalDateTime dateReleve) {
        this.dateReleve = dateReleve;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
