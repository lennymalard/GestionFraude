package gestionnaire2fraudes.fraude;

import java.time.LocalDateTime;

/**
 * Classe abstraite modélisant une fraude.
 */
public abstract class Fraude {
    private LocalDateTime dateReleve;
    private String contenu;
    private String description;

    /**
     * Constructeur d'une fraude.
     *
     * @param dateReleve La date et l'heure du constat de la fraude
     * @param contenu Le contenu de la fraude
     * @param description Une description
     */
    public Fraude(LocalDateTime dateReleve, String contenu, String description) {
        this.dateReleve = dateReleve;
        this.contenu = contenu;
        this.description = description;
    }

    /**
     * Retourne la date de la fraude.
     *
     * @return La date.
     */
    public LocalDateTime getDateReleve() {
        return dateReleve;
    }

    /**
     * Modifie la date de la fraude.
     *
     * @param dateReleve La nouvelle date.
     */
    public void setDateReleve(LocalDateTime dateReleve) {
        this.dateReleve = dateReleve;
    }

    /**
     * Retourne le contenu de la fraude.
     *
     * @return Le contenu.
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Modifie le contenu de la fraude.
     *
     * @param contenu Le nouveau contenu.
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * Retourne la description de la fraude.
     *
     * @return La description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la fraude.
     *
     * @param description La nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la représentation sous forme de chaîne de caractères de la fraude.
     *
     * @return Les détails de base de la fraude formattés.
     */
    @Override
    public String toString() {
        return ", Date de relevé : " + dateReleve.getDayOfMonth() + "/" + dateReleve.getMonthValue() + "/" + dateReleve.getYear() +
                ", Contenu : " + contenu +
                ", Description : " + description;
    }
}