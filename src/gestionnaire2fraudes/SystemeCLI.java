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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class SystemeCLI implements CLI{
    //Intervalle de bonne valeur
    static String[] INTERVALCURSUS = {"E1", "E2", "E3e", "E3a", "E4", "E5"};
    static String[] INTERVALOUINON = {"o","n"};
    static Scanner scanner = new Scanner(System.in);
    String input;
    Systeme sys = new Systeme();
    HashMap<Epreuve, Formulaire> epreuveForm = sys.getFormulaires();


    @Override
    public void start(){
        display("Démarrage du système. Veuillez patienter.");
        isRunning();
    }

    @Override
    public void isRunning(){
        display("Bienvenue dans le menu principal");
        display("1 - Création d'un formulaire");
        display("2 - Modification d'un formulaire");
        display("3 - Recherche et analyse");
        display("4 - Suppression d'un formulaire");
        display("5 - Création d'une épreuve");
        display("6 - Quitter le programme");
        afficheMenuPrincipal();
        stop();
    }

    @Override
    public void stop(){
        display("Au revoir et à bientôt");
    }

    @Override
    public void display(String message){
        System.out.println(message);
    }

    private boolean rightInput(String input, String[] bonInterval){
        for (String s : bonInterval) {
            if (Objects.equals(input, s)) {
                return true;
            }
        }
        return false;
    }

    private int rightInput(String input){
        int i = Integer.parseInt(input);
        return i;
    }

    private void afficheMenuPrincipal(){
        boolean quitter = false;
        while(!quitter){
            int choix = 0;
            boolean choixMenuPrincipal = false;

            while(!choixMenuPrincipal){
                display("Entrez votre choix : ");
                input = scanner.nextLine().trim();
                try{
                    choix = rightInput(input);
                    if (choix >= 1 && choix <= 6) {
                        choixMenuPrincipal = true;
                    } else {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 6.");
                    }

                } catch (NumberFormatException e) {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                }
            }
            switch(choix){
                case 1:
                    display("Vous allez créer un formulaire");
                    afficherCreationFormulaire();
                    break;
                case 2:
                    display("Vous allez modifier un formulaire");
                    afficherModifierFormulaire();
                    break;
                case 3:
                    display("Vous allez rechercher ou analyser");
                    afficheMenuRecherche();
                    break;
                case 4:
                    display("Vous allez supprimer un formulaire");
                    afficherSupprimerFormulaire();
                    break;
                case 5:
                    display("Vous allez créer une épreuve");
                    afficherCreationEpreuve();
                    break;
                case 6:
                    display("Vous quittez le programme");
                    quitter = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void afficheMenuRecherche(){
        boolean quitterRecherche = false;
        while(!quitterRecherche){
            display("Bienvenue dans le menu rechercher et analyser");
            display("1 - Rechercher tous les formulaires associés à un étudiant");
            display("2 - Rechercher tous les formulaires associés à une épreuve");
            display("3 - Rechercher un étudiant");
            display("4 - Nombre total de formulaire enregistré dans le système");
            display("5 - Nombre total d'étudiant enregistré dans le système");
            display("6 - Nombre total de fraudes enregistré dans le système");
            display("7 - Moyenne de fraude par formulaire");
            display("8 - Écart type de fraude par formulaire");

            int choix = 0;
            boolean choixMenuRecherche = false;

            while(!choixMenuRecherche){
                display("Entrez votre choix : ");
                input = scanner.nextLine().trim();
                try{
                    choix = rightInput(input);
                    if (choix >= 1 && choix <= 8) {
                        choixMenuRecherche = true;
                    } else {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 8.");
                    }

                } catch (NumberFormatException e) {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                }
            }
            switch(choix){
                case 1:
                    //sys.findFormulairesEtudiant()
                    quitterRecherche = true;
                    break;
                case 2:
                    display("Vous allez modifier un formulaire");
                    quitterRecherche = true;
                    break;
                case 3:
                    break;
                case 4:
                    display("Vous allez supprimer un formulaire");
                    quitterRecherche = true;
                    break;
                case 5:
                    display("Vous allez créer une épreuve");
                    quitterRecherche = true;
                    break;
                case 6:
                    display("Vous quittez le programme");
                    quitterRecherche = true;
                    break;
                case 7:
                    display("Vous allez créer une épreuve");
                    quitterRecherche = true;
                    break;
                case 8:
                    display("Vous allez créer une épreuve");
                    quitterRecherche = true;
                    break;
                default:
                    break;
            }
        }
    }



    private boolean containsNumber(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean onlyNumber(String str){
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void afficherCreationFormulaire(){
        Formulaire form = null;
        ArrayList<Epreuve> epreuves = sys.getEpreuves();
        for(int i = 0; i < epreuves.size(); i++){
            StringBuilder strEpreuve = new StringBuilder(i);
            strEpreuve.append(" - ");
            strEpreuve.append(epreuves.get(i).toString()); //faire la fonction toString dans Epreuve (ne pas utiliser de saut de ligne)
            display(strEpreuve.toString());
        }
        boolean choixEpreuve = false;
        while(!choixEpreuve){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix <= epreuves.size()) {
                    choixEpreuve = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(epreuves.size()) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
            if(epreuveForm.containsKey(epreuves.get(choix))){
                display("Cette épreuve à déjà un formulaire, veuillez réessayer.");
                choixEpreuve = false;
            }
        }
        form = new Formulaire(LocalDate.now(), LocalDate.now());
        sys.addFormulaire(epreuves.get(Integer.parseInt(input)), form);
        afficherAjouterFraudeur(form);
        display("Formulaire créer");
    }

    private void afficherModifierFormulaire(){

        Formulaire formAModif = null;

        display("Voici la liste des formulaires que vous pouvez modifier.");
        int count = 0;
        for(Epreuve epreuve : epreuveForm.keySet()){
            display(String.valueOf(count) + " : ");
            display(epreuve.toString());
            display(epreuveForm.get(epreuve).toString());
            count++;
        }
        boolean choixFormulaire = false;
        while(!choixFormulaire){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix < count) {
                    choixFormulaire = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(count-1) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        int index = 0;
        for (Epreuve epreuve : epreuveForm.keySet()) {
            if (index == count-1) {
                formAModif = epreuveForm.get(epreuve);
                break;
            }
            index++;
        }
        afficherAjouterFraudeur(formAModif);
        formAModif.setDateModif(LocalDate.now());
        display("Formulaire modifier");
    }

    private void afficherSupprimerFormulaire(){
        display("Voici la liste des formulaires que vous pouvez supprimer.");
        int count = 0;
        for(Epreuve epreuve : epreuveForm.keySet()){
            display(String.valueOf(count) + " : ");
            display(epreuve.toString());
            display(epreuveForm.get(epreuve).toString());
            count++;
        }
        boolean choixFormulaireSupp = false;
        while(!choixFormulaireSupp){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix < count) {
                    choixFormulaireSupp = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(count-1) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        int index = 0;
        for (Epreuve epreuve : epreuveForm.keySet()) {
            if (index == count-1) {
                sys.removeFormulaire(epreuve);
                break;
            }
            index++;
        }
        display("Formulaire supprimer");
    }

    public void afficherAjouterFraudeur(Formulaire form){
        String nomEtu = null;
        String prenomEtu = null;
        Cursus cursusEtu = null;
        boolean renseignerFraudeEtu = true;
        while(renseignerFraudeEtu){
            display("Vous allez renseigner une fraude");
            boolean choixNomEtu = false;
            while(!choixNomEtu){
                display("Quel est le nom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffre.");
                }else{
                    nomEtu = input;
                    choixNomEtu = true;
                }
            }
            boolean choixPrenomEtu = false;
            while(!choixPrenomEtu){
                display("Quel est le prenom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffres");
                }else{
                    prenomEtu = input;
                    choixPrenomEtu = true;
                }
            }
            boolean choixCursusEtu = false;
            while(!choixCursusEtu){
                display("Quel est le cursus de l'étudiant (E1, E2, E3e, E3a, E4, E5) : ");
                input = scanner.nextLine().trim();
                if(rightInput(input, INTERVALCURSUS)){
                    cursusEtu = Cursus.valueOf(input);
                    choixCursusEtu = true;
                }else{
                    display("Entrée non accepté, veuillez réessayer");
                }
            }
            Etudiant etu = sys.findEtudiant(nomEtu, prenomEtu, cursusEtu);
            if(etu == null){
                etu = new Etudiant(nomEtu, prenomEtu, cursusEtu);
            }
            display("Quel type de fraude a-t-il commis ?");
            display("1 - Fraude avec calculatrice.");
            display("2 - Fraude avec IAG.");
            display("3 - Fraude avec IAG connecté.");
            display("4 - Fraude avec papier.");
            boolean quitterSelFraude = false;
            while(!quitterSelFraude){

                int choix = 0;
                boolean choixSelFraude = false;
                while(!choixSelFraude){
                    display("Entrez votre choix : ");
                    input = scanner.nextLine().trim();
                    try{
                        choix = rightInput(input);
                        if (choix >= 1 && choix <= 4) {
                            choixSelFraude = true;
                        } else {
                            display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 4.");
                        }

                    } catch (NumberFormatException e) {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                    }
                }
                String contenu;
                String description;
                switch (input){
                    case "1":
                        display("Vous avez choisi fraude avec calculatrice");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir la marque de la calculatrice : ");
                        String marque = scanner.nextLine();
                        display("Veuillez saisir le programme de triche utilisé : ");
                        String programme = scanner.nextLine();
                        FraudeCalculatrice fraudeCalc = new FraudeCalculatrice(LocalDateTime.now(), contenu, description, marque, programme);
                        form.ajoutFraudeurs(etu, fraudeCalc);
                        quitterSelFraude = true;
                        break;
                    case "2":
                        display("Vous avez choisi fraude avec IAG");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir le nom du service utilisé : ");
                        String nomService = scanner.nextLine();
                        FraudeIAG fraudeIag = new FraudeIAG(LocalDateTime.now(), contenu, description, nomService);
                        form.ajoutFraudeurs(etu, fraudeIag);
                        quitterSelFraude = true;
                        break;
                    case "3":
                        display("Vous avez choisi fraude avec IAG connecté");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir le nom du service utilisé : ");
                        String nomServiceConnecte = scanner.nextLine();
                        display("Veuillez saisir l'ip utilisé : ");
                        String ip = scanner.nextLine();
                        FraudeIAGConnectee fraudeIagConn = new FraudeIAGConnectee(LocalDateTime.now(), contenu, description, nomServiceConnecte, ip);
                        form.ajoutFraudeurs(etu, fraudeIagConn);
                        quitterSelFraude = true;
                        break;
                    case "4":
                        display("Vous avez choisi fraude avec papier");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir la longueur du papier : ");
                        String longueur = null;
                        boolean choixLongueurPapier = false;
                        while(!choixLongueurPapier){
                            longueur = scanner.nextLine().trim();
                            if(!onlyNumber(longueur)){
                                display("Entrée non accepté, veuillez réessayer");
                            }else{
                                choixLongueurPapier = true;
                            }
                        }
                        display("Veuillez saisir la largeur du papier : ");
                        String largeur = null;
                        boolean choixLargeurPapier = false;
                        while(!choixLargeurPapier){
                            largeur = scanner.nextLine().trim();
                            if(!onlyNumber(largeur)){
                                display("Entrée non accepté, veuillez réessayer");
                            }else{
                                choixLargeurPapier = true;
                            }
                        }
                        display("Est ce que le papier est plié ? (o,n) : ");
                        boolean papierPlie = false;
                        while(!papierPlie){
                            String plie = scanner.nextLine().trim();
                            if(rightInput(plie, INTERVALOUINON)){
                                papierPlie = plie.equals("o");
                            }else{
                                display("Entrée non accepté, veuillez réessayer");
                            }
                        }
                        FraudePapier fraudePapier = new FraudePapier(LocalDateTime.now(), contenu, description, new Tuple(longueur, largeur), papierPlie);
                        form.ajoutFraudeurs(etu, fraudePapier);
                        quitterSelFraude = true;
                        break;
                    default:
                        break;
                }
                display("Avez vous une autre fraude à renseigner ? (o,n)");
                boolean choixAutreFraude = false;
                while (!choixAutreFraude){
                    String nouvelleFraude = scanner.nextLine().trim();
                    if(rightInput(nouvelleFraude, INTERVALOUINON)){
                        if(!nouvelleFraude.equals("o")){
                            renseignerFraudeEtu = false;
                            choixAutreFraude = true;
                        }else{
                            display("Entrée non accepté, veuillez réessayer");
                        }
                    }
                }
            }
        }
    }

    public void afficherCreationEpreuve(){
        display("Entrez un code UCUE : ");
        String codeUcue = scanner.nextLine().trim();

        display("Vous allez renseigner la date de passage");
        display("Veuillez renseigner le jour de passage : ");
        int jourPassage = 0;
        boolean choixJour = false;
        while(!choixJour){
            input = scanner.nextLine().trim();
            try{
                jourPassage = rightInput(input);
                if (jourPassage >= 1 && jourPassage <= 31) {
                    choixJour = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 31");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner le mois de passage : ");
        int moisPassage = 0;
        boolean choixMois = false;
        while(!choixMois){
            input = scanner.nextLine().trim();
            try{
                moisPassage = rightInput(input);
                if (moisPassage >= 1 && moisPassage <= 12) {
                    choixMois = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 12");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner l'année de passage : ");
        int anneePassage = 0;
        boolean choixAnnee = false;
        while(!choixAnnee){
            input = scanner.nextLine().trim();
            try{
                anneePassage = rightInput(input);
                if (anneePassage >= 1900 && anneePassage <= LocalDate.now().getYear()) {
                    choixAnnee = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1900 et " + String.valueOf(LocalDate.now().getYear()));
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner l'heure de passage (en h) : ");
        int heurePassage = 0;
        boolean choixHeure = false;
        while(!choixHeure){
            input = scanner.nextLine().trim();
            try{
                heurePassage = rightInput(input);
                if (heurePassage >= 0 && heurePassage <= 23) {
                    choixHeure = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 0 et 23");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner les minutes de passage : ");
        int minPassage = 0;
        boolean choixMin = false;
        while(!choixMin){
            input = scanner.nextLine().trim();
            try{
                minPassage = rightInput(input);
                if (minPassage >= 0 && minPassage <= 59) {
                    choixMin = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 0 et 59");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner la durée de l'épreuve (en min) : ");
        int duree = 0;
        boolean choixDuree = false;
        while(!choixDuree){
            input = scanner.nextLine().trim();
            try{
                duree = rightInput(input);
                if (duree >= 0) {
                    choixDuree = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre supérieur à 0.");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez choisir la modalité de l'épreuve parmi les choix proposé : ");
        Modalite[] lesModalites = Modalite.values();
        for(int i = 0; i < lesModalites.length; i++){
            display(String.valueOf(i) + " - " + String.valueOf(lesModalites[i]));
        }
        int modaliteInt = 0;
        boolean choixModalite = false;
        while(!choixModalite){
            input = scanner.nextLine().trim();
            try{
                modaliteInt = rightInput(input);
                if (modaliteInt >= 0 && modaliteInt < lesModalites.length) {
                    choixModalite = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre supérieur à 0.");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        Modalite modalite = lesModalites[modaliteInt];
        Epreuve e = new Epreuve(codeUcue, jourPassage, moisPassage, anneePassage, heurePassage, minPassage, duree, modalite);
        sys.addEpreuve(e);
        display("L'épreuve a bien été créé");
    }
}
