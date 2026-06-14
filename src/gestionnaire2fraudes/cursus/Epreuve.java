package gestionnaire2fraudes.cursus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Représente une épreuve évaluée.
 */
public class Epreuve {
    private String codeUcue;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Duration duree;
    private Modalite modalite;

    /**
     * Constructeur principal.
     *
     * @param codeUcue Le code de l'UCUE de l'épreuve.
     * @param datePassage La date de passage.
     * @param heurePassage L'heure de début de l'épreuve.
     * @param duree La durée de l'épreuve.
     * @param modalite La modalité de l'épreuve.
     */
    Epreuve(String codeUcue, LocalDate datePassage, LocalTime heurePassage, Duration duree, Modalite modalite){
        this.codeUcue = codeUcue;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.duree = duree;
        this.modalite = modalite;
    }

    /**
     * Constructeur permettant d'initialiser l'épreuve avec des entiers (pour la date, l'heure et la durée).
     *
     * @param codeUcue      Le code de l'UC/UE.
     * @param jour          Le jour du passage.
     * @param mois          Le mois du passage.
     * @param annee         L'année du passage.
     * @param heurePassage  L'heure du passage.
     * @param minutePassage La minute de début.
     * @param duree         La durée en minutes.
     * @param modalite      La modalité de l'épreuve.
     */
    public Epreuve(String codeUcue, int jour, int mois, int annee, int heurePassage, int minutePassage, int duree, Modalite modalite){
        this(codeUcue, LocalDate.of(annee, mois, jour), LocalTime.of(heurePassage, minutePassage), Duration.ofMinutes(duree), modalite);
    }

    /**
     * Retourne le code UCUE.
     *
     * @return Le code.
     */
    public String getCodeUcue() {
        return codeUcue;
    }

    /**
     * Modifie le code UCUE.
     *
     * @param codeUcue Le nouveau code.
     */
    public void setCodeUcue(String codeUcue) {
        this.codeUcue = codeUcue;
    }

    /**
     * Retourne la date de l'épreuve.
     *
     * @return La date de passage.
     */
    public LocalDate getDatePassage() {
        return datePassage;
    }

    /**
     * Modifie la date de l'épreuve.
     *
     * @param datePassage La nouvelle date.
     */
    public void setDatePassage(LocalDate datePassage) {
        this.datePassage = datePassage;
    }

    /**
     * Retourne l'heure de passage.
     *
     * @return L'heure.
     */
    public LocalTime getHeurePassage() {
        return heurePassage;
    }

    /**
     * Modifie l'heure de passage.
     *
     * @param heurePassage La nouvelle heure.
     */
    public void setHeurePassage(LocalTime heurePassage) {
        this.heurePassage = heurePassage;
    }

    /**
     * Retourne la durée de l'épreuve.
     *
     * @return La durée.
     */
    public Duration getDuree() {
        return duree;
    }

    /**
     * Modifie la durée de l'épreuve.
     *
     * @param duree La nouvelle durée.
     */
    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    /**
     * Retourne la modalité de l'épreuve.
     *
     * @return La modalité.
     */
    public Modalite getModalite() {
        return modalite;
    }

    /**
     * Modifie la modalité de l'épreuve.
     *
     * @param modalite La nouvelle modalité.
     */
    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

    /**
     * Retourne la représentation sous forme de chaîne de caractères de l'épreuve.
     *
     * @return Les détails de l'épreuve formattés.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Epreuve - ");

        str.append("Code UCUE : ");
        str.append(this.getCodeUcue());

        str.append(" Date passage : ");
        str.append(this.getDatePassage().getDayOfMonth());
        str.append("/");
        str.append(this.getDatePassage().getMonthValue());
        str.append("/");
        str.append(this.getDatePassage().getYear());

        str.append(" Heure passage : ");
        str.append(this.getHeurePassage().getHour());
        str.append(":");
        str.append(this.getHeurePassage().getMinute());

        str.append(" Durée épreuve : ");
        str.append(this.getDuree().toMinutes());
        str.append("min");

        str.append(" Modalité : ");
        str.append(this.getModalite().toString());

        return str.toString();
    }
}