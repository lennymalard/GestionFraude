package gestionnaire2fraudes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;

public class Systeme {
    private HashMap<Epreuve, Formulaire> formulaires;

    public Systeme(){
        this.formulaires = new HashMap<>();
    }

    public void addFormulaire(Epreuve epreuve, Formulaire formulaire){
        formulaires.put(epreuve, formulaire);
    }

    public void removeFormulaire(Epreuve epreuve){
        formulaires.remove(epreuve);
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
        ArrayList<Formulaire> formulairesConcernes = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> entry : this.formulaires.entrySet()) {
            if (entry.getKey().equals(epreuve)) {
                formulairesConcernes.add(entry.getValue());
            }
        }
        return formulairesConcernes;
    }


    public Etudiant findEtudiant(String key){
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()){
                switch (key){
                    case "nom":

                        break;
                    case "prenom":
                        break;
                    case "id":
                        break;
                    default:
                        break;
                }
            }

        }

    }

    /*
    public int calcNombreFormulaires(){

    }

    public int calcNombreEtudiants(){

    }

    public int calcNombreFraudes(){

    }

    public float calcMoyenneFraudesFormulaire(){

    }

    public float calcStdFraudesFormulaire(){

    }
    */


}