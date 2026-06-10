package gestionnaire2fraudes;

import gestionnaire2fraudes.Systeme;
import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.cursus.Modalite;
import gestionnaire2fraudes.fraude.FraudeCalculatrice;
import gestionnaire2fraudes.fraude.Fraude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class VerifySystem {


    public static void main(String[] args) {
        Systeme systeme = new Systeme();

        Epreuve epreuveTest = new Epreuve("MATH301", 2, 6, 2026, 8, 30, 90, Modalite.EXAMEN_ECRIT);

        Etudiant etudiantTest = new Etudiant("Malard", "Lenny", Cursus.E3e);
        FraudeCalculatrice fraudeTest = new FraudeCalculatrice(LocalDateTime.now(), "Formules", "Tricherie", "Casio", "Graph 35");

        Formulaire formulaireTest = new Formulaire();
        HashMap<Etudiant, ArrayList<Fraude>> fraudeursMap = new HashMap<>();
        ArrayList<Fraude> fraudesList = new ArrayList<>();
        fraudesList.add(fraudeTest);
        fraudeursMap.put(etudiantTest, fraudesList);
        formulaireTest.setFraudeurs(fraudeursMap);

        systeme.addFormulaire(epreuveTest, formulaireTest);

        Epreuve epreuveAlgorithmique = new Epreuve("ALGO302", 2, 6, 2026, 14, 0, 120, Modalite.ORDINATEUR);
        Epreuve epreuvePhysique = new Epreuve("PHYS401", 3, 6, 2026, 9, 0, 180, Modalite.EXAMEN_ECRIT);

        Etudiant etudiant2 = new Etudiant("Martin", "Alice", Cursus.E3a);
        Etudiant etudiant3 = new Etudiant("Dupont", "Lucas", Cursus.E4);
        Etudiant etudiant4 = new Etudiant("Leclerc", "Sarah", Cursus.E5);

        FraudeCalculatrice fraudeCalc1 = new FraudeCalculatrice(LocalDateTime.now(), "Aide-mémoire", "Formules de Taylor", "TI", "Nspire");
        FraudeCalculatrice fraudeCalc2 = new FraudeCalculatrice(LocalDateTime.now(), "Programmes", "Algorithme de tri stocké", "TI", "83 Premium");
        FraudeCalculatrice fraudeCalc3 = new FraudeCalculatrice(LocalDateTime.now(), "Texte", "Constantes physiques", "Casio", "Fx-92");

        ArrayList<Fraude> fraudesEtudiant2 = new ArrayList<>();
        fraudesEtudiant2.add(fraudeCalc1);
        fraudesEtudiant2.add(fraudeCalc2);
        fraudeursMap.put(etudiant2, fraudesEtudiant2);

        Formulaire formulaireAlgo = new Formulaire();
        HashMap<Etudiant, ArrayList<Fraude>> fraudeursAlgoMap = new HashMap<>();

        ArrayList<Fraude> fraudesEtudiant3 = new ArrayList<>();
        fraudesEtudiant3.add(fraudeCalc1);
        fraudesEtudiant3.add(fraudeCalc3);

        ArrayList<Fraude> fraudesEtudiant4 = new ArrayList<>();
        fraudesEtudiant4.add(fraudeCalc2);

        fraudeursAlgoMap.put(etudiant3, fraudesEtudiant3);
        fraudeursAlgoMap.put(etudiant4, fraudesEtudiant4);
        formulaireAlgo.setFraudeurs(fraudeursAlgoMap);

        Formulaire formulairePhysique = new Formulaire();
        HashMap<Etudiant, ArrayList<Fraude>> fraudeursPhysiqueMap = new HashMap<>();

        ArrayList<Fraude> fraudesMultiplesEtudiantTest = new ArrayList<>();
        fraudesMultiplesEtudiantTest.add(fraudeCalc2);
        fraudesMultiplesEtudiantTest.add(fraudeCalc3);

        fraudeursPhysiqueMap.put(etudiantTest, fraudesMultiplesEtudiantTest);
        fraudeursPhysiqueMap.put(etudiant3, fraudesEtudiant2);
        formulairePhysique.setFraudeurs(fraudeursPhysiqueMap);

        systeme.addFormulaire(epreuveAlgorithmique, formulaireAlgo);
        systeme.addFormulaire(epreuvePhysique, formulairePhysique);

        systeme.findFormulairesEpreuve(epreuveTest);
        systeme.findEtudiant("prenom", "malard");
        systeme.calcNombreEtudiants();
        systeme.calcNombreFraudes();

        systeme.addFraudeurGraphe(etudiantTest);
        systeme.addFraudeurGraphe(etudiant2);
        systeme.addFraudeurGraphe(etudiant3);
        systeme.addFraudeurGraphe(etudiant4);

        systeme.lierFraudeursEpreuve();
        systeme.afficherGraphe();
        systeme.afficherLiensEtudiants();
    }
}