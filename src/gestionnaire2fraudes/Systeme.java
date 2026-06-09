package gestionnaire2fraudes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;
import gestionnaire2fraudes.utils.Graphe;

public class Systeme {
    private HashMap<Epreuve, Formulaire> formulaires;
    private ArrayList<Epreuve> epreuves;
    private Graphe graphe;
    private HashMap<Etudiant, Integer> etudiantIndiceMap;

    public Systeme(){
        this.formulaires = new HashMap<>();
        this.epreuves = new ArrayList<>();
        this.graphe = new Graphe(0, false, new int[][]{});
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

    public void addFraudeurGraphe(Etudiant etudiant){
        if (!etudiantIndiceMap.containsKey(etudiant)){
            this.graphe.ajouterSommet();
            etudiantIndiceMap.put(etudiant, this.graphe.getNombreSommets()-1);
        }
    }

    public void creerLienFraudeurs(Etudiant etudiant1, Etudiant etudiant2){
        if (!etudiantIndiceMap.containsKey(etudiant1) || !etudiantIndiceMap.containsKey(etudiant2)){
            return;
        }
        int indice1 = etudiantIndiceMap.get(etudiant1);
        int indice2 = etudiantIndiceMap.get(etudiant2);
        this.graphe.ajouterArc(indice1, indice2);
    }

    public void lierFraudeursEpreuve(){
        for (Formulaire formulaire : this.formulaires.values()){
            for (Etudiant etudiant1 : formulaire.getFraudeurs().keySet()){
                for (Etudiant etudiant2 : formulaire.getFraudeurs().keySet()) {
                    if (etudiant1.getId() < etudiant2.getId()) {
                        this.creerLienFraudeurs(etudiant1, etudiant2);
                    }
                }
            }
        }
    }

}