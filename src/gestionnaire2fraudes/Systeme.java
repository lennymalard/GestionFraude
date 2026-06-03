package gestionnaire2fraudes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;

public class Systeme {
    private HashMap<Epreuve, Formulaire> formulaires;
    private ArrayList<Epreuve> epreuves;

    public Systeme(){
        this.formulaires = new HashMap<>();
        this.epreuves = new ArrayList<>();
    }

    public HashMap<Epreuve, Formulaire> getFormulaires() {
        return formulaires;
    }

    public void setFormulaires(HashMap<Epreuve, Formulaire> formulaires) {
        this.formulaires = formulaires;
    }

    public ArrayList<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(ArrayList<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    public void addFormulaire(Epreuve epreuve, Formulaire formulaire){
        formulaires.put(epreuve, formulaire);
    }

    public void removeFormulaire(Epreuve epreuve){
        formulaires.remove(epreuve);
    }

    public void addEpreuve(Epreuve epreuve){
        epreuves.add(epreuve);
    }

    public void removeEpreuve(Epreuve epreuve){
        epreuves.remove(epreuve);
    }

    public ArrayList<Formulaire> findFormulairesEtudiant(Etudiant etudiant) {
        ArrayList<Formulaire> formulairesConcernes = new ArrayList<>();
        for (Formulaire formulaire : this.formulaires.values()) {
            if (formulaire.getFraudeurs().containsKey(etudiant)) {
                formulairesConcernes.add(formulaire);
            }
        }
        return formulairesConcernes;
    }

    public ArrayList<Formulaire> findFormulairesEpreuve(Epreuve epreuve) {
        /*Fonctionnalité temporaire*/
        ArrayList<Formulaire> formulairesConcernes = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> entry : this.formulaires.entrySet()) {
            if (entry.getKey().equals(epreuve)) {
                formulairesConcernes.add(entry.getValue());
            }
        }
        return formulairesConcernes;
    }

    public ArrayList<Etudiant> findEtudiant(String key, String value) {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                switch (key) {
                    case "nom":
                        if (etudiant.getNom().equals(value) && !etudiants.contains(etudiant)) {
                            etudiants.add(etudiant);
                        }
                        break;
                    case "prenom":
                        if (etudiant.getPrenom().equals(value) && !etudiants.contains(etudiant)) {
                            etudiants.add(etudiant);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return etudiants;
    }

    public Etudiant findEtudiant(int id){
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if (etudiant.getId() == id) {
                    return etudiant;
                }
            }
        }
        return null;
    }

    public Etudiant findEtudiant(String nom, String prenom, Cursus cursus){
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if (etudiant.getNom().equalsIgnoreCase(nom) &&
                        etudiant.getPrenom().equalsIgnoreCase(prenom) &&
                        etudiant.getCursus() == cursus) {
                    return etudiant;
                }
            }

        }
        return null;
    }

    public int calcNombreFormulaires(){
        return formulaires.size();
    }

    public int calcNombreEtudiants(){
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if  (!etudiants.contains(etudiant)){
                    etudiants.add(etudiant);
                }
            }
        }
        return  etudiants.size();
    }

    public int calcNombreFraudes(){
        ArrayList<Fraude> fraudes = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
               fraudes.addAll(fraudesEtudiant);
            }
        }
        return  fraudes.size();
    }

    public double calcMoyenneFraudesFormulaire() {
        int numFraudes = 0;
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            ArrayList<Fraude> fraudesFormulaire = new ArrayList<>();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
                fraudesFormulaire.addAll(fraudesEtudiant);
            }
            numFraudes += fraudesFormulaire.size();
        }
        return (double) numFraudes / formulaires.size();
    }


    public double calcStdFraudesFormulaire(){
        double moyenne = this.calcMoyenneFraudesFormulaire();
        double sommeEcarts = 0;
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            ArrayList<Fraude> fraudesFormulaire = new ArrayList<>();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
                fraudesFormulaire.addAll(fraudesEtudiant);
            }
            sommeEcarts += Math.pow(fraudesFormulaire.size() - moyenne, 2);
        }
        return Math.sqrt( sommeEcarts / formulaires.size());

    }

}