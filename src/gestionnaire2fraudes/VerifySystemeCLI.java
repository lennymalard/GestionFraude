package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.cursus.Modalite;
import gestionnaire2fraudes.fraude.FraudeCalculatrice;
import gestionnaire2fraudes.fraude.FraudeIAG;
import gestionnaire2fraudes.fraude.FraudeIAGConnectee;
import gestionnaire2fraudes.fraude.FraudePapier;
import gestionnaire2fraudes.utils.Tuple;

import java.time.LocalDateTime;

public class VerifySystemeCLI {
    public static void main(String[] args){
        // 4 Etudiants de cursus différent
        Etudiant etudiant1 = new Etudiant("Malard", "Lenny", Cursus.E3e);
        Etudiant etudiant2 = new Etudiant("Martin", "Alice", Cursus.E3a);
        Etudiant etudiant3 = new Etudiant("Dupont", "Lucas", Cursus.E4);
        Etudiant etudiant4 = new Etudiant("Leclerc", "Sarah", Cursus.E5);

        // 2 Epreuves de modalité différentes
        Epreuve epreuveTest = new Epreuve("MATH301", 2, 6, 2026, 8, 30, 90, Modalite.EXAMEN_ECRIT);
        Epreuve epreuveAlgorithmique = new Epreuve("ALGO302", 2, 6, 2026, 14, 0, 120, Modalite.ORDINATEUR);
        Epreuve epreuvePhysique = new Epreuve("PHYS401", 3, 6, 2026, 9, 0, 180, Modalite.EXAMEN_ECRIT);
        Epreuve epreuveDeep = new Epreuve("DEEP", 9, 6, 2026, 8, 15, 240, Modalite.ORAL);

        // 4 Fraudes avec types différents
        FraudeCalculatrice fraudeCalc1 = new FraudeCalculatrice(LocalDateTime.now(), "Aide-mémoire", "Formules de Taylor", "TI", "Nspire");
        FraudePapier fraudePapier1 = new FraudePapier(LocalDateTime.now(), "2+2=4", "Aide de calcul", new Tuple(10, 15), true);
        FraudeIAG fraudeIAG1 = new FraudeIAG(LocalDateTime.now(), "Combien font 2+2?", "Aide de calcul", "ChatGPT");
        FraudeIAGConnectee fraudeIAGConnectee1 = new FraudeIAGConnectee(LocalDateTime.now(), "Comment s'appelle le 3e merveille du monde?", "Aide de culture générale", "Gemini", "102.152.162.154");

        //3 Formulaires
        Formulaire form1 = new Formulaire();
        form1.ajoutFraudeurs(etudiant1, fraudeIAG1);
        form1.ajoutFraudeurs(etudiant2, fraudePapier1);
        Formulaire form2 = new Formulaire();
        form2.ajoutFraudeurs(etudiant3, fraudeIAGConnectee1);
        Formulaire form3 = new Formulaire();
        form3.ajoutFraudeurs(etudiant4, fraudeCalc1);

        //Ajout des associations entre épreuve et formulaire dans le système
        SystemeCLI sys = new SystemeCLI();
        sys.addEpreuve(epreuveTest);
        sys.addEpreuve(epreuveAlgorithmique);
        sys.addEpreuve(epreuvePhysique);

        sys.addFormulaire(epreuveTest, form1);
        sys.addFormulaire(epreuveAlgorithmique, form2);
        sys.addFormulaire(epreuvePhysique, form3);

        sys.start();
    }
}
