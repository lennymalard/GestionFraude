package gestionnaire2fraudes.cursus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Epreuve {
    private String codeUcue;
    private LocalDate datePassage;
    private LocalTime heurePassage;
    private Duration duree;
    private Modalite modalite;

    Epreuve(String codeUcue, LocalDate datePassage, LocalTime heurePassage, Duration duree, Modalite modalite){
        this.codeUcue = codeUcue;
        this.datePassage = datePassage;
        this.heurePassage = heurePassage;
        this.duree = duree;
        this.modalite = modalite;
    }

    public Epreuve(String codeUcue, int jour, int mois, int annee, int heurePassage, int minutePassage, int duree, Modalite modalite){
        this(codeUcue, LocalDate.of(annee, mois, jour), LocalTime.of(heurePassage, minutePassage), Duration.ofMinutes(duree), modalite);
    }


    public String getCodeUcue() {
        return codeUcue;
    }

    public void setCodeUcue(String codeUcue) {
        this.codeUcue = codeUcue;
    }

    public LocalDate getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(LocalDate datePassage) {
        this.datePassage = datePassage;
    }

    public LocalTime getHeurePassage() {
        return heurePassage;
    }

    public void setHeurePassage(LocalTime heurePassage) {
        this.heurePassage = heurePassage;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    public Modalite getModalite() {
        return modalite;
    }

    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

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
