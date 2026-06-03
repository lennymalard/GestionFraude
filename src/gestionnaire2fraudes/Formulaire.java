package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Formulaire {
    static int numId = 0;

    private int id;
    private LocalDate dateCreation;
    private LocalDate dateModif;
    private HashMap<Etudiant, ArrayList<Fraude>> fraudeurs = new HashMap<>();

    public Formulaire(int id, LocalDate dateCreation, LocalDate dateModif){
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateModif = dateModif;
    }

    public Formulaire(LocalDate dateCreation, LocalDate dateModif) {
        this(++numId, dateCreation, dateModif);
    }

    public Formulaire() {
        this(++numId, LocalDate.now(), LocalDate.now());
    }

    public static int getNumId() {
        return numId;
    }

    public static void setNumId(int numId) {
        Formulaire.numId = numId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModif() {
        return dateModif;
    }

    public void setDateModif(LocalDate dateModif) {
        this.dateModif = dateModif;
    }

    public HashMap<Etudiant, ArrayList<Fraude>> getFraudeurs() {
        return fraudeurs;
    }

    public void setFraudeurs(HashMap<Etudiant, ArrayList<Fraude>> fraudeurs) {
        this.fraudeurs = fraudeurs;
    }

    public void ajoutFraudeurs(Etudiant etudiant, Fraude fraude){
        if (fraudeurs.containsKey(etudiant)){
            fraudeurs.get(etudiant).add(fraude);
        }else{
            ArrayList<Fraude> listeFraudes = new ArrayList<>();
            listeFraudes.add(fraude);
            fraudeurs.put(etudiant, listeFraudes);
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Formulaire - ");

        str.append("Id : ");
        str.append(this.getId());

        str.append(" Date création : ");
        str.append(this.getDateCreation().getDayOfMonth());
        str.append("/");
        str.append(this.getDateCreation().getMonthValue());
        str.append("/");
        str.append(this.getDateCreation().getYear());

        str.append(" Date modification : ");
        str.append(this.getDateModif().getDayOfMonth());
        str.append("/");
        str.append(this.getDateModif().getMonthValue());
        str.append("/");
        str.append(this.getDateModif().getYear());

        return str.toString();
    }
}
